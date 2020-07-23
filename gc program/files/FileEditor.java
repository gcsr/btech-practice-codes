import java.io.*;

public class FileEditor
{
	RandomAccessFile file;
	public FileEditor(File fileName) throws FileNotFoundException
	{
		file=new RandomAccessFile(fileName,"rw");
	}
	public void closeFile()throws IOException
	{
		if(file!=null)
		file.close();
	}
	
	public RandomAccessAccountRecord getRecord(int accountNumber)
	throws IllegalArgumentException,NumberFormatException,IOException
	{
		RandomAccessAccountRecord record=new RandomAccessAccountRecord();
		
		if(accountNumber<1||accountNumber>100)
		throw new IllegalArgumentException("Out of Range");
		
		file.seek((accountNumber-1)*RandomAccessAccountRecord.SIZE);
		
		record.read(file);
		
		return record;
		
		
	}
	
	public void updateRecord(int accountNumber,String firstName,String lastName,double balance)
	throws IllegalArgumentException,IOException
	{
		System.out.println("update record");
		RandomAccessAccountRecord record=getRecord(accountNumber);
		
		if(accountNumber==0)
		throw new IllegalArgumentException("Account Doesn't exist");
		
		file.seek((accountNumber-1)*RandomAccessAccountRecord.SIZE);
		
		record=new RandomAccessAccountRecord(accountNumber,firstName,lastName,balance);
		
		record.write(file);
	}
	
	public void newRecord(int accountNumber,String firstName,String lastName,double balance)
	throws IllegalArgumentException,IOException,AccountCreated
	{
		RandomAccessAccountRecord record=getRecord(accountNumber);
		
		if(record.getAccount()!=0)
		throw new IllegalArgumentException("Account Already exists");
		
		file.seek((accountNumber-1)*RandomAccessAccountRecord.SIZE);
		
		record=new RandomAccessAccountRecord(accountNumber,firstName,lastName,balance);
		
		record.write(file);
		throw new AccountCreated("successful");
		
	}
	
	public void deleteRecord(int accountNumber)
	throws IllegalArgumentException,NumberFormatException,IOException
	{
		RandomAccessAccountRecord record=getRecord(accountNumber);
		
		if(record.getAccount()==0)
		throw new IllegalArgumentException("Account doesn't exist");
		
		file.seek((accountNumber-1)*RandomAccessAccountRecord.SIZE);
		
		record=new RandomAccessAccountRecord();
		
		
		record.write(file);
		
		
		
		
	}

}