<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
    <title>Google Maps JavaScript API Example: Extraction of Geocoding Data</title>
    <script src="http://maps.google.com/maps?file=api&v=2&key=ABQIAAAA7j_Q-rshuWkc8HyFI4V2HxQYPm-xtd00hTQOC0OXpAMO40FHAxT29dNBGfxqMPq5zwdeiDSHEPL89A" type="text/javascript"></script>
<!-- According to the Google Maps API Terms of Service you are required display a Google map when using the Google Maps API. see: http://code.google.com/apis/maps/terms.html -->
    <script type="text/javascript">

    var geocoder, location1, location2;

    function initialize() {
        geocoder = new GClientGeocoder();
    }

    function showLocation() {
        geocoder.getLocations(document.forms[0].address1.value, function (response) {
            if (!response || response.Status.code != 200)
            {
                alert("Sorry, we were unable to geocode the first address");
            }
            else
            {
                location1 = {lat: response.Placemark[0].Point.coordinates[1], lon: response.Placemark[0].Point.coordinates[0], address: response.Placemark[0].address};
                geocoder.getLocations(document.forms[0].address2.value, function (response) {
                    if (!response || response.Status.code != 200)
                    {
                        alert("Sorry, we were unable to geocode the second address");
                    }
                    else
                    {
                        location2 = {lat: response.Placemark[0].Point.coordinates[1], lon: response.Placemark[0].Point.coordinates[0], address: response.Placemark[0].address};
                        calculateDistance();
                    }
                });
            }
        });
    }
    
    function calculateDistance()
    {
        try
        {
            var glatlng1 = new GLatLng(location1.lat, location1.lon);
            var glatlng2 = new GLatLng(location2.lat, location2.lon);
            var miledistance = glatlng1.distanceFrom(glatlng2, 3959).toFixed(1);
            var kmdistance = (miledistance * 1.609344).toFixed(1);

            document.getElementById('results').innerHTML = '<strong>Address 1: </strong>' + location1.address + '<br /><strong>Address 2: </strong>' + location2.address + '<br /><strong>Distance: </strong>' + miledistance + ' miles (or ' + kmdistance + ' kilometers)';
        }
        catch (error)
        {
            alert(error);
        }
    }

    </script>
	<style type="text/css">
		content_main2.C1{background-color:#CCCCCC;}
		form.f1{background-color:#CCCCCC;}
		form1.f5{background-color:#CCCCCC;}
		input.sub{background-color:#009900;}
		input.sub1{background-color:#990000;}
		input.sub2{background-color:#009900;}
		input.sub3{background-color:#CC3366;}
		input.sub4{background-color:#FF6666;}
		input.sub5{background-color:#99FFFF;}
		content_maina1.a{background-color:#CCCCCC;}
		td.d{color:#FF9900;}
		tr.r{color:#FF9900;}
		tr.r1{color:#000000;}
		</style>
  </head>

  <body onload="initialize()">
  <div id="templatmeo_container">
	        <div id="site_title">
        	<h1><a href="#">
            	
                <span><font color="#FFFFFF" face="Times New Roman, Times, serif" size="+2"><strong><em>Fast Nearest Neighbor Search with Keywords</em></strong></font></span>
            </a></h1>
        </div>
    
  

    
    <div id="templatemo_menu">
        <ul id="templatemo_left_menu">
		
		
		
      <li><a href="#" class="current"><span></span>Find_Distance</a></li>
		
		 
      <li><a href="javascript:history.go(-1)"><span></span>Back</a></li>
            
      <li><a href="index1.jsp" ><span></span>Home</a></li>
            
      
			
      
           
        </ul>
        
		<ul id="templatemo_right_menu">
            <li><a href="#" class="first">Sitemap</a></li>
            <li><a href="#">Contact</a></li>
        </ul>
    </div> <!-- end of menu -->
    
    <div id="templatemo_banner">
    	
    </div> <!-- end of banner -->
    
    <div id="templatemo_content">

    <form action="#" onsubmit="showLocation(); return false;">
      <p>
        <input type="text" name="address1" value="Address 1" class="address_input" size="40" />
        <input type="text" name="address2" value="Address 2" class="address_input" size="40" />
        <input type="submit" name="find" value="Search" class="sub4" />
      </p><a href="adminpage.jsp"><font color="#CCFFCC"><strong>Mark_this_distance_go_back</strong></font></a>
    </form>
    
	<table ><p id="results"></p>
	
	
	</table>
 </div> <!-- end of templatemo_content -->
    
	<div id="templatemo_footer">
    	
        <ul class="footer_menu">
        	<li><a href="#" class="first"><span></span>Home</a></li>
            <li><a href="#"><span></span>Projects</a></li>
            <li><a href="#"><span></span>Services</a></li>
            <li><a href="#"><span></span>Partners</a></li>
            <li><a href="#"><span></span>Contact</a></li>
        </ul>
        
         <a href="#"><strong>Fast Nearest Neighbor Search with Keywords</strong></a>  
	</div> <!-- end of footer -->
</div> <!-- end of tempatemo_container -->

  </body>
</html>