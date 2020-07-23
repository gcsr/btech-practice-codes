import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class GeneticAlgo1 {
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
	
	int oneRed=0;
	int oneBlue=0;
	int oneGreen=0;
	
	int twoRed=0;
	int twoBlue=0;
	int twoGreen=0;
	
	int first=0;
	int middle=0;
	int last=0;

	class PixelObject implements Comparable{
		int No;
		int blue;
		int green;
		int red;
		public int compareTo(Object twow) {
			
			PixelObject two=(PixelObject)twow;
			
			first=Integer.parseInt(temp.substring(0,3));
			middle=Integer.parseInt(temp.substring(3,6));;
			last=Integer.parseInt(temp.substring(6,8));;
			oneBlue=blue&248;			
			oneGreen=green&248;
			oneRed=red&252;
			
			oneBlue+=first;
			oneGreen+=middle;
			oneRed+=last;
			//System.out.println(oneBlue+" "+oneRed+" "+oneGreen);
			
			
			twoRed=two.red&252;
			twoBlue=two.blue&248;
			twoGreen=two.green&248;
			
			twoBlue+=first;
			twoGreen+=middle;
			twoRed+=last;
			
			
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
	
	
	public GeneticAlgo1(int[] pixels,String[] password){
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
			pObjects[i].green=pixels[i*3+1];			
			pObjects[i].red=pixels[i*3+2];		
			
		}
		
		System.out.println("calling assignChromosomes");
		
		assignChromosomes();
		
		/*for(int i=0;i<chromosomeLength;i++){
			for(int j=0;j<chromosomeLength;j++){
				//System.out.print(chromosomes[i][j]+ "\t");
				//check(chromosomes[i],password[i]);
			}
			
			System.out.println();
		}*/
		
		for(int i=0;i<chromosomeLength;i++){			
				//System.out.print(chromosomes[i][j]+ "\t");
				check(chromosomes[i],password[i]);
			}
			
			
		
		
		
	}
	
	private void check(int[] array,String s){
		for(int i=0;i<array.length;i++){
			first=Integer.parseInt(s.substring(0,3));
			middle=Integer.parseInt(s.substring(3,6));;
			last=Integer.parseInt(s.substring(6,8));;			
			
			oneBlue=pixels[i*3]&248;			
			oneGreen=pixels[i*3+1]&248;
			oneRed=pixels[i*3+2]&252;
			
			oneBlue+=first;
			oneGreen+=middle;
			oneRed+=last;
			
			System.out.print((pixels[i*3+2]-oneRed)*(pixels[i*3+2]-oneRed)+
					(pixels[i*3]-oneBlue)*(pixels[i*3]-oneBlue)+
					(pixels[i*3+1]-oneGreen)*(pixels[i*3+1]-oneGreen)
			);
			System.out.print("\t");
			
		}
		System.out.println();
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
		//filterChromosomes();
		
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
		/*for(int i=0;i<10;i++){
			System.out.print(pObjects[i].red+"\t ");
		}*/
		//System.out.println();
		temp=ch;
		PixelObject[] tempObjects=pObjects.clone();
		
		
		//for(i=0;i<tempObjects.length)
		
		Arrays.sort(tempObjects);		
		
		int[] returnValue=new int[chromosomeLength];
		//PixelObject[] returnValue=new PixelObject[chromosomeLength];
		for(int i=0;i<chromosomeLength;i++){
			//returnValue[i]=tempObjects[i];
			returnValue[i]=tempObjects[i].No;
		}
		
		//System.out.println("done");
		
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
