import java.util.Scanner;


public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("enter password");
		Scanner scanner=new Scanner(System.in);		
		String password=scanner.next();
		
		String binaryPassword=PassToBinary.getBinary(password);
		
		/*JFileChooser fileChooser;
		
		fileChooser=new JFileChooser();
  		
  				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
  				int result=fileChooser.showOpenDialog(null);
  				
  				if(result==JFileChooser.CANCEL_OPTION)
  					return;
  				
  				
  		String fileName=fileChooser.getSelectedFile().getAbsolutePath();*/
		
		String fileName="D:/dwt/BGR3.JPG";
		
		

	}
	
	public void getPixels(){
		
	}
	
	

}
