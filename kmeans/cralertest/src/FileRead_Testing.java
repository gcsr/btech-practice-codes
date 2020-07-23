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

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.Arrays;

import java.util.Properties;

import com.documentum.fc.common.DfException;

public class FileRead_Testing {

	static Properties prop = new Properties();

	static FileWriter fw = null;

	static String[] extension = null;

	static String[] filenames = null;

	static String filenamesFromProp = null;

	static String extensionFromprop = null;

	static String[] filepath = null;

	// static int count = 0;

	static {

		InputStream input;

		try {

			input = new FileInputStream(

			"src/ILCM_TestingCrawler.properties");

			prop.load(input);

			fw = new FileWriter(new File(
					prop.getProperty("OUTPUT_FILE_LOCATION")));

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	public void crwal(String path) throws IOException {

		File file = new File(path);

		File[] list = file.listFiles(new FileFilter() {

			public boolean accept(File file) {

				for (String filen : filenames) {

					for (String ext : extension) {

						if ((file.isDirectory())
								|| ((file.getName().toLowerCase().endsWith(ext)) && (file
										.getName().toLowerCase()
										.contains((filen))))) {

							return true;

						}

					}

				}

				return false;

			}

		});

		if (list == null)

			return;

		for (File f : list) {

			if (f.isDirectory()) {

				crwal(f.getAbsolutePath());

				System.out.println("PATH:" + f.getAbsoluteFile());

			}

			else {

				// count++;

				System.out.println("FileName:" + f.getAbsolutePath());

				File filesize1 = new File(f.getAbsolutePath());

				long fileSize = filesize1.length();

				// System.out.println("File size in KB is : " +
				// (double)fileSize/1024);

				// write code to fetch creation and modified date

				// put all these in a file

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				File fileModifieddate = new File(f.getAbsolutePath());

				Path file1 = Paths.get(f.getAbsolutePath());

				String filename = file1.getFileName().toString();

				String LocationType = "Local";

				BasicFileAttributes attr = Files.readAttributes(file1,
						BasicFileAttributes.class);

				// System.out.println("creationTime: " + attr.creationTime());

				// System.out.println("Modification Date: "+
				// sdf.format(fileModifieddate.lastModified()));

				fw.write(filename + "\t" + f.getAbsolutePath() + "\t"
						+ LocationType + "\t" + attr.creationTime() + "\t"
						+ sdf.format(fileModifieddate.lastModified()) + "\t"
						+ fileSize + "\n");

			}

		}

		// System.out.println("Total Files :" + count);

		fw.flush();

	}

	public void writetohive() {

		String driverName = "org.apache.hive.jdbc.HiveDriver"; // org.apache.hive.jdbc.HiveDriver
		// //working:org.apache.hadoop.hive.jdbc.HiveDriver

		try {

			Class.forName(driverName);

		} catch (ClassNotFoundException e1) {

			// TODO Auto-generated catch block

			e1.printStackTrace();

		}

		try {

			System.out.println("COnnecting to hive");

			Connection con = DriverManager.getConnection(

			"jdbc:hive2://localhost:10000/default", "hduser", "");

			Statement stmt = con.createStatement();

			String tableName = "CrawlData";

			// // String tableName1 = "tableName1";

			//

			// stmt.execute("create table " + tableName +
			// " (key int, value string)");

			// String sql = "show tables '" + tableName + "'";

			// System.out.println("Running: " + sql);

			// ResultSet res = stmt.executeQuery(sql);

			// if (res.next()) {

			// System.out.println(res.getString(1));

			// }

			String sql;

			String filepath = "/home/hduser/Desktop/mytextfile.ilcm";

			sql = "load data local inpath '" + filepath + "' into table "
					+ tableName;

			System.out.println("Running: " + sql);

			stmt.execute(sql);

			System.out.println("try block called 2");

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	public static void main(String[] args) throws Exception
	{

		String locationtypeLocal = prop.getProperty("LOCATIONTYPE_LOCAL");
		String locationtypeDocumentum = prop.getProperty("LOCATIONTYPE_DOCUMENTUM");

		//System.out.println("locationtypeLocal " + locationtypeLocal);

		if (locationtypeLocal.equals("true")) {
			
			FileRead_Testing fr = new FileRead_Testing();

			filenamesFromProp = prop.getProperty("FILE_NAME");

			//System.out.println("fromPropertyFile" + filenamesFromProp);

			extensionFromprop = prop.getProperty("FILE_EXTENSION");

			//System.out.println("fromPropertyFile" + extensionFromprop);

			if (filenamesFromProp.equals("*") && !filenamesFromProp.equals(""))

			{

				filenames = new String[] { "" };

			}

			else {

				filenames = prop.getProperty("FILE_NAME").split(",");

			}

			if (extensionFromprop.equals("*") && !extensionFromprop.equals(""))

			{

				extension = new String[] { "" };

			}

			else {

				extension = prop.getProperty("FILE_EXTENSION").split(",");

			}

			// System.out.println("TESTTTTTT filenamearray"+ filenames.length +
			// ""
			// +Arrays.toString(filenames));

			// System.out.println("TESTTTTTT extensionarray"+ extension.length +
			// ""
			// +Arrays.toString(extension));

			filepath = prop.getProperty("ROOT_LOCATION").split(",");

			for (String s : filepath) {

				System.out.println("path" + s);

				try {
					fr.crwal(s);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					FileRead_Testing.fw.close();
				}

				// fr.writetohive();

			}

		}

		else if (locationtypeDocumentum.equals("true")) {

			//System.out.println("locationtypeDocumentum " + locationtypeDocumentum);

			// Documentum crawl
			//Connectdocumentum connectDoc = new Connectdocumentum();
			
				//connectDoc.DocumentumMain();
				//System.out.println("File Writing Done :");
				
				
		}	else {
					System.out
							.println("Invalid option in Properties file. Should be either true or false");
				}
			
				
			
			// writetohive
	        	
		
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