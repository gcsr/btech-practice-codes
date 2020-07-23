import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileRead_Training {

	public void crwal(String path) {

		File file = new File(path);
		File[] list = file.listFiles();

		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				crwal(f.getAbsolutePath());
				// System.out.println("PATH:" + f.getAbsoluteFile());
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

		FileRead_Training fr = new FileRead_Training();
		fr.crwal(prop.getProperty("ROOT_LOCATION"));
	}

}
