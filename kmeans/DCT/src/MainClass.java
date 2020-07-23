import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class MainClass {
	
	int width=0,height=0;
	public static void main(String[] gcs){
		new MainClass();
	}
	
	public MainClass(){
		
		
		JFileChooser fileChooser=new JFileChooser();
  		
  		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
  		int result=fileChooser.showOpenDialog(null);
  		if(result==JFileChooser.CANCEL_OPTION)
  					return;
  				
  				
  		String path=(fileChooser.getSelectedFile().getAbsolutePath());
  		
  		BufferedImage img = null;
		try{
		    img = ImageIO.read(new File(path));
		}
		catch (IOException e) {
		}
		
		img=GetGreyImageData.getConvertedGrayscaleImage(img);
		
		new DisplayFrame(img);
		FindFeatures f=new FindFeatures(img);		
		int blockPixels[][][]=f.getBlockWithHighSTD(8,8);		
		
		DCTCoefficients coeffinder=new DCTCoefficients(blockPixels);
		double[][][] dctCoefficients=coeffinder.getDCTCoefficients();		
		
		
			/*for(int j=0;j<8;j++){
				for(int k=0;k<8;k++){
					System.out.print("\t"+dctCoefficients[10][j][k]);
					
				}
				System.out.print("\n");
			}*/
		
		
		int length=dctCoefficients.length;
		
		double[] blockCoefficients=new double[length];
		
		for(int i=0;i<length;i++){
			blockCoefficients[i]=dctCoefficients[i][0][0];
		}
		
		double[] muImageBlocks=getNormalizedValues(blockCoefficients);
		
		//double[] muImageBlocks=NormalizedGreyImageValues.getNormalizedGreyValues(dctCoefficients);
		double muImage=getImgGreyValue(muImageBlocks);
		//System.out.println("mu images is "+muImage);
		double[] sigmaImageBlocks=GetVariances2.getVariances(dctCoefficients);
		
		System.out.println(" muimage is "+muImage);
		//System.out.println(" muimage is "+muImage);
	
		/*for(int i=0;i<100;i++){
			System.out.println(sigmaImageBlocks[i]+" hi  ");
		}*/
		
		/*CannyEdgeDetector detector = new CannyEdgeDetector();
		//adjust its parameters as desired 
		detector.setLowThreshold(0.75f); 
		detector.setHighThreshold(1f); 
		//apply it to an image 
		detector.setSourceImage(img); 
		detector.process(); 
		BufferedImage edgesImage = detector.getEdgesImage();
		//new DisplayFrame(edgesImage);
		edgesImage=GetGreyImageData.getConvertedGrayscaleImage(edgesImage);
		new DisplayFrame(edgesImage);
		FindFeatures fEdges=new FindFeatures(edgesImage);
		*/
		
		SoberOperator sober=new SoberOperator(img);
		sober.soberValues();
		BufferedImage edgesImage = sober.getEdgesImage();
		new DisplayFrame(edgesImage);
		FindFeatures fEdges=new FindFeatures(edgesImage);		
		int blockEdgePixels[][][]=fEdges.getBlockWithHighSTD(8,8);
		
		fileChooser=new JFileChooser();
  		
  		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
  		result=fileChooser.showOpenDialog(null);
  		if(result==JFileChooser.CANCEL_OPTION)
  					return;
  				
  				
  		path=(fileChooser.getSelectedFile().getAbsolutePath());
  		
  		BufferedImage logo = null;
		try{
		    logo = ImageIO.read(new File(path));
		}
		catch (IOException e) {
		}
		
		
		System.out.println("before type is "+logo.getType());
		logo=Scale.getScaledImage(0, 0,1024,1024, logo);
		
		System.out.println("after type is "+logo.getType());
		
		logo=GetGreyImageData.getConvertedGrayscaleImage(logo);
		new DisplayFrame(logo);
		FindFeatures fLogo=new FindFeatures(logo);		
		int logoBlockPixels[][][]=fLogo.getBlockWithHighSTD(8,8);
		
		/*double[] betaLogoBlockValue=NormalizedGreyImageValues.getNormalizedGreyValues(blockPixels);
		double betaBlockValue=getImgGreyValue(betaLogoBlockValue);*/
		//DCTCoefficients logoCoeffinder=new DCTCoefficients(logoBlockPixels);
		//double[][][] logoDctCoefficients=logoCoeffinder.getDCTCoefficients();
		
		//double[] logoVariances=GetVariances.getVariances(dctCoefficients);/*/
		
		double[][][] alphaValues=new double[blockPixels.length][8][8]; 
		double[][][] betaValues=new double[blockPixels.length][8][8];
		
		for(int i=0;i<blockPixels.length;i++){
			for(int j=0;j<8;j++){
				for(int k=0;k<8;k++){ 
					double no=(Math.exp(-1*(muImageBlocks[i]-muImage)*(muImageBlocks[i]-muImage)))+0.0d;					
					alphaValues[i][j][k]=sigmaImageBlocks[i]*no;
					if(sigmaImageBlocks[i]!=0)
						betaValues[i][j][k]=(1/sigmaImageBlocks[i])*(1-no);
					else
						betaValues[i][j][k]=0;
				}
			}
		}
		
		System.out.print("some value in the middle is "+alphaValues[4][3][2]);
		double alphaMin,alphaMax,betaMin,betaMax;
		
		
		alphaMin=alphaMax=alphaValues[0][0][0];
		betaMin=betaMax=betaValues[0][0][0];
		
		for(int i=0;i<blockPixels.length;i++){
			for(int j=0;j<8;j++){
				for(int k=0;k<8;k++){
					
					if(alphaValues[i][j][k]>alphaMax)
						alphaMax=alphaValues[i][j][k];
					if(alphaValues[i][j][k]<alphaMin)
						alphaMin=alphaValues[i][j][k];
					
					if(betaValues[i][j][k]>betaMax)
						betaMax=betaValues[i][j][k];
					if(betaValues[i][j][k]<betaMin)
						betaMin=betaValues[i][j][k];
						
					
				}
			}
		}
		
		System.out.println("alpha max "+alphaMax+" and min "+alphaMin);
		System.out.println("beta max "+betaMax+" and min "+betaMin);
		for(int i=0;i<blockPixels.length;i++){
			for(int j=0;j<8;j++){
				for(int k=0;k<8;k++){					
					alphaValues[i][j][k]=((alphaValues[i][j][k]-alphaMin)/(alphaMax-alphaMin))*(0.03)+0.95;	
					betaValues[i][j][k]=((betaValues[i][j][k]-betaMin)/(betaMax-betaMin))*(0.1)+0.07;
				}
			}
		}
		
		int kkk=0;
		
		int[][][] watermark=new int[blockPixels.length][8][8];
		
		for(int i=0;i<blockPixels.length;i++){
			for(int j=0;j<8;j++){
				for(int k=0;k<8;k++){					
					
					if(blockEdgePixels[i][j][k]!=0){
						//kkk++;
						System.out.print(blockEdgePixels[i][j][k]+" ");
						watermark[i][j][k]=(int)(.98*blockPixels[i][j][k])+(int)(.07*logoBlockPixels[i][j][k]);
					}
					else
						watermark[i][j][k]=(int)(alphaValues[i][j][k]*blockPixels[i][j][k])+(int)(betaValues[i][j][k]*logoBlockPixels[i][j][k]);
						
					
				}
			}
			System.out.println();
		}
		
		
		int[] watermarkImage=new int[1024*1024];
		
		int ip=0;
		int jp=0;
		int kp=0;
		try{
		
		for(ip=0;ip<blockPixels.length;ip++){			
			for(jp=0;jp<8;jp++){
				for(kp=0;kp<8;kp++){					
					watermarkImage[(ip/128)*8*1024+(ip%128)*8+jp*1024+kp]=watermark[ip][jp][kp];
				}
			}
		}
		}
		catch(Exception ex){
			System.out.println(" i is "+ip+" j is "+jp+" k is "+kp+" and "+((ip*8*1024)/128+(ip%128)*8+jp*1024+kp));
			System.exit(0);
		}
		
		byte[] bytes=new byte[1024*1024];
		
		for(int i=0;i<bytes.length;i++){
			
			bytes[i]=(byte)watermarkImage[i];
		}
		
		BufferedImage finalImage=null;
		finalImage = new BufferedImage(1024,1024, BufferedImage.TYPE_BYTE_GRAY);
		finalImage.getWritableTile(0, 0).setDataElements(0, 0,1024,1024, bytes);
		System.out.println("kkk value is "+kkk);
		new DisplayFrame(finalImage);
		
		String conform=                                      
	  			JOptionPane.showInputDialog( "Do you want to save the file yes or no" );
	  	System.out.println("conform is "+conform);
	  	try{
	  	if(conform.equals("yes")){
	  		  				
	  			System.out.println("inside if");
  				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
  				int result2=fileChooser.showSaveDialog(null);
  				System.out.println("after save dilalog");
  				if(result2==JFileChooser.CANCEL_OPTION)
  					return;
  				
  				width=height=1024;
  				FileOutputStream fout=new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath()+"browse");
				fout.write(bytes);
				fout.close();
  				BufferedImage coverImage=new BufferedImage(width,height, BufferedImage.TYPE_BYTE_GRAY);	  					     
  				coverImage.getWritableTile(0, 0).setDataElements(0, 0, width, height, bytes);
  				//middlePanel.updateImage(coverImage); 
  				
  			    File outputfile = new File(fileChooser.getSelectedFile().getAbsolutePath());  			    
  			    ImageIO.write(coverImage, "jpg", outputfile);
  			    
	  		}
		conform=                                      
	  			JOptionPane.showInputDialog( "Do you want to browse the watermark yes or no" );
	  			
		if(conform.equals("yes")){
			
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				result=fileChooser.showOpenDialog(null);
				
				if(result==JFileChooser.CANCEL_OPTION)
					return;				
				
			FileInputStream fin=new FileInputStream(fileChooser.getSelectedFile());
			int offset=0;
			byte[] data = new byte[1024*1024];
			int bytesRead = 0;
			while (offset < 1024*0124) {
					
				bytesRead = fin.read(data, offset, data.length-offset);
				if (bytesRead == -1) break;
				offset += bytesRead;
			}
			fin.close();
			
			finalImage = new BufferedImage(1024,1024, BufferedImage.TYPE_BYTE_GRAY);
			finalImage.getWritableTile(0, 0).setDataElements(0, 0,1024,1024, data);
			
		}
		
		
		}catch(Exception ex){
				ex.printStackTrace();
			}
		
		new Reverse(finalImage,img);
	}
	
	private double getImgGreyValue(double[] normals){
		
		double sum=0;
		for(double x:normals)
			sum+=x;
		return sum/(normals.length) ;
	}
	
	private double[] getNormalizedValues(double[] values){
		
		double max=values[0];
		
		for(int i=0;i<values.length;i++){
			if(max<values[i])
				max=values[i];
			
		}
		
		for(int i=0;i<values.length;i++){
			
				values[i]=values[i]/max;
			
		}
		
		return values;
		
	}
	
	

}
