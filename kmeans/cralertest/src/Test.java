import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test {

	public void crwal(String path) {

		File file = new File(path);
		File[] list = file.listFiles(new ExtensionFileFilter());

		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				crwal(f.getAbsolutePath());
				System.out.println("PATH:" + f.getAbsoluteFile());
			}

			else {
				System.out.println("FileName:" + f.getName());
			}
		}
	}

	public static void main(String[] args) throws IOException {

		Properties prop = new Properties();
		InputStream input = new FileInputStream(
				"src/ILCM_TestingCrawler.properties");
		prop.load(input);

		Test fr = new Test();
		fr.crwal(prop.getProperty("ROOT_LOCATION"));
	}

}

class ExtensionFileFilter implements FileFilter {
	
	@Override
	public boolean accept(File file) {

		if ((file.isDirectory())|| (((file.getName().toLowerCase().endsWith("doc"))
				|| (file.getName().toLowerCase().endsWith("pdf"))
				|| (file.getName().toLowerCase().endsWith("txt"))) && (file.getName().toLowerCase().contains("veeru")))) {
			return true;
		}

		return false;

	}
}