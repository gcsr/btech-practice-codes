import java.util.ArrayList;


public class User implements Comparable {
	
	String userName;
	ArrayList<Book> borrowedBooks=new ArrayList<Book>();
	public User(){
		
	}
	public User(String userName){
		this.userName=userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ArrayList<Book> getBorrowedBooks() {
		return borrowedBooks;
	}
	public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
	
	public void addBook(Book b)throws Exception{
		if(borrowedBooks.size()>2){
			throw new Exception("User has already three books");
			//System.out.println(this.userName+" has alredy 3 books borrowed and is asking for new one");
			//System.out.println("Request Denied");
			//return;
		}
		
		b.setBorrower(this);
		borrowedBooks.add(b);
	}
	
	public void removeBook(Book b){
		if(borrowedBooks.size()<1){
			System.out.println(this.userName+" has not borrowed a single book and is asking remove one");
			System.out.println("Request Denied");
			return;
		}
		
		if(borrowedBooks.contains(b)){
			borrowedBooks.remove(b);
		}else{
			System.out.println(this.userName+" has not borrowed this book and is asking remove one");
			System.out.println("Request Denied");
		}
	}
	
	public Book getBook(int i){
		if(borrowedBooks.size()>i)
			return borrowedBooks.get(i);
		return null;
		
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		User us=(User)o;
		return this.userName.compareToIgnoreCase(us.getUserName());

	}
	
	public boolean equalsTo(User o){
		User us=(User)o;
		return (this.userName.equals(us.getUserName()));
	}
	
	public String toString(){
		return userName;
	}
	
}
