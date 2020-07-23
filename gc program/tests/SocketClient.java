
    1   /*
    2    * Licensed to the Apache Software Foundation (ASF) under one or more
    3    * contributor license agreements.  See the NOTICE file distributed with
    4    * this work for additional information regarding copyright ownership.
    5    * The ASF licenses this file to You under the Apache License, Version 2.0
    6    * (the "License"); you may not use this file except in compliance with
    7    * the License.  You may obtain a copy of the License at
    8    *
    9    *      http://www.apache.org/licenses/LICENSE-2.0
   10    *
   11    * Unless required by applicable law or agreed to in writing, software
   12    * distributed under the License is distributed on an "AS IS" BASIS,
   13    * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   14    * See the License for the specific language governing permissions and
   15    * limitations under the License.
   16    */
   17   
   18   package org.apache.commons.net.telnet;
   19   
   20   import java.io.BufferedInputStream;
   21   import java.io.BufferedOutputStream;
   22   import java.io.OutputStream;
   23   import java.io.IOException;
   24   import org.apache.commons.net.SocketClient;
   25   
   26   /**
   27    * @author Daniel F. Savarese
   28    * @author Bruno D'Avanzo
   29    */
   30   
   31   class Telnet extends SocketClient
   32   {
   33       static final boolean debug =  /*true;*/ false;
   34   
   35       static final boolean debugoptions =  /*true;*/ false;
   36   
   37       static final byte[] _COMMAND_DO = {
   38                                             (byte)TelnetCommand.IAC, (byte)TelnetCommand.DO
   39                                         };
   40   
   41       static final byte[] _COMMAND_DONT = {
   42                                               (byte)TelnetCommand.IAC, (byte)TelnetCommand.DONT
   43                                           };
   44   
   45       static final byte[] _COMMAND_WILL = {
   46                                               (byte)TelnetCommand.IAC, (byte)TelnetCommand.WILL
   47                                           };
   48   
   49       static final byte[] _COMMAND_WONT = {
   50                                               (byte)TelnetCommand.IAC, (byte)TelnetCommand.WONT
   51                                           };
   52   
   53       static final byte[] _COMMAND_SB = {
   54                                             (byte)TelnetCommand.IAC, (byte)TelnetCommand.SB
   55                                         };
   56   
   57       static final byte[] _COMMAND_SE = {
   58                                             (byte)TelnetCommand.IAC, (byte)TelnetCommand.SE
   59                                         };
   60   
   61       static final int _WILL_MASK = 0x01, _DO_MASK = 0x02,
   62                                     _REQUESTED_WILL_MASK = 0x04, _REQUESTED_DO_MASK = 0x08;
   63   
   64       /* public */
   65       static final int DEFAULT_PORT =  23;
   66   
   67       int[] _doResponse, _willResponse, _options;
   68   
   69       /* TERMINAL-TYPE option (start)*/
   70       /***
   71        * Terminal type option
   72        ***/
   73       protected static final int TERMINAL_TYPE = 24;
   74   
   75       /***
   76        * Send (for subnegotiation)
   77        ***/
   78       protected static final int TERMINAL_TYPE_SEND =  1;
   79   
   80       /***
   81        * Is (for subnegotiation)
   82        ***/
   83       protected static final int TERMINAL_TYPE_IS =  0;
   84   
   85       /***
   86        * Is sequence (for subnegotiation)
   87        ***/
   88       static final byte[] _COMMAND_IS = {
   89                                             (byte) TERMINAL_TYPE, (byte) TERMINAL_TYPE_IS
   90                                         };
   91   
   92       /***
   93        * Terminal type
   94        ***/
   95       private String terminalType = null;
   96       /* TERMINAL-TYPE option (end)*/
   97   
   98       /* open TelnetOptionHandler functionality (start)*/
   99       /***
  100        * Array of option handlers
  101        ***/
  102       private TelnetOptionHandler optionHandlers[];
  103   
  104       /* open TelnetOptionHandler functionality (end)*/
  105   
  106       /* Code Section added for supporting AYT (start)*/
  107       /***
  108        * AYT sequence
  109        ***/
  110       static final byte[] _COMMAND_AYT = {
  111                                             (byte) TelnetCommand.IAC, (byte) TelnetCommand.AYT
  112                                          };
  113   
  114       /***
  115        * monitor to wait for AYT
  116        ***/
  117       private Object aytMonitor = new Object();
  118   
  119       /***
  120        * flag for AYT
  121        ***/
  122       private boolean aytFlag = true;
  123       /* Code Section added for supporting AYT (end)*/
  124   
  125       /***
  126        * The stream on which to spy
  127        ***/
  128       private OutputStream spyStream = null;
  129   
  130       /***
  131        * The notification handler
  132        ***/
  133       private TelnetNotificationHandler __notifhand = null;
  134       /***
  135        * Empty Constructor
  136        ***/
  137       Telnet()
  138       {
  139           setDefaultPort(DEFAULT_PORT);
  140           _doResponse = new int[TelnetOption.MAX_OPTION_VALUE + 1];
  141           _willResponse = new int[TelnetOption.MAX_OPTION_VALUE + 1];
  142           _options = new int[TelnetOption.MAX_OPTION_VALUE + 1];
  143           optionHandlers =
  144               new TelnetOptionHandler[TelnetOption.MAX_OPTION_VALUE + 1];
  145       }
  146   
  147       /* TERMINAL-TYPE option (start)*/
  148       /***
  149        * This constructor lets you specify the terminal type.
  150        * <p>
  151        * @param termtype - terminal type to be negotiated (ej. VT100)
  152        ***/
  153       Telnet(String termtype)
  154       {
  155           setDefaultPort(DEFAULT_PORT);
  156           _doResponse = new int[TelnetOption.MAX_OPTION_VALUE + 1];
  157           _willResponse = new int[TelnetOption.MAX_OPTION_VALUE + 1];
  158           _options = new int[TelnetOption.MAX_OPTION_VALUE + 1];
  159           terminalType = termtype;
  160           optionHandlers =
  161               new TelnetOptionHandler[TelnetOption.MAX_OPTION_VALUE + 1];
  162       }
  163       /* TERMINAL-TYPE option (end)*/
  164   
  165       /***
  166        * Looks for the state of the option.
  167        * <p>
  168        * @return returns true if a will has been acknowledged
  169        * <p>
  170        * @param option - option code to be looked up.
  171        ***/
  172       boolean _stateIsWill(int option)
  173       {
  174           return ((_options[option] & _WILL_MASK) != 0);
  175       }
  176   
  177       /***
  178        * Looks for the state of the option.
  179        * <p>
  180        * @return returns true if a wont has been acknowledged
  181        * <p>
  182        * @param option - option code to be looked up.
  183        ***/
  184       boolean _stateIsWont(int option)
  185       {
  186           return !_stateIsWill(option);
  187       }
  188   
  189       /***
  190        * Looks for the state of the option.
  191        * <p>
  192        * @return returns true if a do has been acknowledged
  193        * <p>
  194        * @param option - option code to be looked up.
  195        ***/
  196       boolean _stateIsDo(int option)
  197       {
  198           return ((_options[option] & _DO_MASK) != 0);
  199       }
  200   
  201       /***
  202        * Looks for the state of the option.
  203        * <p>
  204        * @return returns true if a dont has been acknowledged
  205        * <p>
  206        * @param option - option code to be looked up.
  207        ***/
  208       boolean _stateIsDont(int option)
  209       {
  210           return !_stateIsDo(option);
  211       }
  212   
  213       /***
  214        * Looks for the state of the option.
  215        * <p>
  216        * @return returns true if a will has been reuqested
  217        * <p>
  218        * @param option - option code to be looked up.
  219        ***/
  220       boolean _requestedWill(int option)
  221       {
  222           return ((_options[option] & _REQUESTED_WILL_MASK) != 0);
  223       }
  224   
  225       /***
  226        * Looks for the state of the option.
  227        * <p>
  228        * @return returns true if a wont has been reuqested
  229        * <p>
  230        * @param option - option code to be looked up.
  231        ***/
  232       boolean _requestedWont(int option)
  233       {
  234           return !_requestedWill(option);
  235       }
  236   
  237       /***
  238        * Looks for the state of the option.
  239        * <p>
  240        * @return returns true if a do has been reuqested
  241        * <p>
  242        * @param option - option code to be looked up.
  243        ***/
  244       boolean _requestedDo(int option)
  245       {
  246           return ((_options[option] & _REQUESTED_DO_MASK) != 0);
  247       }
  248   
  249       /***
  250        * Looks for the state of the option.
  251        * <p>
  252        * @return returns true if a dont has been reuqested
  253        * <p>
  254        * @param option - option code to be looked up.
  255        ***/
  256       boolean _requestedDont(int option)
  257       {
  258           return !_requestedDo(option);
  259       }
  260   
  261       /***
  262        * Sets the state of the option.
  263        * <p>
  264        * @param option - option code to be set.
  265        ***/
  266       void _setWill(int option)
  267       {
  268           _options[option] |= _WILL_MASK;
  269   
  270           /* open TelnetOptionHandler functionality (start)*/
  271           if (_requestedWill(option))
  272           {
  273               if (optionHandlers[option] != null)
  274               {
  275                   optionHandlers[option].setWill(true);
  276   
  277                   int subneg[] =
  278                       optionHandlers[option].startSubnegotiationLocal();
  279   
  280                   if (subneg != null)
  281                   {
  282                       try
  283                       {
  284                           _sendSubnegotiation(subneg);
  285                       }
  286                       catch (IOException e)
  287                       {
  288                           System.err.println(
  289                               "Exception in option subnegotiation"
  290                               + e.getMessage());
  291                       }
  292                   }
  293               }
  294           }
  295           /* open TelnetOptionHandler functionality (end)*/
  296       }
  297   
  298       /***
  299        * Sets the state of the option.
  300        * <p>
  301        * @param option - option code to be set.
  302        ***/
  303       void _setDo(int option)
  304       {
  305           _options[option] |= _DO_MASK;
  306   
  307           /* open TelnetOptionHandler functionality (start)*/
  308           if (_requestedDo(option))
  309           {
  310               if (optionHandlers[option] != null)
  311               {
  312                   optionHandlers[option].setDo(true);
  313   
  314                   int subneg[] =
  315                       optionHandlers[option].startSubnegotiationRemote();
  316   
  317                   if (subneg != null)
  318                   {
  319                       try
  320                       {
  321                           _sendSubnegotiation(subneg);
  322                       }
  323                       catch (IOException e)
  324                       {
  325                           System.err.println("Exception in option subnegotiation"
  326                               + e.getMessage());
  327                       }
  328                   }
  329               }
  330           }
  331           /* open TelnetOptionHandler functionality (end)*/
  332       }
  333   
  334       /***
  335        * Sets the state of the option.
  336        * <p>
  337        * @param option - option code to be set.
  338        ***/
  339       void _setWantWill(int option)
  340       {
  341           _options[option] |= _REQUESTED_WILL_MASK;
  342       }
  343   
  344       /***
  345        * Sets the state of the option.
  346        * <p>
  347        * @param option - option code to be set.
  348        ***/
  349       void _setWantDo(int option)
  350       {
  351           _options[option] |= _REQUESTED_DO_MASK;
  352       }
  353   
  354       /***
  355        * Sets the state of the option.
  356        * <p>
  357        * @param option - option code to be set.
  358        ***/
  359       void _setWont(int option)
  360       {
  361           _options[option] &= ~_WILL_MASK;
  362   
  363           /* open TelnetOptionHandler functionality (start)*/
  364           if (optionHandlers[option] != null)
  365           {
  366               optionHandlers[option].setWill(false);
  367           }
  368           /* open TelnetOptionHandler functionality (end)*/
  369       }
  370   
  371       /***
  372        * Sets the state of the option.
  373        * <p>
  374        * @param option - option code to be set.
  375        ***/
  376       void _setDont(int option)
  377       {
  378           _options[option] &= ~_DO_MASK;
  379   
  380           /* open TelnetOptionHandler functionality (start)*/
  381           if (optionHandlers[option] != null)
  382           {
  383               optionHandlers[option].setDo(false);
  384           }
  385           /* open TelnetOptionHandler functionality (end)*/
  386       }
  387   
  388       /***
  389        * Sets the state of the option.
  390        * <p>
  391        * @param option - option code to be set.
  392        ***/
  393       void _setWantWont(int option)
  394       {
  395           _options[option] &= ~_REQUESTED_WILL_MASK;
  396       }
  397   
  398       /***
  399        * Sets the state of the option.
  400        * <p>
  401        * @param option - option code to be set.
  402        ***/
  403       void _setWantDont(int option)
  404       {
  405           _options[option] &= ~_REQUESTED_DO_MASK;
  406       }
  407   
  408       /**
  409        * Processes a DO request.
  410        * 
  411        * @param option - option code to be set.
  412        * @throws IOException - Exception in I/O.
  413        **/
  414       void _processDo(int option) throws IOException
  415       {
  416           if (debugoptions)
  417           {
  418               System.err.println("RECEIVED DO: "
  419                   + TelnetOption.getOption(option));
  420           }
  421   
  422           if (__notifhand != null)
  423           {
  424               __notifhand.receivedNegotiation(
  425                   TelnetNotificationHandler.RECEIVED_DO,
  426                   option);
  427           }
  428   
  429           boolean acceptNewState = false;
  430   
  431   
  432           /* open TelnetOptionHandler functionality (start)*/
  433           if (optionHandlers[option] != null)
  434           {
  435               acceptNewState = optionHandlers[option].getAcceptLocal();
  436           }
  437           else
  438           {
  439           /* open TelnetOptionHandler functionality (end)*/
  440               /* TERMINAL-TYPE option (start)*/
  441               if (option == TERMINAL_TYPE)
  442               {
  443                   if ((terminalType != null) && (terminalType.length() > 0))
  444                   {
  445                       acceptNewState = true;
  446                   }
  447               }
  448               /* TERMINAL-TYPE option (end)*/
  449           /* open TelnetOptionHandler functionality (start)*/
  450           }
  451           /* open TelnetOptionHandler functionality (end)*/
  452   
  453           if (_willResponse[option] > 0)
  454           {
  455               --_willResponse[option];
  456               if (_willResponse[option] > 0 && _stateIsWill(option))
  457               {
  458                   --_willResponse[option];
  459               }
  460           }
  461   
  462           if (_willResponse[option] == 0)
  463           {
  464               if (_requestedWont(option))
  465               {
  466   
  467                   switch (option)
  468                   {
  469   
  470                   default:
  471                       break;
  472   
  473                   }
  474   
  475   
  476                   if (acceptNewState)
  477                   {
  478                       _setWantWill(option);
  479                       _sendWill(option);
  480                   }
  481                   else
  482                   {
  483                       ++_willResponse[option];
  484                       _sendWont(option);
  485                   }
  486               }
  487               else
  488               {
  489                   // Other end has acknowledged option.
  490   
  491                   switch (option)
  492                   {
  493   
  494                   default:
  495                       break;
  496   
  497                   }
  498   
  499               }
  500           }
  501   
  502           _setWill(option);
  503       }
  504   
  505       /**
  506        * Processes a DONT request.
  507        * 
  508        * @param option - option code to be set.
  509        * @throws IOException - Exception in I/O.
  510        **/
  511       void _processDont(int option) throws IOException
  512       {
  513           if (debugoptions)
  514           {
  515               System.err.println("RECEIVED DONT: "
  516                   + TelnetOption.getOption(option));
  517           }
  518           if (__notifhand != null)
  519           {
  520               __notifhand.receivedNegotiation(
  521                   TelnetNotificationHandler.RECEIVED_DONT,
  522                   option);
  523           }
  524           if (_willResponse[option] > 0)
  525           {
  526               --_willResponse[option];
  527               if (_willResponse[option] > 0 && _stateIsWont(option))
  528               {
  529                   --_willResponse[option];
  530               }
  531           }
  532   
  533           if (_willResponse[option] == 0 && _requestedWill(option))
  534           {
  535   
  536               switch (option)
  537               {
  538   
  539               default:
  540                   break;
  541   
  542               }
  543   
  544               /* FIX for a BUG in the negotiation (start)*/
  545               if ((_stateIsWill(option)) || (_requestedWill(option)))
  546               {
  547                   _sendWont(option);
  548               }
  549   
  550               _setWantWont(option);
  551               /* FIX for a BUG in the negotiation (end)*/
  552           }
  553   
  554           _setWont(option);
  555       }
  556   
  557   
  558       /**
  559        * Processes a WILL request.
  560        * 
  561        * @param option - option code to be set.
  562        * @throws IOException - Exception in I/O.
  563        **/
  564       void _processWill(int option) throws IOException
  565       {
  566           if (debugoptions)
  567           {
  568               System.err.println("RECEIVED WILL: "
  569                   + TelnetOption.getOption(option));
  570           }
  571   
  572           if (__notifhand != null)
  573           {
  574               __notifhand.receivedNegotiation(
  575                   TelnetNotificationHandler.RECEIVED_WILL,
  576                   option);
  577           }
  578   
  579           boolean acceptNewState = false;
  580   
  581           /* open TelnetOptionHandler functionality (start)*/
  582           if (optionHandlers[option] != null)
  583           {
  584               acceptNewState = optionHandlers[option].getAcceptRemote();
  585           }
  586           /* open TelnetOptionHandler functionality (end)*/
  587   
  588           if (_doResponse[option] > 0)
  589           {
  590               --_doResponse[option];
  591               if (_doResponse[option] > 0 && _stateIsDo(option))
  592               {
  593                   --_doResponse[option];
  594               }
  595           }
  596   
  597           if (_doResponse[option] == 0 && _requestedDont(option))
  598           {
  599   
  600               switch (option)
  601               {
  602   
  603               default:
  604                   break;
  605   
  606               }
  607   
  608   
  609               if (acceptNewState)
  610               {
  611                   _setWantDo(option);
  612                   _sendDo(option);
  613               }
  614               else
  615               {
  616                   ++_doResponse[option];
  617                   _sendDont(option);
  618               }
  619           }
  620   
  621           _setDo(option);
  622       }
  623   
  624       /**
  625        * Processes a WONT request.
  626        * 
  627        * @param option - option code to be set.
  628        * @throws IOException - Exception in I/O.
  629        **/
  630       void _processWont(int option) throws IOException
  631       {
  632           if (debugoptions)
  633           {
  634               System.err.println("RECEIVED WONT: "
  635                   + TelnetOption.getOption(option));
  636           }
  637   
  638           if (__notifhand != null)
  639           {
  640               __notifhand.receivedNegotiation(
  641                   TelnetNotificationHandler.RECEIVED_WONT,
  642                   option);
  643           }
  644   
  645           if (_doResponse[option] > 0)
  646           {
  647               --_doResponse[option];
  648               if (_doResponse[option] > 0 && _stateIsDont(option))
  649               {
  650                   --_doResponse[option];
  651               }
  652           }
  653   
  654           if (_doResponse[option] == 0 && _requestedDo(option))
  655           {
  656   
  657               switch (option)
  658               {
  659   
  660               default:
  661                   break;
  662   
  663               }
  664   
  665               /* FIX for a BUG in the negotiation (start)*/
  666               if ((_stateIsDo(option)) || (_requestedDo(option)))
  667               {
  668                   _sendDont(option);
  669               }
  670   
  671               _setWantDont(option);
  672               /* FIX for a BUG in the negotiation (end)*/
  673           }
  674   
  675           _setDont(option);
  676       }
  677   
  678       /* TERMINAL-TYPE option (start)*/
  679       /**
  680        * Processes a suboption negotiation.
  681        * 
  682        * @param suboption - subnegotiation data received
  683        * @param suboptionLength - length of data received
  684        * @throws IOException - Exception in I/O.
  685        **/
  686       void _processSuboption(int suboption[], int suboptionLength)
  687       throws IOException
  688       {
  689           if (debug)
  690           {
  691               System.err.println("PROCESS SUBOPTION.");
  692           }
  693   
  694           /* open TelnetOptionHandler functionality (start)*/
  695           if (suboptionLength > 0)
  696           {
  697               if (optionHandlers[suboption[0]] != null)
  698               {
  699                   int responseSuboption[] =
  700                     optionHandlers[suboption[0]].answerSubnegotiation(suboption,
  701                     suboptionLength);
  702                   _sendSubnegotiation(responseSuboption);
  703               }
  704               else
  705               {
  706                   if (suboptionLength > 1)
  707                   {
  708                       if (debug)
  709                       {
  710                           for (int ii = 0; ii < suboptionLength; ii++)
  711                           {
  712                               System.err.println("SUB[" + ii + "]: "
  713                                   + suboption[ii]);
  714                           }
  715                       }
  716                       if ((suboption[0] == TERMINAL_TYPE)
  717                           && (suboption[1] == TERMINAL_TYPE_SEND))
  718                       {
  719                           _sendTerminalType();
  720                       }
  721                   }
  722               }
  723           }
  724           /* open TelnetOptionHandler functionality (end)*/
  725       }
  726   
  727       /***
  728        * Sends terminal type information.
  729        * <p>
  730        * @throws IOException - Exception in I/O.
  731        ***/
  732       final synchronized void _sendTerminalType()
  733       throws IOException
  734       {
  735           if (debug)
  736           {
  737               System.err.println("SEND TERMINAL-TYPE: " + terminalType);
  738           }
  739           if (terminalType != null)
  740           {
  741               _output_.write(_COMMAND_SB);
  742               _output_.write(_COMMAND_IS);
  743               _output_.write(terminalType.getBytes());
  744               _output_.write(_COMMAND_SE);
  745               _output_.flush();
  746           }
  747       }
  748   
  749       /* TERMINAL-TYPE option (end)*/
  750   
  751       /* open TelnetOptionHandler functionality (start)*/
  752       /**
  753        * Manages subnegotiation for Terminal Type.
  754        *
  755        * @param subn - subnegotiation data to be sent
  756        * @throws IOException - Exception in I/O.
  757        **/
  758       final synchronized void _sendSubnegotiation(int subn[])
  759       throws IOException
  760       {
  761           if (debug)
  762           {
  763               System.err.println("SEND SUBNEGOTIATION: ");
  764               if (subn != null)
  765               {
  766                   for (int ii = 0; ii < subn.length; ii++)
  767                   {
  768                       System.err.println("subn["  + ii + "]=" + subn[ii]);
  769                   }
  770               }
  771           }
  772           if (subn != null)
  773           {
  774               byte byteresp[] = new byte[subn.length];
  775               for (int ii = 0; ii < subn.length; ii++)
  776               {
  777                   byteresp[ii] = (byte) subn[ii];
  778               }
  779   
  780               _output_.write(_COMMAND_SB);
  781               _output_.write(byteresp);
  782               _output_.write(_COMMAND_SE);
  783   
  784               /* Code Section added for sending the negotiation ASAP (start)*/
  785               _output_.flush();
  786               /* Code Section added for sending the negotiation ASAP (end)*/
  787           }
  788       }
  789       /* open TelnetOptionHandler functionality (end)*/
  790   
  791       /* Code Section added for supporting AYT (start)*/
  792       /***
  793        * Processes the response of an AYT
  794        ***/
  795       final synchronized void _processAYTResponse()
  796       {
  797           if (!aytFlag)
  798           {
  799               synchronized (aytMonitor)
  800               {
  801                   aytFlag = true;
  802                   try
  803                   {
  804                       aytMonitor.notifyAll();
  805                   }
  806                   catch (IllegalMonitorStateException e)
  807                   {
  808                       System.err.println("Exception notifying:" + e.getMessage());
  809                   }
  810               }
  811           }
  812       }
  813       /* Code Section added for supporting AYT (end)*/
  814   
  815       /***
  816        * Called upon connection.
  817        * <p>
  818        * @throws IOException - Exception in I/O.
  819        ***/
  820       @Override
  821       protected void _connectAction_() throws IOException
  822       {
  823           /* (start). BUGFIX: clean the option info for each connection*/
  824           for (int ii = 0; ii < TelnetOption.MAX_OPTION_VALUE + 1; ii++)
  825           {
  826               _doResponse[ii] = 0;
  827               _willResponse[ii] = 0;
  828               _options[ii] = 0;
  829               if (optionHandlers[ii] != null)
  830               {
  831                   optionHandlers[ii].setDo(false);
  832                   optionHandlers[ii].setWill(false);
  833               }
  834           }
  835           /* (end). BUGFIX: clean the option info for each connection*/
  836   
  837           super._connectAction_();
  838           _input_ = new BufferedInputStream(_input_);
  839           _output_ = new BufferedOutputStream(_output_);
  840   
  841           /* open TelnetOptionHandler functionality (start)*/
  842           for (int ii = 0; ii < TelnetOption.MAX_OPTION_VALUE + 1; ii++)
  843           {
  844               if (optionHandlers[ii] != null)
  845               {
  846                   if (optionHandlers[ii].getInitLocal())
  847                   {
  848                       try
  849                       {
  850                           _requestWill(optionHandlers[ii].getOptionCode());
  851                       }
  852                       catch (IOException e)
  853                       {
  854                           System.err.println(
  855                               "Exception while initializing option: "
  856                               + e.getMessage());
  857                       }
  858                   }
  859   
  860                   if (optionHandlers[ii].getInitRemote())
  861                   {
  862                       try
  863                       {
  864                           _requestDo(optionHandlers[ii].getOptionCode());
  865                       }
  866                       catch (IOException e)
  867                       {
  868                           System.err.println(
  869                               "Exception while initializing option: "
  870                               + e.getMessage());
  871                       }
  872                   }
  873               }
  874           }
  875           /* open TelnetOptionHandler functionality (end)*/
  876       }
  877   
  878       /**
  879        * Sends a DO.
  880        *
  881        * @param option - Option code.
  882        * @throws IOException - Exception in I/O.
  883        **/
  884       final synchronized void _sendDo(int option)
  885       throws IOException
  886       {
  887           if (debug || debugoptions)
  888           {
  889               System.err.println("DO: " + TelnetOption.getOption(option));
  890           }
  891           _output_.write(_COMMAND_DO);
  892           _output_.write(option);
  893   
  894           /* Code Section added for sending the negotiation ASAP (start)*/
  895           _output_.flush();
  896           /* Code Section added for sending the negotiation ASAP (end)*/
  897       }
  898   
  899       /**
  900        * Requests a DO.
  901        *
  902        * @param option - Option code.
  903        * @throws IOException - Exception in I/O.
  904        **/
  905       final synchronized void _requestDo(int option)
  906       throws IOException
  907       {
  908           if ((_doResponse[option] == 0 && _stateIsDo(option))
  909               || _requestedDo(option))
  910           {
  911               return ;
  912           }
  913           _setWantDo(option);
  914           ++_doResponse[option];
  915           _sendDo(option);
  916       }
  917   
  918       /**
  919        * Sends a DONT.
  920        *
  921        * @param option - Option code.
  922        * @throws IOException - Exception in I/O.
  923        **/
  924       final synchronized void _sendDont(int option)
  925       throws IOException
  926       {
  927           if (debug || debugoptions)
  928           {
  929               System.err.println("DONT: " + TelnetOption.getOption(option));
  930           }
  931           _output_.write(_COMMAND_DONT);
  932           _output_.write(option);
  933   
  934           /* Code Section added for sending the negotiation ASAP (start)*/
  935           _output_.flush();
  936           /* Code Section added for sending the negotiation ASAP (end)*/
  937       }
  938   
  939       /**
  940        * Requests a DONT.
  941        *
  942        * @param option - Option code.
  943        * @throws IOException - Exception in I/O.
  944        **/
  945       final synchronized void _requestDont(int option)
  946       throws IOException
  947       {
  948           if ((_doResponse[option] == 0 && _stateIsDont(option))
  949               || _requestedDont(option))
  950           {
  951               return ;
  952           }
  953           _setWantDont(option);
  954           ++_doResponse[option];
  955           _sendDont(option);
  956       }
  957   
  958   
  959       /**
  960        * Sends a WILL.
  961        *
  962        * @param option - Option code.
  963        * @throws IOException - Exception in I/O.
  964        **/
  965       final synchronized void _sendWill(int option)
  966       throws IOException
  967       {
  968           if (debug || debugoptions)
  969           {
  970               System.err.println("WILL: " + TelnetOption.getOption(option));
  971           }
  972           _output_.write(_COMMAND_WILL);
  973           _output_.write(option);
  974   
  975           /* Code Section added for sending the negotiation ASAP (start)*/
  976           _output_.flush();
  977           /* Code Section added for sending the negotiation ASAP (end)*/
  978       }
  979   
  980       /**
  981        * Requests a WILL.
  982        *
  983        * @param option - Option code.
  984        * @throws IOException - Exception in I/O.
  985        **/
  986       final synchronized void _requestWill(int option)
  987       throws IOException
  988       {
  989           if ((_willResponse[option] == 0 && _stateIsWill(option))
  990               || _requestedWill(option))
  991           {
  992               return ;
  993           }
  994           _setWantWill(option);
  995           ++_doResponse[option];
  996           _sendWill(option);
  997       }
  998   
  999       /**
 1000        * Sends a WONT.
 1001        *
 1002        * @param option - Option code.
 1003        * @throws IOException - Exception in I/O.
 1004        **/
 1005       final synchronized void _sendWont(int option)
 1006       throws IOException
 1007       {
 1008           if (debug || debugoptions)
 1009           {
 1010               System.err.println("WONT: " + TelnetOption.getOption(option));
 1011           }
 1012           _output_.write(_COMMAND_WONT);
 1013           _output_.write(option);
 1014   
 1015           /* Code Section added for sending the negotiation ASAP (start)*/
 1016           _output_.flush();
 1017           /* Code Section added for sending the negotiation ASAP (end)*/
 1018       }
 1019   
 1020       /**
 1021        * Requests a WONT.
 1022        *
 1023        * @param option - Option code.
 1024        * @throws IOException - Exception in I/O.
 1025        **/
 1026       final synchronized void _requestWont(int option)
 1027       throws IOException
 1028       {
 1029           if ((_willResponse[option] == 0 && _stateIsWont(option))
 1030               || _requestedWont(option))
 1031           {
 1032               return ;
 1033           }
 1034           _setWantWont(option);
 1035           ++_doResponse[option];
 1036           _sendWont(option);
 1037       }
 1038   
 1039       /**
 1040        * Sends a byte.
 1041        *
 1042        * @param b - byte to send
 1043        * @throws IOException - Exception in I/O.
 1044        **/
 1045       final synchronized void _sendByte(int b)
 1046       throws IOException
 1047       {
 1048           _output_.write(b);
 1049   
 1050           /* Code Section added for supporting spystreams (start)*/
 1051           _spyWrite(b);
 1052           /* Code Section added for supporting spystreams (end)*/
 1053   
 1054       }
 1055   
 1056       /* Code Section added for supporting AYT (start)*/
 1057       /**
 1058        * Sends an Are You There sequence and waits for the result.
 1059        *
 1060        * @param timeout - Time to wait for a response (millis.)
 1061        * @throws IOException - Exception in I/O.
 1062        * @throws IllegalArgumentException - Illegal argument
 1063        * @throws InterruptedException - Interrupted during wait.
 1064        * @return true if AYT received a response, false otherwise
 1065        **/
 1066       final boolean _sendAYT(long timeout)
 1067       throws IOException, IllegalArgumentException, InterruptedException
 1068       {
 1069           boolean retValue = false;
 1070           synchronized (aytMonitor)
 1071           {
 1072               synchronized (this)
 1073               {
 1074                   aytFlag = false;
 1075                   _output_.write(_COMMAND_AYT);
 1076                   _output_.flush();
 1077               }
 1078   
 1079               try
 1080               {
 1081                   aytMonitor.wait(timeout);
 1082                   if (aytFlag == false)
 1083                   {
 1084                       retValue = false;
 1085                       aytFlag = true;
 1086                   }
 1087                   else
 1088                   {
 1089                       retValue = true;
 1090                   }
 1091               }
 1092               catch (IllegalMonitorStateException e)
 1093               {
 1094                   System.err.println("Exception processing AYT:"
 1095                       + e.getMessage());
 1096               }
 1097           }
 1098   
 1099           return (retValue);
 1100       }
 1101       /* Code Section added for supporting AYT (end)*/
 1102   
 1103       /* open TelnetOptionHandler functionality (start)*/
 1104   
 1105       /**
 1106        * Registers a new TelnetOptionHandler for this telnet  to use.
 1107        *
 1108        * @param opthand - option handler to be registered.
 1109        * @throws InvalidTelnetOptionException - The option code is invalid.
 1110        **/
 1111       void addOptionHandler(TelnetOptionHandler opthand)
 1112       throws InvalidTelnetOptionException
 1113       {
 1114           int optcode = opthand.getOptionCode();
 1115           if (TelnetOption.isValidOption(optcode))
 1116           {
 1117               if (optionHandlers[optcode] == null)
 1118               {
 1119                   optionHandlers[optcode] = opthand;
 1120                   if (isConnected())
 1121                   {
 1122                       if (opthand.getInitLocal())
 1123                       {
 1124                           try
 1125                           {
 1126                               _requestWill(optcode);
 1127                           }
 1128                           catch (IOException e)
 1129                           {
 1130                               System.err.println(
 1131                                   "Exception while initializing option: "
 1132                                   + e.getMessage());
 1133                           }
 1134                       }
 1135   
 1136                       if (opthand.getInitRemote())
 1137                       {
 1138                           try
 1139                           {
 1140                               _requestDo(optcode);
 1141                           }
 1142                           catch (IOException e)
 1143                           {
 1144                               System.err.println(
 1145                                   "Exception while initializing option: "
 1146                                   + e.getMessage());
 1147                           }
 1148                       }
 1149                   }
 1150               }
 1151               else
 1152               {
 1153                   throw (new InvalidTelnetOptionException(
 1154                       "Already registered option", optcode));
 1155               }
 1156           }
 1157           else
 1158           {
 1159               throw (new InvalidTelnetOptionException(
 1160                   "Invalid Option Code", optcode));
 1161           }
 1162       }
 1163   
 1164       /**
 1165        * Unregisters a  TelnetOptionHandler.
 1166        *
 1167        * @param optcode - Code of the option to be unregistered.
 1168        * @throws InvalidTelnetOptionException - The option code is invalid.
 1169        **/
 1170       void deleteOptionHandler(int optcode)
 1171       throws InvalidTelnetOptionException
 1172       {
 1173           if (TelnetOption.isValidOption(optcode))
 1174           {
 1175               if (optionHandlers[optcode] == null)
 1176               {
 1177                   throw (new InvalidTelnetOptionException(
 1178                       "Unregistered option", optcode));
 1179               }
 1180               else
 1181               {
 1182                   TelnetOptionHandler opthand = optionHandlers[optcode];
 1183                   optionHandlers[optcode] = null;
 1184   
 1185                   if (opthand.getWill())
 1186                   {
 1187                       try
 1188                       {
 1189                           _requestWont(optcode);
 1190                       }
 1191                       catch (IOException e)
 1192                       {
 1193                           System.err.println(
 1194                               "Exception while turning off option: "
 1195                               + e.getMessage());
 1196                       }
 1197                   }
 1198   
 1199                   if (opthand.getDo())
 1200                   {
 1201                       try
 1202                       {
 1203                           _requestDont(optcode);
 1204                       }
 1205                       catch (IOException e)
 1206                       {
 1207                           System.err.println(
 1208                               "Exception while turning off option: "
 1209                               + e.getMessage());
 1210                       }
 1211                   }
 1212               }
 1213           }
 1214           else
 1215           {
 1216               throw (new InvalidTelnetOptionException(
 1217                   "Invalid Option Code", optcode));
 1218           }
 1219       }
 1220       /* open TelnetOptionHandler functionality (end)*/
 1221   
 1222       /* Code Section added for supporting spystreams (start)*/
 1223       /***
 1224        * Registers an OutputStream for spying what's going on in
 1225        * the Telnet session.
 1226        * <p>
 1227        * @param spystream - OutputStream on which session activity
 1228        * will be echoed.
 1229        ***/
 1230       void _registerSpyStream(OutputStream  spystream)
 1231       {
 1232           spyStream = spystream;
 1233       }
 1234   
 1235       /***
 1236        * Stops spying this Telnet.
 1237        * <p>
 1238        ***/
 1239       void _stopSpyStream()
 1240       {
 1241           spyStream = null;
 1242       }
 1243   
 1244       /***
 1245        * Sends a read char on the spy stream.
 1246        * <p>
 1247        * @param ch - character read from the session
 1248        ***/
 1249       void _spyRead(int ch)
 1250       {
 1251           if (spyStream != null)
 1252           {
 1253               try
 1254               {
 1255                   if (ch != '\r')
 1256                   {
 1257                       spyStream.write(ch);
 1258                       if (ch == '\n')
 1259                       {
 1260                           spyStream.write('\r');
 1261                       }
 1262                       spyStream.flush();
 1263                   }
 1264               }
 1265               catch (IOException e)
 1266               {
 1267                   spyStream = null;
 1268               }
 1269           }
 1270       }
 1271   
 1272       /***
 1273        * Sends a written char on the spy stream.
 1274        * <p>
 1275        * @param ch - character written to the session
 1276        ***/
 1277       void _spyWrite(int ch)
 1278       {
 1279           if (!(_stateIsDo(TelnetOption.ECHO)
 1280               && _requestedDo(TelnetOption.ECHO)))
 1281           {
 1282               if (spyStream != null)
 1283               {
 1284                   try
 1285                   {
 1286                       spyStream.write(ch);
 1287                       spyStream.flush();
 1288                   }
 1289                   catch (IOException e)
 1290                   {
 1291                       spyStream = null;
 1292                   }
 1293               }
 1294           }
 1295       }
 1296       /* Code Section added for supporting spystreams (end)*/
 1297   
 1298       /***
 1299        * Registers a notification handler to which will be sent
 1300        * notifications of received telnet option negotiation commands.
 1301        * <p>
 1302        * @param notifhand - TelnetNotificationHandler to be registered
 1303        ***/
 1304       public void registerNotifHandler(TelnetNotificationHandler  notifhand)
 1305       {
 1306           __notifhand = notifhand;
 1307       }
 1308   
 1309       /***
 1310        * Unregisters the current notification handler.
 1311        * <p>
 1312        ***/
 1313       public void unregisterNotifHandler()
 1314       {
 1315           __notifhand = null;
 1316       }
 1317   }


