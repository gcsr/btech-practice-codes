-import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FullLearning {
	//int userNo=943;
	int userNo=943;
	int moviesNo=1682;
	int movieCount=0;
	
	double[][] latentFeatures=new double[userNo][19];
	double[][] ratings=new double[userNo][moviesNo];		
	double[][] similarity;
	double[][] itemFeatures=new double[moviesNo][19];;
	double[][] trustValues;
	double[][] pInterest;	
	MainClass mc;
	
	int usersSize=943;
	//int usersSize=3;
	int itemsSize=1682;
	
	public FullLearning(double[][] ratings,double[][] itemFeatures,double[][] latentFeatures){
		this.ratings=ratings;
		this.itemFeatures=itemFeatures;
		this.latentFeatures=latentFeatures;
		learn();
	}
	
	public FullLearning(){
		try{
			Class.forName("org.postgresql.Driver"); // load the JDBC driver
			Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/recommendations","postgres","gcsekhar");
			Statement stmt=connection.createStatement();
			
			String query="select *from \"User\" order by \"UserId\"";
			ResultSet rs=stmt.executeQuery(query);
			
			
			while(rs.next()){		
				latentFeatures[rs.getInt("UserId")-1][0]=rs.getDouble(6);
				latentFeatures[rs.getInt("UserId")-1][1]=rs.getDouble(7);
				latentFeatures[rs.getInt("UserId")-1][2]=rs.getDouble(8);
				latentFeatures[rs.getInt("UserId")-1][3]=rs.getDouble(9);
				latentFeatures[rs.getInt("UserId")-1][4]=rs.getDouble(10);
				latentFeatures[rs.getInt("UserId")-1][5]=rs.getDouble(11);
				latentFeatures[rs.getInt("UserId")-1][6]=rs.getDouble(12);
				latentFeatures[rs.getInt("UserId")-1][7]=rs.getDouble(13);
				latentFeatures[rs.getInt("UserId")-1][8]=rs.getDouble(14);
				latentFeatures[rs.getInt("UserId")-1][9]=rs.getDouble(15);
				latentFeatures[rs.getInt("UserId")-1][10]=rs.getDouble(16);
				latentFeatures[rs.getInt("UserId")-1][11]=rs.getDouble(17);
				latentFeatures[rs.getInt("UserId")-1][12]=rs.getDouble(18);
				latentFeatures[rs.getInt("UserId")-1][13]=rs.getDouble(19);
				latentFeatures[rs.getInt("UserId")-1][14]=rs.getDouble(20);
				latentFeatures[rs.getInt("UserId")-1][15]=rs.getDouble(21);
				latentFeatures[rs.getInt("UserId")-1][16]=rs.getDouble(22);
				latentFeatures[rs.getInt("UserId")-1][17]=rs.getDouble(23);
				latentFeatures[rs.getInt("UserId")-1][18]=rs.getDouble(24);
																		
						
			}
			
			Statement stmt1=connection.createStatement();
			query="select *from \"Movie_Genre\" order by \"MovieId\"";
			ResultSet rs1=stmt1.executeQuery(query);
			
			
			while(rs1.next()){		
				itemFeatures[rs1.getInt("MovieId")-1][0]=rs1.getDouble(2);
				itemFeatures[rs1.getInt("MovieId")-1][1]=rs1.getDouble(3);
				itemFeatures[rs1.getInt("MovieId")-1][2]=rs1.getDouble(4);
				itemFeatures[rs1.getInt("MovieId")-1][3]=rs1.getDouble(5);
				itemFeatures[rs1.getInt("MovieId")-1][4]=rs1.getDouble(6);
				itemFeatures[rs1.getInt("MovieId")-1][5]=rs1.getDouble(7);
				itemFeatures[rs1.getInt("MovieId")-1][6]=rs1.getDouble(8);
				itemFeatures[rs1.getInt("MovieId")-1][7]=rs1.getDouble(9);
				itemFeatures[rs1.getInt("MovieId")-1][8]=rs1.getDouble(10);
				itemFeatures[rs1.getInt("MovieId")-1][9]=rs1.getDouble(11);
				itemFeatures[rs1.getInt("MovieId")-1][10]=rs1.getDouble(12);
				itemFeatures[rs1.getInt("MovieId")-1][11]=rs1.getDouble(13);
				itemFeatures[rs1.getInt("MovieId")-1][12]=rs1.getDouble(14);
				itemFeatures[rs1.getInt("MovieId")-1][13]=rs1.getDouble(15);
				itemFeatures[rs1.getInt("MovieId")-1][14]=rs1.getDouble(16);
				itemFeatures[rs1.getInt("MovieId")-1][15]=rs1.getDouble(17);
				itemFeatures[rs1.getInt("MovieId")-1][16]=rs1.getDouble(18);
				itemFeatures[rs1.getInt("MovieId")-1][17]=rs1.getDouble(19);
				itemFeatures[rs1.getInt("MovieId")-1][18]=rs1.getDouble(20);
																		
						
			}

							
			Statement stmt2=connection.createStatement();
			query="select *from \"User_Movie_Rating\"";
			ResultSet rs2=stmt2.executeQuery(query);
			
			
			while(rs2.next()){		
				ratings[rs2.getInt("UserId")-1][rs2.getInt("MovieId")-1]=rs2.getInt("Rating");
						
			}				
			
			
			rs.close();
			rs1.close();
			rs2.close();
			stmt1.close();
			stmt.close();
			stmt2.close();
			
			connection.close();
			
		}catch(Exception ex){
	
			ex.printStackTrace();
			System.exit(1);
		}


	}
					
	
	private void learn(){
		
		System.out.println();
		/*double[][] LTF=new double[3][19];
		LTF[0]=latentFeatures[0];
		LTF[1]=latentFeatures[1];
		LTF[2]=latentFeatures[2];
		
		latentFeatures=LTF;*/
		similarity=getSimilarityMatrix();
		System.out.println("similarity interest");
		pInterest=getPersonalInterest();
		System.out.println("personal interest");
		trustValues=getTrustValues();
		System.out.println("trust values");
		for(int i=0;i<trustValues.length;i++){
			trustValues[i]=SimilarityMatrix.getNormalizedValues(trustValues[i]);
		}		
		mc=new MainClass(ratings,latentFeatures,similarity,itemFeatures,trustValues);
		System.out.println("creating learning class");
		mc.learn();
		System.out.println("out learn method");
		
			
		
	}
	
	
	private double[][] getSimilarityMatrix(){
		return SimilarityMatrix.getSimilaritiesMatrix(latentFeatures,latentFeatures);
	}
	
	
	private double[][] getPersonalInterest(){
		return SimilarityMatrix.getSimilaritiesMatrix(latentFeatures,itemFeatures);
	}
	
	private double[][] getTrustValues(){
		
		trustValues=new double[usersSize][usersSize];
		double[] rrr=new double[usersSize];
		
		for(int i=0;i<usersSize;i++){
			for(int j=0;j<itemsSize;j++){				
				if(ratings[i][j]!=0)
				rrr[i]++;
			}
		}
		
		for(int i=0;i<usersSize;i++){
			for(int j=0;j<usersSize;j++){
				trustValues[i][j]=rrr[j]/(rrr[i]+rrr[j]);
			}
		}
		
		return trustValues;
	}
	
	public double[][] latentFeatures(){
		return mc.getLatentFeatures();
	}
	
	public double[][] itemFeatures(){
		return mc.getItemFeatures();
	}
	
	public void updateValues(){
		latentFeatures=mc.getLatentFeatures();
		itemFeatures=mc.getItemFeatures();
		
		String fileName="userLatent";
		
		
		for(int is=0;is<usersSize;is++){
			String ff= fileName+is+".dat";
			WriteToFile.write(latentFeatures[is],ff);
		}
		
		fileName="itemLatent";
	
		
		for(int is=0;is<itemsSize;is++){
			String ff= fileName+is+".dat";
			WriteToFile.write(itemFeatures[is],ff);
		}
	
	
		
		/*try{
			Class.forName("org.postgresql.Driver"); // load the JDBC driver
			Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/recommendations","postgres","gcsekhar");
			Statement stmt=connection.createStatement();
			
			String query="select *from \"User\" order by \"UserId\"";
			ResultSet rs=stmt.executeQuery(query);
			double[] result;
			for(int i=0;i<userNo;i++){
				result=latentFeatures[i];
				query="update \"User\" set \"Unknown\"="
						+result[0]+",\"Action\"="+result[1]+",\"Adventure\"="
						+result[2]+",\"Animation\"="+result[3]+",\"Children\"="
						+result[4]+",\"Comedy\"="+result[5]+",\"Crime\"="
						+result[6]+",\"Documentry\"="+result[7]+",\"Drama\"="
						+result[8]+",\"Fantasy\"="+result[9]+",\"Film_Noir\"="
						+result[10]+",\"Horror\"="+result[11]+",\"Musical\"="
						+result[12]+",\"Mystery\"="+result[13]+",\"Romance\"="
						+result[14]+",\"Sci_Fic\"="+result[15]+",\"Thriller\"="
						+result[16]+",\"War\"="+result[17]+",\"Western\"="
						+result[18]+"where \"UserId\"="+(i+1);
				System.out.println(query);
				stmt.addBatch(query);
				
			}
			
			for(int i=0;i<moviesNo;i++){
				result=itemFeatures[i];
				query="update \"Movie_Genre\" set \"Unknown\"="
						+result[0]+",\"Action\"="+result[1]+",\"Adventure\"="
						+result[2]+",\"Animation\"="+result[3]+",\"Children\"="
						+result[4]+",\"Comedy\"="+result[5]+",\"Crime\"="
						+result[6]+",\"Documentry\"="+result[7]+",\"Drama\"="
						+result[8]+",\"Fantasy\"="+result[9]+",\"Film_Noir\"="
						+result[10]+",\"Horror\"="+result[11]+",\"Musical\"="
						+result[12]+",\"Mystery\"="+result[13]+",\"Romance\"="
						+result[14]+",\"Sci_Fic\"="+result[15]+",\"Thriller\"="
						+result[16]+",\"War\"="+result[17]+",\"Western\"="
						+result[18]+"where \"MovieId\"="+(i+1);
				System.out.println(query);
				stmt.addBatch(query);
				
			}

			stmt.executeBatch();
			
			stmt.close();

			connection.close();
			
		}catch(Exception ex){
	
			ex.printStackTrace();
		}*/

	}
	
	private void displayUsers(){
		
		String fileName="userLatent";
		
		double[][] iiiii=new double[3][19];
		for(int is=0;is<3;is++){
			String ff= fileName+is+".dat";
			iiiii[is]=ReadFromFile.read(ff);
		}
		
		for(int i=0;i<3;i++){
			System.out.println();
			for(int j=0;j<19;j++){
				System.out.print(j+" "+iiiii[i][j]+"\t");
			}
		}
		
	}
	
	private void updateLearningsToDatabase(){
		
		
		try{			
			
			String query=""; 
			
			Class.forName("org.postgresql.Driver"); // load the JDBC driver
			Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/recommendations","postgres","gcsekhar");
			Statement stmt=connection.createStatement();
			
			
			String fileName="userLatent";
			double[][] latentFeatures=new double[usersSize][19];
			double[][] itemFeatures=new double[itemsSize][19];
			
			
			for(int is=0;is<usersSize;is++){
				String ff= fileName+is+".dat";
				latentFeatures[is]=ReadFromFile.read(ff);
			}
			
			
			fileName="itemLatent";
			for(int i=0;i<itemsSize;i++){
				String ff= fileName+i+".dat";
				itemFeatures[i]=ReadFromFile.read(ff);
			}
			
		
			double[] result;
			
			
			for(int i=0;i<usersSize;i++){
				result=latentFeatures[i];
				query="update \"User\" set \"Unknown\"="
						+result[0]+",\"Action\"="+result[1]+",\"Adventure\"="
						+result[2]+",\"Animation\"="+result[3]+",\"Children\"="
						+result[4]+",\"Comedy\"="+result[5]+",\"Crime\"="
						+result[6]+",\"Documentry\"="+result[7]+",\"Drama\"="
						+result[8]+",\"Fantasy\"="+result[9]+",\"Film_Noir\"="
						+result[10]+",\"Horror\"="+result[11]+",\"Musical\"="
						+result[12]+",\"Mystery\"="+result[13]+",\"Romance\"="
						+result[14]+",\"Sci_Fic\"="+result[15]+",\"Thriller\"="
						+result[16]+",\"War\"="+result[17]+",\"Western\"="
						+result[18]+"where \"UserId\"="+(i+1);
				System.out.println(query);
				stmt.addBatch(query);
				
			}
			
			for(int i=0;i<itemsSize;i++){
				result=itemFeatures[i];
				query="update \"Movie_Genre\" set \"unknown\"="
						+result[0]+",\"Action\"="+result[1]+",\"Adventure\"="
						+result[2]+",\"Animation\"="+result[3]+",\"Children\"="
						+result[4]+",\"Comedy\"="+result[5]+",\"Crime\"="
						+result[6]+",\"Documentory\"="+result[7]+",\"Drama\"="
						+result[8]+",\"Fantasy\"="+result[9]+",\"Film_Noir\"="
						+result[10]+",\"Horror\"="+result[11]+",\"Musical\"="
						+result[12]+",\"Mystery\"="+result[13]+",\"Romance\"="
						+result[14]+",\"Sci-Fici\"="+result[15]+",\"Thriller\"="
						+result[16]+",\"War\"="+result[17]+",\"Western\"="
						+result[18]+"where \"MovieId\"="+(i+1);
				System.out.println(query);
				stmt.addBatch(query);
				
			}

			stmt.executeBatch();
			
			stmt.close();

			connection.close();
			
		}catch(Exception ex){
	
			ex.printStackTrace();
		}

	}
	
	public static void main(String[] gcs){
		//System.out.println("Fuck");
		FullLearning lr=new FullLearning();
		//iiiiiiiiilr.learn();
		//jjjjjjjjjjlr.updateValues();
		//lr.displayUsers();
		lr.updateLearningsToDatabase();
	}
}
