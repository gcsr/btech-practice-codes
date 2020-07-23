
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class Documentum {
	static Properties prop = new Properties();
	

	static FileWriter	fw =null;
	static String[] extension = null;
	static String[] filenames = null;
	static String[] filepath  = null;
	boolean conditionChecked=false;
	BasicFileAttributes attr=null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	StringBuffer sb=new StringBuffer();
	static FileFilter fffff=new FileFilter() {

		public boolean accept(File file) {
			for(String filen:filenames){			
				for(String ext: extension){
					if ((file.isDirectory()) || ((file.getName().toLowerCase().endsWith(ext)) && (file.getName().toLowerCase().contains((filen)))) ){
						return true;
					}
				}
			}

			return false;

		}
	};
	
	static{
		InputStream input;
		try {
			input = new FileInputStream(
					"src/ILCM_TestingCrawler.properties");
			prop.load(input);

			fw = new FileWriter(new File(prop.getProperty("OUTPUT_FILE_LOCATION")));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	String fileName;
	public void crwal(File file) throws IOException {
		
		//System.out.println("its here");
		
		


		//File file = new File(path);
		File[] list = file.listFiles();

		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				crwal(f);
				// System.out.println("PATH:" + f.getAbsoluteFile());
			}

		
			else {
				conditionChecked=false;
				fileName=f.getName().toLowerCase();
				mainloop:
				for(String filen:filenames){			
					for(String ext: extension){
						if ((fileName.endsWith(ext)) && (fileName.contains(filen))){
							conditionChecked=true;
							//System.out.println("breaking");
							break mainloop;
						}
					}
					//if(conditionChecked)
						//break;
				}
				//System.out.println(fileName);

				if(!conditionChecked)
					continue;
				
				
				//File fileModifieddate = new File(f.getAbsolutePath());
				//Path file1 = Paths.get(f.getAbsolutePath());
				attr = Files.readAttributes(Paths.get(f.getAbsolutePath()),BasicFileAttributes.class);
				
				sb.append(f.getAbsolutePath() + "\t" + attr.creationTime()+ "\t" + sdf.format(f.lastModified()) + "\n");
				
				//System.out.println("creationTime: " + attr.creationTime());
				//
				//System.out.println("Modification Date: "+ sdf.format(fileModifieddate.lastModified()));
				//sb.append(f.getAbsolutePath() + "\t" + attr.creationTime()+ "\t" + sdf.format(fileModifieddate.lastModified()) + "\n");
				//fw.write(f.getAbsolutePath() + "\t" + attr.creationTime()+ "\t" + sdf.format(fileModifieddate.lastModified()) + "\n");

			}
		}
		fw.write(sb.toString());
		fw.flush();
	}

	public static void main(String[] args) throws IOException {



		Documentum fr = new Documentum();
		filenames =prop.getProperty("FILE_NAME").split(",");
		extension = prop.getProperty("FILE_EXTENSION").split(",");
		
		filepath = prop.getProperty("ROOT_LOCATION").split(",");
		File f;
		long curr=System.currentTimeMillis();
		for(String s:filepath){
			f=new File(s);
			fr.crwal(f);

		}
		System.out.println(" "+(System.currentTimeMillis()-curr));	

	}

}

// class ExtensionFileFilter implements FileFilter {
//
// @Override
// public boolean accept(File file) {
//
// if ((file.isDirectory())|| (((file.getName().toLowerCase().endsWith("doc"))
// || (file.getName().toLowerCase().endsWith("pdf"))
// || (file.getName().toLowerCase().endsWith("txt"))) &&
// (file.getName().toLowerCase().contains("veeru")))) {
// return true;
// }
//
// return false;
//
// }
// }