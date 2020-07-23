import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class Reverse {
	BufferedImage original,mixed;
	public Reverse(BufferedImage mixed,BufferedImage original){
		
		this.original=original;
		this.mixed=mixed;
		find();
	}
	
	private void find(){
		FindFeatures f=new FindFeatures(original);		
		int blockPixels[][][]=f.getBlockWithHighSTD(8,8);		
		
		DCTCoefficients coeffinder=new DCTCoefficients(blockPixels);
		double[][][] dctCoefficients=coeffinder.getDCTCoefficients();		
		int length=dctCoefficients.length;
		
		double[] blockCoefficients=new double[length];
		
		for(int i=0;i<length;i++){
			blockCoefficients[i]=dctCoefficients[i][0][0];
		}
		
		double[] muImageBlocks=getNormalizedValues(blockCoefficients);
		double muImage=getImgGreyValue(muImageBlocks);
		double[] sigmaImageBlocks=GetVariances2.getVariances(dctCoefficients);
		
		System.out.println(" muimage is "+muImage);
		SoberOperator sober=new SoberOperator(original);
		sober.soberValues();
		BufferedImage edgesImage = sober.getEdgesImage();
		new DisplayFrame(edgesImage);
		FindFeatures fEdges=new FindFeatures(edgesImage);		
		int blockEdgePixels[][][]=fEdges.getBlockWithHighSTD(8,8);
		
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
		
		f=new FindFeatures(mixed);
		int[][][] mixedPixels=f.getBlockWithHighSTD(8, 8);
		
		for(int i=0;i<blockPixels.length;i++){
			for(int j=0;j<8;j++){
				for(int k=0;k<8;k++){					
					
					if(blockEdgePixels[i][j][k]!=0){
						
						System.out.print(blockEdgePixels[i][j][k]+" ");
						watermark[i][j][k]=(int)(((mixedPixels[i][j][k] -(int)(.98*blockPixels[i][j][k])))/.07);
					}
					else
						watermark[i][j][k]=(int)(((mixedPixels[i][j][k] -(int)(alphaValues[i][j][k]*blockPixels[i][j][k])))/betaValues[i][j][k]);
						
					
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
