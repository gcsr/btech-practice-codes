import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class GeneticAlgo {
	static final public int NOOFCHROMOSOMES=100;
	
	int firstDiff=0;
	
	int secondDiff=0;
	
	String temp="";
	int[] pixelPositions;
	int[] pixels;
	int[][] chromosomes;
	//PixelObject[][] chromosomes;
	int pixelsLength=0;
	int chromosomeLength=0;
	String[] password;
	
	String oneBlueString="";
	String oneRedString="";
	String oneGreenString="";
	
	String twoBlueString="";
	String twoRedString="";
	String twoGreenString="";
	
	
	int oneRed=0;
	int oneBlue=0;
	int oneGreen=0;
	
	int twoRed=0;
	int twoBlue=0;
	int twoGreen=0;

	class PixelObject implements Comparable{
		int No;
		int blue;
		int green;
		int red;
		String blueString="";
		String greenString="";
		String redString="";
		@Override
		public int compareTo(Object twow) {
			
			PixelObject two=(PixelObject)twow;
			oneBlueString=blueString;
			oneRedString=redString;
			oneGreenString=greenString;
			//oneBlueString.s
			//System.out.println("temps is "+temp);
			//System.out.println(oneBlueString+" "+oneRedString+" "+oneGreenString);
			oneBlueString=oneBlueString.substring(0,5)+temp.substring(0,3);
			oneRedString=oneRedString.substring(0,6)+temp.substring(6,8);
			oneGreenString=oneGreenString.substring(0,5)+temp.substring(3,6);
			//System.out.println(oneBlueString+" "+oneRedString+" "+oneGreenString);
			twoBlueString=two.blueString;
			twoRedString=two.redString;
			twoGreenString=two.greenString;
			
			//System.out.println(twoBlueString+" "+twoRedString+" "+twoGreenString);
			
			twoBlueString=twoBlueString.substring(0,5)+temp.substring(0,3);
			twoRedString=twoRedString.substring(0,6)+temp.substring(6,8);
			twoGreenString=twoGreenString.substring(0,5)+temp.substring(3,6);
			//System.out.println(twoBlueString+" "+twoRedString+" "+twoGreenString);
			
			
			oneRed=Integer.parseInt(oneRedString,2);
			oneBlue=Integer.parseInt(oneBlueString,2);
			oneGreen=Integer.parseInt(oneGreenString,2);
			
			//System.out.println(oneBlue+" "+oneRed+" "+oneGreen);
			
			
			twoRed=Integer.parseInt(twoRedString,2);
			twoBlue=Integer.parseInt(twoBlueString,2);
			twoGreen=Integer.parseInt(twoGreenString,2);
			
			//System.out.println(twoBlue+" "+twoRed+" "+twoGreen);
			
			firstDiff=(red-oneRed)*(red-oneRed)+
					(blue-oneBlue)*(blue-oneBlue)+
					(green-oneGreen)*(green-oneGreen);
			
			secondDiff=(two.red-twoRed)*(two.red-twoRed)+
					(two.blue-twoBlue)*(two.blue-twoBlue)+
					(two.green-twoGreen)*(two.green-twoGreen);
			//System.out.println(firstDiff+", "+secondDiff);
			
			//System.exit(1);
			
			return firstDiff-secondDiff;
			/*if(firstDiff>secondDiff)	
				return 1;
			else
			return -1;*/
		
		}
		
		
	}
	
	PixelObject[] pObjects;
	
	
	public GeneticAlgo(int[] pixels,String[] password){
		this.pixels=pixels;
		this.password=password;
		chromosomeLength=password.length;
		pixelsLength=pixels.length/3;
		chromosomes=new int[chromosomeLength][chromosomeLength];
		//chromosomes=new PixelObject[chromosomeLength][chromosomeLength];
		pObjects=new PixelObject[pixelsLength];		
		for(int i=0;i<pixelsLength;i++){
			pObjects[i]=new PixelObject();
			pObjects[i].No=i;
			pObjects[i].blue=pixels[i*3];
			for (int j = 0; j < 8; j++){
				pObjects[i].blueString+=((pixels[i*3] & 128) == 0 ? 0 : 1);
		        pixels[i*3] <<= 1;
		     }
			
			pObjects[i].green=pixels[i*3+1];
			
			for (int j = 0; j < 8; j++){
				pObjects[i].greenString+=((pixels[i*3+1] & 128) == 0 ? 0 : 1);
		        pixels[i*3+1] <<= 1;
		     }
			
			pObjects[i].red=pixels[i*3+2];
			
			for (int j = 0; j < 8; j++){
				pObjects[i].redString+=((pixels[i*3+2] & 128) == 0 ? 0 : 1);
		        pixels[i*3+2] <<= 1;
		     }
			
			
			
		}
		
		System.out.println("calling assignChromosomes");
		
		assignChromosomes();
		
		for(int i=0;i<chromosomeLength;i++){
			for(int j=0;j<chromosomeLength;j++){
				System.out.print(chromosomes[i][j]+ "\t");
			}
			
			System.out.println();
		}
	}
	
	

	
	private void selectTargets(){
		
	}
	
	private void findChromosomes(){
		
		
	}
	
	private void assignChromosomes(){		
		
		for(int i=0;i<chromosomeLength;i++){
			chromosomes[i]=getChromosome(password[i]);
		}
		
			//chromosomes[0]=getChromosome(password[0]);
		filterChromosomes();
		
	}
	
	public void start(){
		
	}
	
	
	public void noName(){
		
		
	}
	
	
	
	public void getPixels(){
		
	}
	
	
	public void initialize(){
		
	}
	
	
	public void mutate(){
		
	}
	
	
	public void crossover(){
		
	}
	
	
	private boolean checkConstraint(){
		
		return false;
	}
	
	private int[] getChromosome(String ch){
	//PixelObject[] getChromosome(String ch){		
		
		temp=ch;
		PixelObject[] tempObjects=pObjects;
		
		Arrays.sort(tempObjects);
		
		/*for(int i=0;i<pixelsLength-1;i++){
			for(int j=i+1;j<pixelsLength;j++){
				if(firstGreater(tempObjects[i],tempObjects[j])){
					PixelObject tempTemp=tempObjects[i];
					tempObjects[i]=tempObjects[j];
					tempObjects[j]=tempTemp;
				}
			}
			
		}*/
		
		int[] returnValue=new int[chromosomeLength];
		//PixelObject[] returnValue=new PixelObject[chromosomeLength];
		for(int i=0;i<chromosomeLength;i++){
			//returnValue[i]=tempObjects[i];
			returnValue[i]=tempObjects[i].No;
		}
		
		System.out.println("done");
		
		return returnValue;
		
	}
	
	private void filterChromosomes(){
		for(int i=0;i<chromosomeLength;i++){
			for(int j=0;j<chromosomeLength;j++){
				if(checkNotAvailable(i,j)){
					putnegatives(i,j);
					break;
				}
			}
		}
	}
	
	private void putnegatives(int i,int j){
		for(int k=j+1;k<chromosomeLength;k++){
			chromosomes[i][k]=-500;
		}
	}
	
	
	private boolean checkNotAvailable(int i,int j){
		return false;
	}

}
