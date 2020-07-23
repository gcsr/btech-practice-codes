import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Vector;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfFormat;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfType;
import com.documentum.fc.client.IDfTypedObject;
import com.documentum.fc.client.distributed.replica.impl.refresh.operation.SysObjectCopyOperation;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfAttr;
import com.documentum.fc.common.IDfId;
import com.documentum.fc.common.IDfLoginInfo;
import com.documentum.operations.IDfExportNode;
import com.documentum.operations.IDfExportOperation;

public class Connectdocumentum {
	static Properties prop = new Properties();
	static String[] extension = null;

	static String[] filenames = null;
	static FileWriter fw = null;
	static String filenamesFromProp = null;

	static String extensionFromprop = null;

	InputStream input;

	static {

		InputStream input;

		try {

			input = new FileInputStream(

			"src/ILCM_TestingCrawler.properties");

			prop.load(input);

			fw = new FileWriter(new File(
					prop.getProperty("OUTPUT_FILE_LOCATION_DOCUMENTUM")));

		
		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	// private Vector m_fileIDs = new Vector();
	public void DocumentumMain() throws Exception 
	{
		IDfSessionManager mySessMgr = null;
		IDfSession session = null;
		Connectdocumentum cd = new Connectdocumentum();

		filenamesFromProp = prop.getProperty("FILE_NAME_DOCUMENTUM");

		//System.out.println("fromPropertyFile " + filenamesFromProp);

		extensionFromprop = prop.getProperty("FILE_EXTENSION_DOCUMENTUM");

		//System.out.println("fromPropertyFile " + extensionFromprop);

		if (filenamesFromProp.equals("*") && !filenamesFromProp.equals(""))

		{

			filenames = new String[] { "" };

		}

		else {

			filenames = prop.getProperty("FILE_NAME_DOCUMENTUM").split(",");

		}

		if (extensionFromprop.equals("*") && !extensionFromprop.equals(""))

		{

			extension = new String[] { "" };

		}

		else {

			extension = prop.getProperty("FILE_EXTENSION_DOCUMENTUM")
					.split(",");

		}

		try {
			DfClientX clientx = new DfClientX();
			IDfClient client = clientx.getLocalClient();
			IDfTypedObject config = client.getClientConfig();
			config.setString("primary_host", "172.22.254.49");
			config.setInt("primary_port", 1489);
			mySessMgr = client.newSessionManager();
			mySessMgr = client.newSessionManager();
			IDfLoginInfo mylogininfo = clientx.getLoginInfo();
			mylogininfo.setUser("Administrator");
			mylogininfo.setPassword("Password123");
			mySessMgr.setIdentity("globalrepo", mylogininfo);
			session = mySessMgr.getSession("globalrepo");
			System.out.println("Session Created");
			// cd.fetchdoclist(session);
			cd.getlistoffolders(session);
		} catch (Exception dfe) {
			dfe.printStackTrace();
			//System.out.println("DfException caught in main :");
					//+ Arrays.toString(dfe.getStackTrace()));
		}

	}

	public void fetchdoclist(IDfSession session) throws Exception {
		String docId = null;
		String docName = null;
		String creationdate = null;
		String objtype = null;
		IDfQuery query = new DfQuery();
		String path = "/Administrator/SSGBangalore";
		IDfFolder myFolder = session.getFolderByPath(path);
		System.out.println("myFolder path  :" + myFolder);

		IDfCollection folderList = myFolder.getContents(null);
		System.out.println("myFolder path count :" + folderList);

		while (folderList.next()) {
			IDfTypedObject doc = folderList.getTypedObject();
			docId = doc.getString("r_object_id");
			docName = doc.getString("object_name");

			System.out.println(docName);

		}
		if (folderList != null)
			folderList.close();
		//System.out.println("-----------------------------");

	}

	// public void exportExample (IDfSession mySession, String docId, String
	// targetLocalDirectory) throws DfException
	// {
	// IDfSessionManager mySessMgr=null;
	// try
	// {
	//
	//
	//
	// IDfId idObj =mySession.getIdByQualification(
	// "dm_document where r_object_id='" + docId + "'" );
	// IDfSysObject sysObj = (IDfSysObject) mySession.getObject(idObj);
	//
	// // Create a new client instance.
	// IDfClientX clientx = new DfClientX();
	// // Use the factory method to create an IDfExportOperation instance.
	// IDfExportOperation eo = clientx.getExportOperation();
	// // Create a document object that represents the document being exported.
	// IDfDocument doc =(IDfDocument) mySession.getObject(new DfId(docId));
	// System.out.println("Session Created for Docbase:"+sysObj);
	// // Create an export node, adding the document to the export operation
	// object.
	// IDfExportNode node = (IDfExportNode)eo.add(sysObj);
	// // Set the full file path on the local system.
	// node.setFilePath(targetLocalDirectory + doc.getObjectName());
	//
	//
	// IDfFormat format = doc.getFormat();
	// if (targetLocalDirectory.lastIndexOf("/") !=
	// targetLocalDirectory.length() - 1 &&
	// targetLocalDirectory.lastIndexOf("\\") != targetLocalDirectory.length()-
	// 1 )
	// {
	// targetLocalDirectory += "/";
	// }
	// node.setFilePath(targetLocalDirectory + doc.getObjectName() + "." +
	// format.getDOSExtension());
	//
	// if (eo.execute())
	// {
	// System.out.println( "Export operation successful." );
	// }
	// else
	// {
	// System.out.println("Export operation failed.");
	// }
	//
	// }
	// catch (Exception ex)
	// {
	// System.out.println("Exception has been thrown: " + ex);
	// ex.printStackTrace();
	//
	// }
	//
	//
	// }

	public static String getlistoffolders(IDfSession session) throws Exception {

		String folderpath = null;
		IDfQuery query = new DfQuery();
		query.setDQL("select r_folder_path,object_name from dm_folder where any i_ancestor_id in(select r_object_id from dm_folder where any r_folder_path in('/SSGBangalore'))");

		IDfCollection coll = query.execute(session, 0);
		while (coll.next()) {
			IDfTypedObject typeObject = (IDfTypedObject) coll.getTypedObject();
			//System.out.println("------------------------------------------------------------");

			folderpath = typeObject.getString("r_folder_path");

			if (folderpath != null) {
//				System.out.println("Folder Name : "
//						+ typeObject.getString("object_name") + " at "
//						+ "Folder path : "
//						+ typeObject.getString("r_folder_path"));

				docproplist(session, folderpath);
			}
		}
		return folderpath;

	}

	public static void docproplist(IDfSession session1, String folderpath)
			throws Exception {

		IDfQuery query = new DfQuery();

		query.setDQL("select * from dm_document(ALL) where folder('"
				+ folderpath + "');");

		IDfCollection coll = query.execute(session1, 0);
		while (coll.next()) {
			IDfTypedObject typeObject = (IDfTypedObject) coll.getTypedObject();

			// System.out.println("------------------------------------------------------------");
			// System.out.println("Object Name: " +
			// typeObject.getString("object_name"));
			// System.out.println("creation date: " +
			// typeObject.getString("r_creation_date"));
			// System.out.println("Modification date: " +
			// typeObject.getString("r_modify_date"));
			// System.out.println("File Extention: " +
			// typeObject.getString("a_content_type"));

			String docname = typeObject.getString("object_name");
			int size = getcontentsize(session1, docname);

//			System.out.println("------------------------------------------------------------");
			String ext = typeObject.getString("a_content_type");

			String docId = typeObject.getString("r_object_id");
			
			String LocationType ="Documentum";

			// have extension and file name string seperately for documentum in
			// ILCM prop file.
			// load the prop file ILCM prop file
			// read ext and filename string as in FileRead_Testing.. split and
			// all
			// have the loops like in FileReadin_... at line 94
			// write the test passed files to filewriter

			for (String filen : filenames) {

				for (String ext1 : extension) {

					if ((typeObject.getString("object_name").toLowerCase()
							.endsWith(ext1))
							&& (typeObject.getString("object_name")
									.toLowerCase().contains((filen)))) {

	//				System.out.println(typeObject.getString("object_name") +"  size :" +size);
						fw.write(typeObject.getString("object_name") + "\t" +folderpath + "\t"
								+ LocationType + "\t" + typeObject.getString("r_creation_date") + "\t"
								+ typeObject.getString("r_modify_date") + "\t"
								+ size + "\n");
                      
					}

				}
				fw.flush();
				
			}

			// if
			// (ext.equals("pdf")||ext.equals("msw8")||ext.equals("msw12")||ext.equals("crtext"))
			// {
			// //exportExample (session1,docId,"D:\\test"); filewrite.......
			//
			// }
			// else
			// {
			// System.out.println("Sorry Invalid Extention");
			//
			// }
		}
		if (coll != null)
			coll.close();
	}

	public static int getcontentsize(IDfSession session, String docname)
			throws DfException {
		IDfCollection rs = null;
		try {
			// Setup a new dql query
			IDfQuery query = new DfQuery();
			query.setDQL("select r_object_id, object_name, r_content_size from dm_document where object_name ='"
					+ docname + "'");

			// Execute the query and recover the results
			rs = query.execute(session, DfQuery.DF_READ_QUERY);

			// Loop through the results
			while (rs.next()) {

				int size = rs.getInt("r_content_size");
				// System.out.println("size:" +size/1024);
				return size/1024;

			}
		} finally {
			// Close the IDfCollection.
			if (rs != null) {
				rs.close();
			}
		}
		return 0;

	}
	

}
