<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.util.DbConnector"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Cloud</title>
        <link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
        <link href="http://fonts.googleapis.com/css?family=Coda:400,800" rel="stylesheet" type="text/css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
        <script type="text/JavaScript">
<!--
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_validateForm() { //v4.0
  var i,p,q,nm,test,num,min,max,errors='',args=MM_validateForm.arguments;
  for (i=0; i<(args.length-2); i+=3) { test=args[i+2]; val=MM_findObj(args[i]);
    if (val) { nm=val.name; if ((val=val.value)!="") {
      if (test.indexOf('isEmail')!=-1) { p=val.indexOf('@');
        if (p<1 || p==(val.length-1)) errors+='- '+nm+' must contain an e-mail address.\n';
      } else if (test!='R') { num = parseFloat(val);
        if (isNaN(val)) errors+='- '+nm+' must contain a number.\n';
        if (test.indexOf('inRange') != -1) { p=test.indexOf(':');
          min=test.substring(8,p); max=test.substring(p+1);
          if (num<min || max<num) errors+='- '+nm+' must contain a number between '+min+' and '+max+'.\n';
    } } } else if (test.charAt(0) == 'R') errors += '- '+nm+' is required.\n'; }
  } if (errors) alert('The following error(s) occurred:\n'+errors);
  document.MM_returnValue = (errors == '');
}
//-->
        </script>
    </head>
	<%!
		long size;
	%>
	<%
									try{
										String dir="D:/m/companies/";
										File f=new File(dir+request.getParameter("fileName")); 
										size=f.length();
										}catch(Exception ex)
										{
										ex.printStackTrace();
										}
									%>
    <body>
        <div id="menu-wrapper">
            <div id="menu">
                <ul>
                    <li><a href="userHome.jsp">User Home</a></li>
                    <li><a href="fileUpload.jsp">Upload</a></li>
                    <li><a href="first.jsp">Log out</a></li>
                </ul>
            </div>
            <!-- end #menu -->
        </div>
        <div id="header-wrapper">
            <div id="header">
                <div id="logo">
                    <h1><a href="#">Cooperative Provable Data Posession</span></a></h1>                    
                </div>
            </div>
        </div>
        <div id="banner"><img src="images/img04.jpg" width="1000" height="350" alt="" /></div>
        <div id="wrapper">
            <!-- end #header -->
            <div id="page">
                <div id="page-bgtop">
                    <div id="page-bgbtm">
                        <div id="content">
                            <div class="post">
                                <h3>Report</h3>
                                <div style="clear: both;">&nbsp;</div>
                                <div class="entry" onfocus="MM_validateForm('key1','','R','key2','','R','key3','','R','key4','','R');return document.MM_returnValue">
                                    <%if (request.getParameter("msg") != null) {
                                            out.println(request.getParameter("msg"));
                                        }%>
                                    <h2>Users</h2>
									<form action="/cpdp/IntegrityCheck" onsubmit="MM_validateForm('fileName','','R','key1','','R','key2','','R','key3','','R','key4','','R');return document.MM_returnValue" >
                                    <table border="1" width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><label>FileName</label></td>
                                            <td><input type="text" name="fileName" value="<%=request.getParameter("fileName")%>"/>											</td>
										</tr>	
                                        <tr>
											<td><label>Key1</label></td>
                                            <td><input type="text" name="key1" /></td>
										</tr>
										<tr>	
											<td><label>Key2</label></td>
                                            <td><input type="text" name="key2" /></td>
										</tr>
										<tr>	
											<td><label>Key3</label></td>
                                            <td><input type="text" name="key3" /></td>
										</tr>	
										<tr>
											<td><label>Key4</label></td>
                                            <td><input type="text" name="key4" /></td>
                                        </tr>
                                       <tr><td colspan="2"  align="center"><input type="submit" value="submit"/></td></tr>
                                    </table>      
									
									<input type="hidden" name="key" value="<%=size%>"  />
								</form>	
                              </div>
                            </div>
                            <div style="clear: both;">&nbsp;</div>
                        </div>
                        <!-- end #content -->

                        <!-- end #sidebar -->
                        <div style="clear: both;">&nbsp;</div>
                    </div>
                </div>
            </div>
            <!-- end #page -->
        </div>
        <div id="footer">
            <p>Copyright &copy; 2012 JP Infotech. All rights Reserved.</p>
        </div>
        <!-- end #footer -->
    </body>
</html>
