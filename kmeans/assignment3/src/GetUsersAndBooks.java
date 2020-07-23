import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;


public class GetUsersAndBooks {
	
	int counter=0;
	String inputFileName;
	ArrayList<User> users=new SortedArrayList<User>();
	ArrayList<Book> books=new SortedArrayList<Book>();
	String[] split;
	GetUsersAndBooks(String inputFileName){
		this.inputFileName=inputFileName;
		split=readFromFile().split("\n");
		int booksLength=Integer.parseInt(split[counter]);
		counter++;
		getBooks(booksLength);
		int usersLength=Integer.parseInt(split[counter++]);
		getUsers(usersLength);
		//display();
	}

	private String readFromFile(){
		BufferedReader br = null;
		String fullText="";
		try {
 
			String sCurrentLine;
			br = new BufferedReader(new FileReader(inputFileName));
			
			while ((sCurrentLine = br.readLine()) != null) {
				fullText+=sCurrentLine+"\n";
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(fullText);
		return fullText;
		
	}
	
	private void getBooks(int length){
		String titleName,authorName;
		Book book;
		String[] names;
		while(counter<=length*2){
			titleName=split[counter++];
			authorName=split[counter++];
			names=authorName.split(" ");
			authorName=names[1]+" "+names[0];
			book=new Book(titleName,authorName);
			books.add(book);
		}
	}
	
	private void getUsers(int length){
		int f=0;
		User user;
		String userName;
		String[] names;
		while(f<length){
			userName=split[counter++];
			names=userName.split(" ");
			userName=names[1]+" "+names[0];
			user=new User(userName);
			users.add(user);
			f++;
		}
		
	}
	
	public static  void main(String[] gcs){
		new GetUsersAndBooks("D:/input.txt");
	}
	
	

}
