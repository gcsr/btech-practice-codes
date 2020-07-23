 import java.io.*;

public class FileEditor
{
	int tidli,tpongal,tuthappam,tchapathi,trice,tlemRice,tdosa,tvoda,textraRS;
	
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
	

	public RandomAccessAccountRecord getRecord(int recordNumber)
	throws IllegalArgumentException,NumberFormatException,IOException
	{
		RandomAccessAccountRecord record=new RandomAccessAccountRecord();
		
		if(recordNumber<1||recordNumber>100)
		throw new IllegalArgumentException("Out of Range");
		
		file.seek((recordNumber-1)*RandomAccessAccountRecord.SIZE);
		
		record.read(file);
		
		return record;
		
		
	}
	
	
	public RandomAccessAccountRecord getRecord(String date)
	throws IllegalArgumentException,NumberFormatException,IOException
	{
		
		
		int recordNumber=1;
		System.out.println("getRecorddate");
		
		RandomAccessAccountRecord record=getRecord(recordNumber);
		
		while(!record.getDate().equals(date)&&record.getRecordNumber()!=0)
		{	
		    recordNumber++;
		    record=getRecord(recordNumber);
		
		}
		
		
		if(record.getRecordNumber()==0)
		throw new IllegalArgumentException("no record");
		
		file.seek((recordNumber-1)*RandomAccessAccountRecord.SIZE);
		
		record.read(file);
		
		return record;
		
		
	}
	
	public void updateRecord(int rno,String dt,int idli,int pongal,int uthappam,int dosa,int voda,
	int chapathi,int lemRice,int rice,int extraRS,String extraItems)
	throws IllegalArgumentException,IOException,AccountCreated
	{
		System.out.println("update record");
		RandomAccessAccountRecord record;			
		
		file.seek((rno-1)*RandomAccessAccountRecord.SIZE);
		
		record=new RandomAccessAccountRecord(rno,dt,idli,pongal,uthappam,dosa,voda,chapathi,lemRice,rice,extraRS,extraItems);
		
		record.write(file);
		
		
		throw new AccountCreated("successful");
	}
	
	public void newRecord(int rno,String dt,int idli,int pongal,int uthappam,int dosa,int voda,
	int chapathi,int lemRice,int rice,int extraRS,String extraItems)
	throws IllegalArgumentException,IOException,AccountCreated
	{
		System.out.println(rno);
		RandomAccessAccountRecord record=getRecord(1);
		
		int pp=1;
		
		while(record.getRecordNumber()!=0&&!record.getDate().equals(dt))
		{
			pp++;
			record=getRecord(pp);
		}
		
		System.out.println(pp);

		
		if(record.getRecordNumber()!=0)
		throw new IllegalArgumentException("already created");
		
		file.seek((pp-1)*RandomAccessAccountRecord.SIZE);
		
		record=new RandomAccessAccountRecord(pp,dt,idli,pongal,uthappam,dosa,voda,chapathi,lemRice,rice,extraRS,extraItems);
		
		record.write(file);
		throw new AccountCreated("successful");
		
	}
	
	public void deleteRecord(int recordNumber)
	throws IllegalArgumentException,NumberFormatException,IOException,AccountCreated
	{
		RandomAccessAccountRecord record;
				
		file.seek((recordNumber-1)*RandomAccessAccountRecord.SIZE);
		
		record=new RandomAccessAccountRecord();
		
		
		record.write(file);
		
		
		throw new AccountCreated("record deleted");
		
	}
	
	public RandomAccessAccountRecord sum()
		throws IllegalArgumentException,NumberFormatException,IOException
	{
			int r=1;
			RandomAccessAccountRecord record=getRecord(r);	
			while(record.getRecordNumber()!=0)
				{
					tidli+=record.getIdli();
					tpongal+=record.getPongal();
					tuthappam+=record.getUthappam();
					tdosa+=record.getDosa();
					tvoda+=record.getVoda();
					tchapathi+=record.getChapathi();
					tlemRice+=record.getLemRice();
					trice+=record.getRice();
					textraRS+=record.getExtraRS();
					r++;
					record=getRecord(r);
		
				}
				
			RandomAccessAccountRecord rec=new RandomAccessAccountRecord(1,null,tidli,tpongal,tuthappam,tdosa,tvoda,tchapathi,tlemRice,trice,textraRS,null);
			
			return rec;
	
	}
	
}