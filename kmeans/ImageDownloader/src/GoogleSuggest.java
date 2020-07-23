
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleSuggest {
    public static void main(String[] args) throws Exception {
        // The Firefox driver supports javascript 
        WebDriver driver = new FirefoxDriver();        
        // Go to the Google Suggest home page
        //driver.get("http://www.google.com/webhp?complete=1&hl=en");
        //https://www.google.co.in/search?biw=1280&bih=702&tbm=isch&q=website+background+images+technology&revid=80680060&dpr=1
        driver.get("https://www.google.co.in/search?biw=1280&bih=702&tbm=isch&q=website+background+images+technology");
        
        /*String s=driver.getPageSource();
        System.out.println(s);
        // Enter the query string "Cheese"
        /*WebElement query = driver.findElement(By.name("q"));
        query.sendKeys("Cheese");*/

        // Sleep until the div we want is visible or 5 seconds is over
        /*long end = System.currentTimeMillis() + 5000;
       /* while (System.currentTimeMillis() < end) {
            WebElement resultsDiv = driver.findElement(By.tagName("a"));

            // If results have been returned, the results are displayed in a drop down.
            if (resultsDiv.isDisplayed()) {
              break;
            }
        }
        
        

        // And now list the suggestions*/
        
        List<String> urls=new LinkedList<String>();
        List<WebElement> allSuggestions = driver.findElements(By.tagName("a"));
        //List<WebElement> allSuggestions = driver.findElements(By.xpath("//td[@class='gssb_a gbqfsf']"));
        
        System.out.println(allSuggestions.size());
        for (WebElement suggestion : allSuggestions) {        	
        	String s=suggestion.getAttribute("href");
        	if(s!=null){
        	if(s.contains(".jpg"))
        		
            urls.add(s.substring(s.indexOf("imgurl")+7,s.indexOf('&')));
        	System.out.println(s.substring(s.indexOf("imgurl")+7,s.indexOf('&')));
        	}
            //suggestion.
        }

        driver.quit();
        
        for(int i=0;i<25;i++){
        	try{
        		BinarySaver.saveBinaryFile(urls.get(i));
        	}catch(Exception ex){
        		
        	}
        }
        
    }
}