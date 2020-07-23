import java.io.IOException;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class FetchUrl {
 
  public static void main(String[] args) {
 
	Document doc;
	try {
 
		//get all images
		doc=Jsoup.connect("https://www.google.co.in/search?tbm=isch&q=apple&oq=apple") .userAgent("Mozilla/5.0") .timeout(30000) .get();
		/*Elements images = doc.select("img[src~=(?i)\\.(png|jpg|gif)]");
		for (Element image : images) {
 
			System.out.println("\nsrc : " + image.attr("src"));
			System.out.println("height : " + image.attr("height"));
			System.out.println("width : " + image.attr("width"));
			System.out.println("alt : " + image.attr("alt"));
 
		}*/
		
				Elements links = doc.select("a[href]");
				for (Element link : links) {
		 
					// get the value from href attribute
					System.out.println("\nlink : " + link.attr("href"));
					System.out.println("text : " + link.text());
		 
				}		
 
	} catch (IOException e) {
		e.printStackTrace();
	}
 
  }
 
}