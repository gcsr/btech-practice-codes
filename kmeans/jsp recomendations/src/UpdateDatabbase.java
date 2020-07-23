import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class UpdateDatabbase {
	
	
	int userNo=943;
	int moviesNo=1682;
	
	double[][] ratings=new double[userNo][moviesNo];
	double[][] movieGenre=new double[moviesNo][19];
	int[] recordCount=new int[userNo];
	double[][] latentFeatures=new double[userNo][19];
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
		System.out.print("update users");
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
							+sp[1]+","+male+",'"+sp[3]+"','"+sp[4]+"',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)";
					System.out.println(query);
					
					stmt.addBatch(query);
				}
				
				stmt.executeBatch();
				stmt.close();
				connection.close();
				
		}catch(BatchUpdateException exe){
			exe.getNextException().printStackTrace();
		}catch(Exception ex){
		
			ex.printStackTrace();
		}


	}
	
	
	public void updateLatentFeatures(){
		try{
			Class.forName("org.postgresql.Driver"); // load the JDBC driver
			Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/recommendations","postgres","gcsekhar");
			Statement stmt=connection.createStatement();
			
			//double[][] ratings=new 
			
			String query="select *from \"User_Movie_Rating\" order by \"UserId\",\"MovieId\" ";
			
			ResultSet rs=stmt.executeQuery(query);
			
			while(rs.next()){
				
				ratings[rs.getInt("UserId")-1][rs.getInt("MovieId")-1]=rs.getInt("Rating");
				recordCount[rs.getInt("UserId")-1]++;
				//if(user)
				
			}
			//System.out.println(recordCount[2]);
			
		
			//stmt.executeBatch();
			
			rs.close();stmt.close();
			query="select *from \"Movie_Genre\" order by \"MovieId\"";
			
			Statement stmt1=connection.createStatement();
			ResultSet rs1=stmt1.executeQuery(query);
			int i=0;
			while(rs1.next()){
				
				movieGenre[i][0]= rs1.getDouble(2);
				movieGenre[i][1]= rs1.getDouble(3);
				movieGenre[i][2]= rs1.getDouble(4);
				movieGenre[i][3]= rs1.getDouble(5);
				movieGenre[i][4]= rs1.getDouble(6);
				movieGenre[i][5]= rs1.getDouble(7);
				movieGenre[i][6]= rs1.getDouble(8);
				movieGenre[i][7]= rs1.getDouble(9);
				movieGenre[i][8]= rs1.getDouble(10);
				movieGenre[i][9]= rs1.getDouble(11);
				movieGenre[i][10]= rs1.getDouble(12);
				movieGenre[i][11]= rs1.getDouble(13);
				movieGenre[i][12]= rs1.getDouble(14);
				movieGenre[i][13]= rs1.getDouble(15);
				movieGenre[i][14]= rs1.getDouble(16);
				movieGenre[i][15]= rs1.getDouble(17);
				movieGenre[i][16]= rs1.getDouble(18);
				movieGenre[i][17]= rs1.getDouble(19);
				movieGenre[i][18]= rs1.getDouble(20);
				i++;
			
			}
			//stmt.close();
			stmt1.close();
			
			
			int count=0;
			ExecutorService pool=Executors.newFixedThreadPool(20);	
			
			//userNo=20;
			while(count<userNo){			
				synchronized(this){
					MatrixFactorization mf=new MatrixFactorization(movieGenre,ratings[count],recordCount[count]);
					Future<double[]> result = pool.submit(mf);
					latentFeatures[count]=result.get();
					for(double x:latentFeatures[count])
						System.out.print("\t"+x);
					System.out.println();
				}
				count++;
			}
			
				
			
			
			Statement stmt3=connection.createStatement();
			
			for(int ij=0;ij<userNo;ij++){
				query="update \"User\" set \"Unknown\"="+latentFeatures[ij][0]+","
						+ "\"Action\"="+latentFeatures[ij][1]+",\"Adventure\"="
						+ ""+latentFeatures[ij][2]+",\"Animation\"="+latentFeatures[ij][3]+","
						+ "\"Children\"="+latentFeatures[ij][4]+",\"Comedy\"="
						+latentFeatures[ij][5]+",\"Crime\"="+latentFeatures[ij][6]+",\"Documentry\"="
						+latentFeatures[ij][7]+",\"Drama\"="+latentFeatures[ij][8]+",\"Fantasy\"="
						+latentFeatures[ij][9]+",\"Film_Noir\"="+latentFeatures[ij][10]+",\"Horror\"="
						+latentFeatures[ij][11]+",\"Musical\"="+latentFeatures[ij][12]+",\"Mystery\"="+
						latentFeatures[ij][13]+",\"Romance\"="+latentFeatures[ij][14]+",\"Sci_Fic\"="+
						latentFeatures[ij][15]+",\"Thriller\"="+latentFeatures[ij][16]+",\"War\"="
						+latentFeatures[ij][17]+",\"Western\"="+latentFeatures[ij][18]+"where \"UserId\"="+(ij+1);
				System.out.println(query);
				stmt3.addBatch(query);
			}
			
			stmt3.executeBatch();
			connection.close();
			pool.shutdown();
			//for(00)
			
			
			
	}catch(BatchUpdateException exe){
		exe.getNextException().printStackTrace();
	}catch(Exception ex){
		ex.printStackTrace();
	}


	}
	
	
	
	public static void main(String[] gcs){

		System.out.print("main");
		UpdateDatabbase ud=new UpdateDatabbase();
		//ud.updateMovieGenre();
		//ud.updateUsers();
		//ud.updateLatentFeatures();
		
		
	}

}
