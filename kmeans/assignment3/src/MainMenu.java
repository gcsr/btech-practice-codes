import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class MainMenu{
	String outputFileName="";
    FileWriter fileWriter;
    Scanner scanner;
    int stringLength=30;
    int bookNo;
    int userNo;
    SortedArrayList<User> users;
    SortedArrayList<Book> books;
    int booksLength;
    int usersLength;
    
    String search1;
    String search2;
    String search3;
    String search4;
    String search5;
    
    
    public MainMenu(List<Book> books, List<User> users,String outputFileName){
    	
    	this.books=(SortedArrayList)books;
    	this.users=(SortedArrayList)users;
    	
    	booksLength=books.size();
    	usersLength=users.size();
    	
    	this.outputFileName=outputFileName;
    	try{
    		
    		fileWriter=new FileWriter(outputFileName);
    	}catch(Exception ex){
    		System.out.println("out file is not correct path");
    		System.exit(1);
    	}
    	
    	
    	while(true){
    		System.out.println("--------------------------------------------------------------------------------");
    		System.out.println("f: to finish running the program.");
    		System.out.println("b: to display on the screen the information about all the books in the library.");
    		System.out.println("u: to display on the screen the information about all the users.");
    		System.out.println("i: to update the stored data when a book is issued to a user.");
    		System.out.println("r: to update the stored data when a user returns a book to the library.");    		
    		System.out.println("--------------------------------------------------------------------------------");
    		System.out.println("Enter Command");
    		scanner=new Scanner(System.in);
    		String cr=scanner.next();
    		if(cr.length()!=1){
    			System.out.println("Invalid Command");
    			System.out.println("I'm leaving");
    			System.exit(1);
    		}
    		else{
    			char c=cr.charAt(0);
    			switch(c){
    			case 'f':
    				System.out.println("You have selected to exit");
    				System.exit(1);
    				
    			case 'b':
    				displayBooks();
    				break;
    				
    			case 'u':
    				displayUsers();
    				break;
    				
    			case 'i':
    				System.out.println("Enter Book No");
    				bookNo=scanner.nextInt();
    				
    				System.out.println("Enter User No");
    				userNo=scanner.nextInt();
    				
    				if(bookNo>booksLength){
    					System.out.println("You have  Entered Wrong Book No");
    					break;
    				}
    				
    				if(userNo>usersLength){
    					System.out.println("You have Wrong book");
    					break;
    				}
    				
    				
    				selectedI(books.get(bookNo-1),users.get(userNo-1));
    				
    				break;
    				
    			case 'r':
    				
    				System.out.println("Enter User No");
    				userNo=scanner.nextInt();
    				
    				System.out.println("Enter Book No");
    				bookNo=scanner.nextInt();
    				
    				if(bookNo>booksLength){
    					System.out.println("You have Entered Wrong Book No");
    					break;
    				}
    				
    				if(userNo>usersLength){
    					System.out.println("You have Entered Wrong User No");
    					break;
    				}
    				
    				
    				returnBookI(books.get(bookNo-1),users.get(userNo-1));
    				
    			
    				break;
    				
    			default:
    				System.out.println("Wrong Command");
    				break;
    			}
    			
    		}
    	}
    		
    	
    }    
    
    private void returnBookI(Book bk,User ur){
		if(bk.getBorrower()==ur){
			try{
				ur.removeBook(bk);
				bk.setBorrower(null);
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		else{
			System.out.println(ur.toString()+" has not borrowed " +bk.toString()+ " book");
		}
	}
    
    public void displayBooks(){
    	
    	String s="BookNo";
    	String s1="Author Name";
    	String s2="Title Name";
    	String s3="Borrower";
    	System.out.print(getString(s));
    	System.out.print(getString(s1));
    	System.out.print(getString(s2));
    	System.out.print(getString(s3));
    	System.out.println();
    	System.out.println();
    	Iterator iterator=books.iterator();
    	int i=0;
    	Book bbb=null;
    	User uuu=null;
    	while(iterator.hasNext()){
    		i++;
    		bbb=(Book)iterator.next();
    		System.out.print(getString(i+""));
    		System.out.print(getString(bbb.getAuthorName()));
        	System.out.print(getString(bbb.getTitleName()));
        	uuu=bbb.getBorrower();
        	if(uuu==null)
        		System.out.print(getString(null));
        	else
        		System.out.print(getString(uuu.toString()));
        	System.out.println();
    	}
    }
    
    public void displayUsers(){
    	
    	String s="User No";
    	String s4="User No";
    	String s1="Book1";
    	String s2="Book2";
    	String s3="Book3";
    	System.out.print(getString(s));
    	System.out.print(getString(s4));
    	System.out.print(getString(s1));
    	System.out.print(getString(s2));
    	System.out.print(getString(s3));
    	System.out.println();
    	System.out.println();
    	Iterator iterator=users.iterator();
    	int i=0;
    	Book bbb=null;
    	User uuu=null;
    	while(iterator.hasNext()){
    		i++;
    		uuu=(User)iterator.next();
    		System.out.print(getString(i+""));
    		System.out.print(getString(uuu.toString()));
    		
    		bbb=uuu.getBook(0);
    		
    		if(bbb!=null)
    			System.out.print(getString(bbb.toString()));
    		else
    			System.out.print(getString(null));
    		
    		bbb=uuu.getBook(1);

    		if(bbb!=null)
    			System.out.print(getString(bbb.toString()));
    		else
    			System.out.print(getString(null));
    		
    		bbb=uuu.getBook(2);
    		
    		if(bbb!=null)
    			System.out.print(getString(bbb.toString()));
    		else
    			System.out.print(getString(null));
    		
        	
        	
        	System.out.println();
    	}
    }
        
    private String getString(String str){
    	if(str==null)
    		str="";
    	int pp=stringLength-str.length();
    	while(pp>0){
    		str+=" ";
    		pp--;
    	}
    	
    	return str;
    }
    
    public void selectedI(Book bk,User ur){
    	
    	if(bk.getBorrower()==null){
			try{
				ur.addBook(bk);
				bk.setBorrower(ur);
			}catch(Exception ex){
				String ss="Failure: "+ur.toString()+" has already 3 books in his account so "+bk.toString()+" can't be borrowed\r\n";
				try{
					fileWriter.write(ss);
					fileWriter.flush();
				}catch(Exception ep){
					
				}
			}
		}
		else{
			String ss="Failure: "+bk.toString()+" can't be borrowed by "+ur.toString()+"\r\n";
			ss+="It is already borrowed by some other user" +bk.getBorrower().toString()+"\r\n";
			try{
				fileWriter.write(ss);
				fileWriter.flush();
			}catch(Exception ex){
				
			}
			
			
			System.out.println("Can't be borrowed");
		}

    	
    }
    
    public static void main(String[] gcs){
    	if(gcs.length!=2){
    		System.out.println("Usage:java MainMenu inputfilename outputfilename");
    		System.exit(1);
    	}
    	
    	GetUsersAndBooks ub=new GetUsersAndBooks(gcs[0]);//"D:/input.txt");
    	List<Book> b=ub.books;
    	List<User> u=ub.users;
    	MainMenu app=new MainMenu(b,u,gcs[1]);//"D:/output.txt");    	
    }

}
