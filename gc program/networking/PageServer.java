import javax.swing.text.*;

import javax.swing.text.html.*;

import javax.swing.text.html.parser.*;

import java.io.*;

import java.net.*;

import java.util.*;



public class PageServer extends HTMLEditorKit.ParserCallback {



  private Writer out;

  private URL base;

  

  public PageServer(Writer out, URL base) {

    this.out = out;

    this.base = base;

  }



  public void handleStartTag(HTML.Tag tag, 

   MutableAttributeSet attributes, int position) {

    try {  

      out.write("<" + tag);

      this.writeAttributes(attributes);

      // for the <APPLET> tag we may have to add a codebase attribute

      if (tag == HTML.Tag.APPLET 

       && attributes.getAttribute(HTML.Attribute.CODEBASE) == null) {

        String codebase = base.toString( );

        if (codebase.endsWith(".htm") || codebase.endsWith(".html")) {

          codebase = codebase.substring(0, codebase.lastIndexOf('/'));   

        }

        out.write(" codebase=\"" + codebase + "\""); 

      }

      out.write(">");

      out.flush( );

    }

    catch (IOException ex) {

      System.err.println(ex);

      ex.printStackTrace( );

    }

  }

  

  public void handleEndTag(HTML.Tag tag, int position) {

    try {    

      out.write("</" + tag + ">");

      out.flush( );

    }

    catch (IOException ex) {

      System.err.println(ex);

    }

  }

  

  private void writeAttributes(AttributeSet attributes) 

   throws IOException {

    

    Enumeration e = attributes.getAttributeNames( );

    while (e.hasMoreElements( )) {

      Object name = e.nextElement( );

      String value = (String) attributes.getAttribute(name);

      try {

        if (name == HTML.Attribute.HREF || name == HTML.Attribute.SRC 

         || name == HTML.Attribute.LOWSRC 

         || name == HTML.Attribute.CODEBASE ) {

          URL u = new URL(base, value);

          out.write(" " + name + "=\"" + u + "\"");              

        }

        else {

          out.write(" " + name + "=\"" + value + "\"");

        }

      }

      catch (MalformedURLException ex) {

        System.err.println(ex);

        System.err.println(base);

        System.err.println(value);

        ex.printStackTrace( );

      }

    }

  }

  

  public void handleComment(char[] text, int position) { 

    

    try {

      out.write("<!-- ");

      out.write(text);

      out.write(" -->");

      out.flush( );

    }

    catch (IOException ex) {

      System.err.println(ex);

    }

    

  }

  

  public void handleText(char[] text, int position) { 

    

    try { 

      out.write(text);

      out.flush( );

    }

    catch (IOException ex) {

      System.err.println(ex);

      ex.printStackTrace( );

    }

    

  }

  

  public void handleSimpleTag(HTML.Tag tag, 

   MutableAttributeSet attributes, int position) {

    try {

      out.write("<" + tag);

      this.writeAttributes(attributes);

      out.write(">");

    }

    catch (IOException ex) {

      System.err.println(ex);

      ex.printStackTrace( );

    }

  }



  public static void main(String[] args) { 

    


      

      ParserGetter kit = new ParserGetter( );

      HTMLEditorKit.Parser parser = kit.getParser( );

    

      try {

        URL u = new URL("http://localhost:8080/");

        InputStream in = u.openStream( );

        InputStreamReader r = new InputStreamReader(in);

        String remoteFileName = u.getFile( );
        
        


        if (remoteFileName.endsWith("/")) {

          remoteFileName += "index.html";

        }

        if (remoteFileName.startsWith("/")) {

          remoteFileName = remoteFileName.substring(1);

        }

        File localDirectory = new File(u.getHost( ));

        while (remoteFileName.indexOf('/') > -1) {

          String part = remoteFileName.substring(0, remoteFileName.

                                                          indexOf('/'));

          remoteFileName =

                remoteFileName.substring(remoteFileName.indexOf('/')+1);

          localDirectory = new File(localDirectory, part);

        }
        System.out.println(localDirectory+"   is");


        if (localDirectory.mkdirs( )) {

          File output = new File(localDirectory, remoteFileName);

          FileWriter out = new FileWriter(output);

          HTMLEditorKit.ParserCallback callback = new PageServer(out, u);

          parser.parse(r, callback, false);

        }

      }

      catch (IOException ex) {

        System.err.println(ex); 

        ex.printStackTrace( );

      }

      

    

    

  }

  

}
