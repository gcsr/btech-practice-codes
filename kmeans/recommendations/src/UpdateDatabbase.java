import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class UpdateDatabbase {
	
	
	public UpdateDatabbase(){
		
	}
	
	public void updateUserMovieRatings(){
		BufferedReader br=null;
		String inputFileName="E:/old laptop/d drive/papers/harish/personalized/ml-100k/work/u1.BASE";
		String query="";

		
		//List<Movie> movies=new ArrayList<Movie>();
		try{
				Class.forName("org.postgresql.Driver"); // load the JDBC driver
				Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/recommendations","postgres","gcsekhar");
				Statement stmt=connection.createStatement();
				br = new BufferedReader(new FileReader(inputFileName));		
				String sCurrentLine="";				
				while ((sCurrentLine = br.readLine()) != null) {
					String s[]	=sCurrentLine.split("\\s+");
					query="insert into \"User_Movie_Rating\" values("+s[0]+","+s[1]+","+s[2]+","+s[3]+")";
					stmt.addBatch(query);
				}
				
				stmt.executeBatch();
				stmt.close();
				connection.close();
				
		}catch(BatchUpdateException exe){
			exe.getNextException().printStackTrace();
		}catch(Exception ex){
		
			
		}

	}
	
	
	public void updateMovieGenre(){
		BufferedReader br=null;
		String inputFileName="E:/old laptop/d drive/papers/harish/personalized/ml-100k/work/u.ITEM";
		String query="";
		
		

		
		//List<Movie> movies=new ArrayList<Movie>();
		try{
				Class.forName("org.postgresql.Driver"); // load the JDBC driver
				Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/recommendations","postgres","gcsekhar");
				Statement stmt=connection.createStatement();
				br = new BufferedReader(new FileReader(inputFileName));		
				String sCurrentLine="";			
				String name="";
				while ((sCurrentLine = br.readLine()) != null) {
					
					double []genreValues=new double[19];
					sCurrentLine=sCurrentLine.replace("||","|");
					sCurrentLine=sCurrentLine.replace("|","no:separator:");				
					String[] pp=sCurrentLine.split("no:separator:");
					int id=Integer.parseInt(pp[0]);					
					if(id==267){	
						name="";
						for(int i=0;i<19;i++){
							genreValues[i]=Double.parseDouble(pp[i+3]);	
						}					
											
					}
					else{
						name=pp[1];		
						name=name.replace('(',' ');
						name=name.replace(')',' ');
						name=name.replace(',',' ');
						name=name.replace('\'',' ');
						for(int i=0;i<19;i++){
							genreValues[i]=Double.parseDouble(pp[i+4]);
							//System.out.print("\t"+Double.parseDouble(pp[i+4]));
						}
						
					}
					
					query="insert into \"Movie_Genre\" values("+id+","
							+ ""+genreValues[0]+","+genreValues[1]+","+genreValues[2]+","
								+genreValues[3]+","+genreValues[4]+","+genreValues[5]+","
								+genreValues[6]+","+genreValues[7]+","+genreValues[8]+","
								+genreValues[9]+","+genreValues[10]+","+genreValues[11]+","
								+genreValues[12]+","+genreValues[13]+","+genreValues[14]+","
								+genreValues[15]+","+genreValues[16]+","+genreValues[17]+","
								+genreValues[18]+",'"+name+"')";
					
					System.out.println(query+";");
					
					
				stmt.addBatch(query);
				}
				
				stmt.executeBatch();
				stmt.close();
				connection.close();
				
		}catch(BatchUpdateException exe){
			exe.getNextException().printStackTrace();
		}catch(Exception ex){
		
			
		}

	}
	
	public void updateUsers(){
		BufferedReader br=null;
		String inputFileName="E:/old laptop/d drive/papers/harish/personalized/ml-100k/work/u.USER";
		String query="";

		
		//List<Movie> movies=new ArrayList<Movie>();
		try{
				Class.forName("org.postgresql.Driver"); // load the JDBC driver
				Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/recommendations","postgres","gcsekhar");
				Statement stmt=connection.createStatement();
				br = new BufferedReader(new FileReader(inputFileName));		
				String sCurrentLine="";			
				String name="";
				boolean male;
				
				while ((sCurrentLine = br.readLine()) != null) {					
					
					sCurrentLine=sCurrentLine.replace("|"," ");
					String[] sp=sCurrentLine.split("\\s+");
					System.out.println("this"+sp[2]+"this");
					
					if(sp[2]=="M")
						male=true;
					else
						male=false;
					
					query="insert into \"User\" values("+sp[0]+","
							+sp[1]+","+male+",'"+sp[3]+"','"+sp[4]+"')";					
					
					stmt.addBatch(query);
				}
				
				stmt.executeBatch();
				stmt.close();
				connection.close();
				
		}catch(BatchUpdateException exe){
			exe.getNextException().printStackTrace();
		}catch(Exception ex){
		
			
		}


	}
	
	
	public void updateLatentFeatures(){
		
	}
	
	
	
	public static void main(String[] gcs){

		UpdateDatabbase ud=new UpdateDatabbase();
		//ud.updateMovieGenre();
		//ud.updateUsers();
	}

}
