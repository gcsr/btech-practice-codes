import java.util.Arrays;
import java.util.Comparator;


public class WordDealer {
	
	private static  String[] singleWords={
	"a","an","and","the","I","you","he","she","it","they","is",
	"we","me","him","her","it","us","them","me","myself","mine",
	"my","ourselves","ourself","ours","our","your","yourself","himself","his",
	"herself","oneself","one's",	
	"abaft","aboard",
	"about","above","absent","across","afore","after",
	"against","along","alongside","amid","amidst",
	"among","amongst","an","anenst","apropos","apud",
	"around","as","aside","astride","at","athwart","atop",
	"barring","before","behind","below","beneath","beside",
	"besides","between","betwixt","beyond","but","by","circa",
	"concerning","despite","down","during","except","excluding",
	"failing","following","for","forenenst","from","given","in",
	"including","inside","into","lest","like","mid","midst",
	"minus","modulo","near","next","notwithstanding","o'",
	"of","off","on","onto","opposite","out","outside","over",
	"pace","past","per","plus","pro","qua","regarding","round",
	"sans","save","since","than","through","throughout","till",
	"until","times","to","toward","towards","under","underneath",
	"unlike","until","unto","up","upon","versus","via","vice",
	"vis-à-vis","with","within","without","worth","was","were","has","have","had",
	"will","shall","would","should"
	
	};
	
	
	
	private static  String[] twoWords={"according to","ahead of","apart from","as for",
			"as of","as per","as regards","aside from","back to","because of",
			"close to","due to","except for","far from","in to","inside of",
			"instead of","left of","near to","next to","on to","out from",
			"out of","outside of","owing to","prior to","pursuant to",
			"rather than","regardless of","	right of","subsequent to",
			"such as","thanks to","that of","up to","where as"
			};
	
	private static String[] threeWords={"as far as","as long as","as opposed to",
			"as well as","as soon as","by means of","by virtue of",
			"for the sake of","in accordance with","in addition to",
			"in case of","in front of","in lieu of","in order to",
			"in place of","in point of","in spite of","	on account of",
			"on behalf of","on top of","with regard to","with respect to",
			"with a view to"
			};
	private static String[] fourWords={"at the behest of","for the sake of"};
	static {
		
		Arrays.sort(singleWords, new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				
				return o2.length()-o1.length();
			}
			
		});
		Arrays.sort(twoWords, new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				
				return o2.length()-o1.length();
			}
			
		});	

		Arrays.sort(threeWords, new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				
				return o2.length()-o1.length();
			}
			
		});	

		Arrays.sort(fourWords, new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				
				return o2.length()-o1.length();
			}
			
		});	

		
	}
	
	public static boolean  isInTheList(String testS){
		for(String s:fourWords){
			if(s.equals(testS))
				return true;
		}
		
		for(String s:threeWords){
			if(s.equals(testS))
				return true;
		}
		
		for(String s:twoWords){
			if(s.equals(testS))
				return true;
		}
		
		for(String s:singleWords){
			if(s.equals(testS))
				return true;
		}
		
		
		return false;
	}
	
	public static boolean isAuxillary(String[] group){
		
		for(String s:group){
			if(!isInTheList(s))
				return false;
		}
		return true;
	}
	
	
	public static String  removeAuxillaryWords(String cipherText){
		
		for(String s:fourWords){
			cipherText=cipherText.replace(" "+s+" ","  ");
		}
		
		for(String s:threeWords){
			cipherText=cipherText.replace(" "+s+" ","  ");
		}
		
		for(String s:twoWords){
			cipherText=cipherText.replace(" "+s+" ","  ");
		}
		
		for(String s:singleWords){
			cipherText=cipherText.replace(" "+s+" ","  ");
		}
		
		return cipherText;
		
	}
	
	

}
