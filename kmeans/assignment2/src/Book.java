
public class Book implements Comparable {
	private String titleName;
	private String authorName;
	private User borrower;
	
	public Book(String titleName,String authorName){
		this.titleName=titleName;
		this.authorName=authorName;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public User getBorrower() {
		return borrower;
	}
	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Book bk=(Book)o;
		return this.getAuthorName().compareToIgnoreCase(bk.getAuthorName());
	}
	
	public boolean equalsTo(Book o){
		Book bk=(Book)o;
		return (this.getAuthorName().equals(bk.getAuthorName()) && 
				this.getTitleName().equals(bk.getTitleName()));
	}
	
	public String toString(){
		return authorName+" "+titleName;
	}
	
}
