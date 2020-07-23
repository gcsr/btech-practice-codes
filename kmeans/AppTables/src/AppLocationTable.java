
public class AppLocationTable {

    /******************************* location table *********************/
    public static final String TABLE_LOCATION = "location";
    public static final String LOCATION_ID = "id";
    public static final String LOCATION_LATITUDE = "latitude";
    public static final String LOCATION_LONGITUDE = "longitude";
    public static final String LOCATION_URL = "location_url";
    public static final String LOCATION_KEYWORDS = "keywords";
    public static final String LOCATION_ADDRESS_LINE = "address_line";
    public static final String LOCATION_ADMIN_AREA = "admin_area";
    public static final String LOCATION_COUNTRY_CODE = "country_code";
    public static final String LOCATION_COUNTRY_NAME = "country_name";
    public static final String LOCATION_FEATURE_NAME = "feature_name";
    public static final String LOCATION_MAX_ADDRESS_LINE_INDEX = "address_line_index";
    public static final String LOCATION_PHONE = "phone";
    public static final String LOCATION_POSTAL_CODE = "postal_code";
    public static final String LOCATION_PREMISES = "premises";
    public static final String LOCATION_SUB_ADMIN_AREA = "sub_admin_area";
    public static final String LOCATION_SUB_LOCALITY = "sub_locality";
    public static final String LOCATION_SUB_THROUGH_FARE = "sub_through_fare";
    public static final String LOCATION_THROUGH_FARE = "through_fare";
    public static final String LOCATION_LIKES = "likes";
    public static final String LOCATION_VERIFICATIONS = "verifications";
    public static final String LOCATION_FAVOURITES = "favourites";
    public static final String LOCATION_DISLIKES = "dislikes";
    public static final String LOCATION_EMAIL1 = "email1";
    public static final String LOCATION_EMAIL2 = "email2";
    public static final String LOCATION_NAME = "name";
    public static final String LOCATION_TYPE = "type";
    public static final String LOCATION_SUBTYPE = "sub_type";
    public static final String LOCATION_TYPE2 = "type2";
    public static final String LOCATION_SUBTYPE2 = "sub_type2";
    public static final String LOCATION_TYPE3 = "type3";
    public static final String LOCATION_SUBTYPE3 = "sub_type3";
    public static final String LOCATION_PROFESSION1 = "profession1";
    public static final String LOCATION_PROFESSION2 = "profession2";
    public static final String LOCATION_PROFESSION3 = "profession3";
    public static final String LOCATION_SCALE1 = "scale1";
    public static final String LOCATION_SCALE2 = "scale2";
    public static final String LOCATION_SCALE3 = "scale3";
    public static final String LOCATION_URL_THUMBNAIL1 = "thumbnail1";
    public static final String LOCATION_URL_THUMBNAIL2 = "thumbnail2";
    public static final String LOCATION_URL_THUMBNAIL3 = "thumbnail3";
    public static final String LOCATION_FILE_URL = "file_url";

    /******************************* location table *********************/

	 public static final String[] LOCATION_COLUMN_NAMES={
         LOCATION_ID, LOCATION_LATITUDE, LOCATION_LONGITUDE, LOCATION_URL,
         LOCATION_KEYWORDS, LOCATION_ADDRESS_LINE, LOCATION_ADMIN_AREA, LOCATION_COUNTRY_CODE,
         LOCATION_COUNTRY_NAME, LOCATION_FEATURE_NAME, LOCATION_MAX_ADDRESS_LINE_INDEX, LOCATION_PHONE,
         LOCATION_POSTAL_CODE, LOCATION_PREMISES, LOCATION_SUB_ADMIN_AREA, LOCATION_SUB_LOCALITY,
         LOCATION_SUB_THROUGH_FARE, LOCATION_THROUGH_FARE, LOCATION_LIKES, LOCATION_VERIFICATIONS,
         LOCATION_FAVOURITES, LOCATION_DISLIKES, LOCATION_EMAIL1, LOCATION_EMAIL2,
         LOCATION_NAME, LOCATION_TYPE,LOCATION_SUBTYPE, LOCATION_TYPE2,
         LOCATION_SUBTYPE2, LOCATION_TYPE3, LOCATION_SUBTYPE3, LOCATION_PROFESSION1,
         LOCATION_PROFESSION2, LOCATION_PROFESSION3, LOCATION_SCALE1, LOCATION_SCALE2,
         LOCATION_SCALE3, LOCATION_URL_THUMBNAIL1, LOCATION_URL_THUMBNAIL2, LOCATION_URL_THUMBNAIL3,
         LOCATION_FILE_URL
	 };
	 
	 public static final String[] LOCATION_COLUMN_TYPES={
         "integer", "double", "double", "text",
         "text", "text", "text", "text",
         "text", "text", "int", "text",
         "text", "text", "text", "text",
         "text", "text", "int", "int",
         "int", "int", "text", "text",
         "text", "text", "text", "text",
         "text", "text", "text", "text",
         "text", "text", "text", "text",
         "text", "text", "text", "text",
         "text"
	 };
	 
	 public static final String[] LOCATION_CONSTRAINTS = {

	 };
	 
	 public String getLocationTableQuery(){

	        return null;
	 }
	 
	 public static String getTableQuery(String[] columnNames, String[] columnTypes, String[] constraitns){
		 String query = "create table (";
		 
		 for(int i=0; i<columnNames.length ; i++){
			 query += columnNames[i] + " "+ columnTypes[i]+" ," ;
		 }
		 
		 query = query.substring(0, query.length()-1);
		 query += ")" ;
		 return query;
	 }

	 public static void main(String[] gcs){
		 String tableQuery = getTableQuery(LOCATION_COLUMN_NAMES, LOCATION_COLUMN_TYPES,LOCATION_CONSTRAINTS);
		 System.out.println(tableQuery);
	 }
	  
}
