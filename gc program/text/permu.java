public class permu
{
	public static void main(String[] gcs)
	{
		    int n=Integer.parseInt(gcs[0]);

		    int length=6;//Integer.parseInt(gcs[1]);
		    char first='a';
		    char last='z';
			StringBuilder r=new StringBuilder();
			for(int i=0;i<length;i++)
			{
				System.out.println((int)first);
				char c=(char)(first+n%(last-first+1));
				r.insert(0,c);
				n=n/(last-first+1);
			}
		    System.out.println(r);
	}
}