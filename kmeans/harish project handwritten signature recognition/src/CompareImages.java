import java.awt.Point;
import java.awt.image.BufferedImage;


public class CompareImages {
	public static CompareObjct compare(BufferedImage image1,BufferedImage image2){

		BufferedImage temp1;
		BufferedImage temp2;
		temp1=image1;
		temp2=image2;
		
	/*	temp1=ImageOperations.getGrayScaleImage(image1);
		temp1=ImageOperations.getBinaryImage(image1);		
		temp1=ImageOperations.getResizedImage(image1);
		temp1=ImageOperations.getBoundingBox(image1);
		temp1=ImageOperations.getDilatedImage(image1);
		temp1=ImageOperations.getThinnedImage(image1);
		
		temp2=ImageOperations.getGrayScaleImage(image2);
		temp2=ImageOperations.getBinaryImage(image2);		
		temp2=ImageOperations.getResizedImage(image2);
		temp2=ImageOperations.getBoundingBox(image2);
		temp2=ImageOperations.getDilatedImage(image2);
		temp2=ImageOperations.getThinnedImage(image2);*/
		
		int[] temp1His=ImageOperations.getMaximumHistogram(temp1);
		int[] temp2His=ImageOperations.getMaximumHistogram(temp2);
		
		Point[] temp1Mass=ImageOperations.getCenterOfMass(temp1);
		Point[] temp2Mass=ImageOperations.getCenterOfMass(temp2);
				
		double[] temp1Tri=ImageOperations.getTrisurface(temp1);
		double[] temp2Tri=ImageOperations.getTrisurface(temp2);
		
		Point[] temp1Sex=ImageOperations.getSixFoldCenter(temp1);
		Point[] temp2Sex=ImageOperations.getSixFoldCenter(temp2);
		
		double temp1Area=ImageOperations.getNormalizedArea(temp1);
		double temp2Area=ImageOperations.getNormalizedArea(temp2);
		double matching=0;
		double diff=0;
		for(int i=0;i<temp1His.length;i++){
			diff+=(temp1His[i]-temp2His[i])*(temp1His[i]-temp2His[i]);
		}
		
		if(diff<400)
			matching+=12;
		
		diff=0;
		
		for(int i=0;i<temp1Mass.length;i++){
			diff+=getDiff(temp1Mass[i],temp2Mass[i]);
		}
		
		if(diff<400)
			matching+=13;
		
		for(int i=0;i<temp1Tri.length;i++){
			diff+=(temp1Tri[i]-temp2Tri[i])*(temp1Tri[i]-temp2Tri[i]);
		}
		
		if(diff<600)
			matching+=25;
		
		diff=0;
		
		for(int i=0;i<temp1Sex.length;i++){
			diff+=getDiff(temp1Sex[i],temp2Sex[i]);
		}
		
		if(diff<600)
			matching+=40;
	
		diff=0;
		if((temp1Area-temp2Area)*(temp1Area-temp2Area)<200)
			matching+=10;
		
		CompareObjct oct=new CompareObjct();
		oct.overallPercentage=matching;
		
		return oct;
	}
	
	private static double getDiff(Point p1,Point p2){
		return (p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y);
	}
	
}
