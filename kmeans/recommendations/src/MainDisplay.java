import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;




public class MainDisplay extends JFrame{
	JPanel lafetPanel,rightPanel,middlePanel;
	GridBagConstraints constraints;
	JTextField usersField,itemsField;
	
	JButton loadAll,loadSpecific,learn,selectMovie,find,displayMovies,displayUsers;
	ReadMovie moviesObject;
	LatentFeatureParams LFParams;
	//JLabel usersLabl,itemsLabel;
	Movie[] movies;
	UserRating[] users;
	GridBagLayout layout;
	int leftBound,rightBound;
	int movieCount=0;
	int[] usersList;
	int[] moviesList;
	double[][] latentFeatures;

	double[][] ratings;		
	double[][] similarity;
	double[][] itemFeatures;
	double[][] trustValues;
	double[][] pInterest;	
	MainClass mc;

	
	public MainDisplay(){
		super("Recommendations");
		setLayout(new BorderLayout());
		add(new LeftPanel(), BorderLayout.WEST);
		add(new RightPanel(), BorderLayout.EAST);
		add(new MiddlePanel(), BorderLayout.CENTER);
		
	}
	
	class LeftPanel extends JPanel{
		LeftPanel(){
			
			Box vertical1 = Box.createVerticalBox();
			//vertical1.add( Box.createVerticalStrut( 100 ) );
			loadAll=new JButton("Load Data");
			vertical1.add(loadAll);
			
			vertical1.add( Box.createVerticalStrut( 10) );
			
			loadAll.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					readFiles();
				}
			});
			
			displayMovies=new JButton("Display Movies");
			vertical1.add(displayMovies);
			vertical1.add( Box.createVerticalStrut( 10) );
			
			displayMovies.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					MoviesTable table=new MoviesTable(movies);
					table.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					table.setSize(400,600);
					table.setVisible(true);
				}
			});
			
			displayUsers=new JButton("Display Users");
			vertical1.add(displayUsers);
			add(vertical1);
			
			vertical1.add( Box.createVerticalStrut( 10) );
			
			learn=new JButton("Learn");
			vertical1.add(learn);
			vertical1.add( Box.createVerticalStrut( 10) );
			learn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					learn();
				}
			});
			
			
			
			vertical1.add( Box.createVerticalStrut( 10) );
			
			selectMovie=new JButton("Select Movie");
			vertical1.add(selectMovie);
			
			selectMovie.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					SelectionFrame sf=new SelectionFrame();
					sf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					sf.setSize(600,400);
					sf.setVisible(true);
				}
			});
			vertical1.add( Box.createVerticalStrut( 10) );
			find=new JButton("Find Rating");			
			vertical1.add(find);
			
						
		}		
	}
	
	class RightPanel extends JPanel{
		RightPanel(){
			usersField=new JTextField("",20);
			itemsField=new JTextField("",20);
			Box vertical1 = Box.createVerticalBox();
			//vertical1.add( Box.createVerticalStrut( 100 ) );
			vertical1.add(new JLabel("Uses No"));
			//vertical1.add( Box.createVerticalStrut( 25) );
			vertical1.add(usersField);
			vertical1.add( Box.createVerticalStrut( 10) );
			vertical1.add(new JLabel("Movies No"));			
			vertical1.add(itemsField);
			vertical1.add( Box.createVerticalStrut( 25) );
			
			loadSpecific=new JButton("Load Specfied");
			vertical1.add(loadSpecific);
			loadSpecific.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					assignParameters();
					filter();
				}
			});
			add(vertical1);
		}
	}
	
	class MiddlePanel extends JPanel{
		MiddlePanel(){
			
		}
	}
	
	public static void main(String[] gcs){
		MainDisplay dis=new MainDisplay();
		dis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dis.setSize(800,400);
		dis.setVisible(true);
	}
	
	private void readFiles(){
		LFParams=new LatentFeatureParams();
		movies=LFParams.getMovies();
		users=LFParams.getUserRatings();
	}
	
	
	private void assignParameters(){
		try{
			String s=usersField.getText();
			String[] ss=s.split("-");
			leftBound=Integer.parseInt(ss[0]);
			rightBound=Integer.parseInt(ss[1]);
			
			movieCount=Integer.parseInt(itemsField.getText());
			//filter();
		}catch(Exception ex){
			ex.printStackTrace();
			return;
		}
	}
	
	private void filter(){
		System.out.println("in filter method");
		//LFParams=new LatentFeatureParams();
		int userLength=rightBound-leftBound+1;
		usersList=new int[userLength];
		for(int i=0;i<userLength;i++){
			usersList[i]=leftBound+i;
		}
		moviesList=new int[movieCount];
		movies=LFParams.getFilteredMovies(usersList,movieCount);
		
		int i=0;
		for(Movie m:movies){
			moviesList[i]=m.getId();
			i++;
		}
		
		System.out.println("last line filter method");
		users=LFParams.getFilteredUsers(moviesList, usersList);
		System.out.println("out filter method");
	}
	
	
	private void learn(){
		System.out.println("in learn method");
		latentFeatures=LFParams.findAndGetFactors(users);
		System.out.println("latent features");
		ratings=getUserRatings();
		System.out.println("user ratings");
		itemFeatures=getItemFeatures();
		System.out.println("item features");
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
	
	private double[][] getUserRatings(){
		double[][] userRatingsArray=new double[users.length][movies.length];
		int userID;
		int movieID;
		for(UserRating ur:users){
			userID=ur.getUserId();
			List<MovieRating> mrs=ur.getMovieRatings();
				
			Iterator itr=mrs.iterator();			
			while(itr.hasNext()){
				MovieRating mr=(MovieRating)itr.next();
				//System.out.println(mr);
				movieID=mr.getMovieId();
				userRatingsArray[getUserIndex(userID)][getMovieIndex(movieID)]=mr.getRating();
			}
		}
		return userRatingsArray;
	}
	
	
	private double[][] getItemFeatures(){
		double[][] itemFeaturesArray=new double[movies.length][19];		
		for(Movie mv:movies){
			itemFeaturesArray[getMovieIndex(mv.getId())]=mv.getGenreValues();
		}
		return itemFeaturesArray;
	}
	
	private double[][] getSimilarityMatrix(){
		return SimilarityMatrix.getSimilaritiesMatrix(latentFeatures,latentFeatures);
	}
	
	
	private double[][] getPersonalInterest(){
		return SimilarityMatrix.getSimilaritiesMatrix(latentFeatures,itemFeatures);
	}
	
	private double[][] getTrustValues(){
		int usersSize=users.length;
		double[] rrr=new double[usersSize];
		int itemsSize=movies.length;
		
		trustValues=new double[usersSize][usersSize];
		
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
	
	private int getMovieIndex(int index){
		for(int i=0;i<movieCount;i++){
			if(moviesList[i]==index)
				return i;
		}
		
		return -10;
	}
	
	private int getUserIndex(int index){
		for(int i=0;i<movieCount;i++){
			if(usersList[i]==index)
				return i;
		}
		
		return -10;
	}
	
	class SelectionFrame extends JFrame{
				
		private GridBagLayout layout; // layout of this frame
		private GridBagConstraints constraints; // constraint
		
		javax.swing.JList moviesList,usersList;
		SelectionFrame(){
			
			layout = new GridBagLayout();                                     
			setLayout( layout ); // set frame layout                          
			constraints = new GridBagConstraints(); // instantiate constraints
			
			
			constraints.fill = GridBagConstraints.NONE;
	        constraints.weightx=0;        
	        constraints.weighty=0.5;
	        constraints.gridx = 0; // set gridx                           
	        constraints.gridy = 0; // set gridy                              
	        constraints.gridwidth =1; // set gridwidth                    
	        constraints.gridheight =1; // set gridheight                 
	        add(new JLabel("Select User"),constraints);
		
			moviesList= new JList(new MovieListModel(movies)); // create with colorNames
			moviesList.setToolTipText("Select Movie");
			moviesList.setVisibleRowCount( 2 ); // display five rows at once
			moviesList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
			
			usersList = new JList(new UserListModel(users)); // create with colorNames
			usersList.setToolTipText("Select User");
			usersList.setVisibleRowCount( 2 ); // display five rows at once
			usersList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
			
			constraints.gridx = 1; // set gridx                           
		    constraints.gridy = 0; // set gridy                              
		    constraints.gridwidth =1; // set gridwidth                    
		    constraints.gridheight =1; // set gridheight
		    add( new JScrollPane(moviesList) ,constraints);
		    constraints.gridx = 0; // set gridx                           
		    constraints.gridy = 1; // set gridy                              
		    constraints.gridwidth =1; // set gridwidth                    
		    constraints.gridheight =1; // set gridheight	    
		    add(new JLabel("Select User"),constraints);
		    constraints.gridx = 1; // set gridx                           
		    constraints.gridy = 1; // set gridy                              
		    constraints.gridwidth =1; // set gridwidth                    
		    constraints.gridheight =1; // set gridheight
			
			add( new JScrollPane(usersList),constraints);
			
			constraints.gridx = 0; // set gridx                           
		    constraints.gridy = 2; // set gridy                              
		    constraints.gridwidth =2; // set gridwidth                    
		    constraints.gridheight =1; // set gridheight
			
			JButton button=new JButton("rating");
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					double rating=mc.getRating(usersList.getSelectedIndex(),moviesList.getSelectedIndex());
					JOptionPane.showMessageDialog(MainDisplay.this,"Selected Rating is "+rating);
				}
			});
			
			add(button,constraints);
		}
		
	}
	
	
	/*private void ss(){
		int[] result;
		int userid;
		//String query="update \"User\" set \"Unknown\"="+result[0]+",\"Action\"="+result[1]+",\"Adventure\"="+result[2]+",\"Animation\"="+result[3]+",\"Children\"="+result[4]+",\"Comedy\"="+result[5]+",\"Crime\"="+result[6]+",\"Documentory\"="+result[7]+",\"Drama\"="+result[8]+",\"Fantasy\"="+result[9]+",\"Film_Noir\"="+result[10]+",\"Horror\"="+result[11]+",\"Musical\"="+result[12]+",\"Mystery\"="+result[13]+",\"Romance\"="+result[14]+",\"Sci_Fic\"="+result[15]+",\"Thriller\"="+result[16]+",\"War\"="+result[17]+",\"Western\"="+result[18]+"where \"UserId\="+userid;
	}*/
	
	
	
	
}
