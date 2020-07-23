import java.awt.image.BufferedImage;

import java.util.Arrays;
import java.util.List;


public class GeneticAlgo3 {
	static final public int NOOFCHROMOSOMES=100;
	ChromosomeElement[][] geneticChromosomes;
	List[] chromosomesList;
	ChromosomeElement[][] filteredChromosomes;
	int geneticNo=0;
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
	
	int first=0;
	int middle=0;
	int last=0;
	int[] finalResultPixels;
	class ChromosomeElement implements Comparable{
		int no;
		int diff;
		ChromosomeElement(int no,int diff){
			this.no=no;
			this.diff=diff;
		}
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			ChromosomeElement op=(ChromosomeElement)o;
			return diff-op.diff;
		}
		
		
		
	}

	class PixelObject implements Comparable{
		int No;
		int blue;
		int green;
		int red;
		int diff=0;
		
		
		
		PixelObject(int No,int blue,int green,int red){
			this.No=No;
			this.red=red;
			this.blue=blue;
			this.green=green;
		}
		
		public int compareTo(Object twow) {			
			PixelObject two=(PixelObject)twow;
			return diff-two.diff;
		}		
		
	}
	
	PixelObject[] pObjects;

	int width,height;
	
	public GeneticAlgo3(int[] pixels,String[] password,int width,int height){
		//System.out.println(pixels.length);
		this.pixels=pixels;
		this.password=password;
		chromosomeLength=password.length;
		pixelsLength=pixels.length/3;
		chromosomes=new int[chromosomeLength][chromosomeLength];
		//chromosomes=new PixelObject[chromosomeLength][chromosomeLength];
		pObjects=new PixelObject[pixelsLength];		
		chromosomesList=new List[chromosomeLength];
		for(int i=0;i<pixelsLength;i++){
			pObjects[i]=new PixelObject(i,pixels[i*3],pixels[i*3+1],pixels[i*3+2]);			
		}
		this.width=width;
		this.height=height;
		
		finalResultPixels=new int[password.length];
		System.out.println("calling assignChromosomes");
		
		assignChromosomes();
		
		for(int i=0;i<chromosomeLength;i++){
			for(int j=0;j<chromosomeLength;j++){
				System.out.print(chromosomes[i][j]+ "\t");
				//check(chromosomes[i],password[i]);
			}
			
			System.out.println();
		}
		
		/*
		
		int singleElements=0;
		
		for(int i=0;i<chromosomeLength;i++){
			chromosomesList[i]=new LinkedList<Integer>();
			for(int j=0;j<chromosomeLength;j++){
				if(chromosomes[i][j]<0)
					break;
				else
					chromosomesList[i].add(chromosomes[i][j]);
			}
			
			if(chromosomesList[i].size()==1)
				singleElements++;
			
		}
		
		filteredChromosomes=new ChromosomeElement[chromosomeLength-singleElements][];
		int index=0;
		for(int i=0;i<chromosomes.length;i++){
			if(chromosomesList[i].size()==1)
				continue;
				else{
					int counter=0;
					filteredChromosomes[i]=new ChromosomeElement[chromosomesList[i].size()];
					Iterator ite=chromosomesList[i].iterator();
					int j=0;
					while(ite.hasNext()){
						filteredChromosomes[index][j]=getDiff((Integer)ite.next(),password[i]);
					}
					index++;
				}
		}
		//for()
		
		//filtere
		
		/*for(int i=0;i<chromosomeLength;i++){			
				//System.out.print(chromosomes[i][j]+ "\t");
				check(chromosomes[i],password[i]);
		}*/
			
			
		
		
		for(int i=0;i<chromosomeLength;i++){			
			assign(i);			
		}
		
		System.out.println("and the final result is ");
		
		for(int i:finalResultPixels)
			System.out.print(" "+i);
	}
	int difference=0,ii=0;
	
	private void assign(int i){
		int[] tempChromosomes=chromosomes[i];
		for(int ip=0;ip<chromosomeLength;ip++){
			if(notExists(i,tempChromosomes[ip])){
				finalResultPixels[i]=tempChromosomes[ip];
				return;
			}
		}
	}
	
	
	private boolean notExists(int i,int tempNo){
		for(int j=0;j<i;j++){
			if(finalResultPixels[j]==tempNo)
					return false;		
			
		}
		
		return true;
	}
	
	private void check(int[] array,String s){
		first=Integer.parseInt(s.substring(0,3),2);
		middle=Integer.parseInt(s.substring(3,6),2);
		last=Integer.parseInt(s.substring(6,8),2);
		
		for(int i=0;i<array.length;i++){
			
			ii=array[i];
			oneBlue=pObjects[ii].blue & 248;			
			oneGreen=pObjects[ii].green & 248;
			oneRed=pObjects[ii].red & 252;
			
			oneBlue+=first;
			oneGreen+=middle;
			oneRed+=last;
			difference=(pObjects[ii].red-oneRed)*(pObjects[ii].red-oneRed)+
					(pObjects[ii].blue-oneBlue)*(pObjects[ii].blue-oneBlue)+
					(pObjects[ii].green-oneGreen)*(pObjects[ii].green-oneGreen);
			System.out.print(difference);
			System.out.print("\t");
			
			System.out.print(ii);
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
		int length=filteredChromosomes.length;
		geneticNo=length*length;
		geneticChromosomes=new ChromosomeElement[geneticNo][length];
		
		for(int i=0;i<geneticNo;i++){
			geneticChromosomes[i]=initialize(length);
		}
		
		for(int i=0;i<length*length;i++){
			mutate();
			crossover();
			order();
		}
	}
	
	
	
	
	
	
	public void getPixels(){
		
	}
	
	
	public ChromosomeElement[] initialize(int length){
		int sizes[]=new int[length];
		for(int i=0;i<length;i++){
			sizes[i]=filteredChromosomes[i].length;
		}
		ChromosomeElement[] result=new ChromosomeElement[length];
		for(int i=0;i<length;i++){
			
			result[i]=filteredChromosomes[i][(int)(Math.random()*sizes[i])];
		}
		
		return result;
	}
	
	
	public void mutate(){
		for(int i=0;i<geneticNo;i++){
			
		}
	}
	
	
	public void crossover(){
		
	}
	
	public void order(){
		
	}
	
	int totalDiff(ChromosomeElement[] ele){
		int sum=0;
		for(ChromosomeElement s:ele)
			sum+=s.diff;
		return sum;
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
		first=Integer.parseInt(temp.substring(0,3),2);
		middle=Integer.parseInt(temp.substring(3,6),2);;
		last=Integer.parseInt(temp.substring(6,8),2);;
		
		
		for(int i=0;i<tempObjects.length;i++){
			oneBlue=tempObjects[i].blue & 248;			
			oneGreen=tempObjects[i].green & 248;
			oneRed=tempObjects[i].red & 252;
			
			oneBlue+=first;
			oneGreen+=middle;
			oneRed+=last;
			
			
			tempObjects[i].diff=(oneBlue-tempObjects[i].blue)*(oneBlue-tempObjects[i].blue)
					+(oneRed-tempObjects[i].red)*(oneRed-tempObjects[i].red)
					+(oneGreen-tempObjects[i].green)*(oneGreen-tempObjects[i].green);
			
			/*if(i<10){
				System.out.print(tempObjects[i].blue+"\t "+tempObjects[i].green+"\t "+tempObjects[i].red+"\t "+tempObjects[i].diff+"\t ");
				System.out.println();
				System.out.print(oneBlue+"\t "+oneGreen+"\t "+oneRed+"\t ");
				System.out.println();
			}*/
			
		}		
		
		Arrays.sort(tempObjects);
		
		
		/*for(int i=0;i<10;i++){
			System.out.print(pObjects[i].diff+"\t ");
		}
		System.out.println();
		
		for(int i=0;i<10;i++){
			System.out.print(tempObjects[i].diff+"\t ");
		}
		System.out.println();*/
	
	
		
		
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
		
		for(int pi=0;pi<chromosomeLength;pi++){
			for(int pj=0;pj<chromosomeLength;pj++){
				if(pi==i)
					break;
				else{
					if(chromosomes[i][j]==chromosomes[pi][pj])
						return false;
				}
			}
			
		}
		return true;
	}
	
	ChromosomeElement getDiff(int i,String tmep){
		first=Integer.parseInt(temp.substring(0,3),2);
		middle=Integer.parseInt(temp.substring(3,6),2);;
		last=Integer.parseInt(temp.substring(6,8),2);;
		
		oneBlue=pixels[3*i] & 248;			
		oneGreen=pixels[3*i+1] & 248;
		oneRed=pixels[3*i+2] & 252;
		
		oneBlue+=first;
		oneGreen+=middle;
		oneRed+=last;
		
		
		int diff=(oneBlue-pixels[3*i])*(oneBlue-pixels[3*i])
				+(oneRed-pixels[3*i+2])*(oneRed-pixels[3*i+2])
				+(oneGreen-pixels[3*i+1])*(oneGreen-pixels[3*i+1]);
		
		return new ChromosomeElement(i,diff);
	
	}
	
	public int[] pixelPositions(){
		return finalResultPixels;
	}
	
	public BufferedImage getEmbeddedeImage(){
		
		for(int i=0;i<chromosomeLength;i++){
			int pixel=pixels[3*finalResultPixels[i]];
			int secPixel=pixels[3*finalResultPixels[i]+1];
			int thiPixel=pixels[3*finalResultPixels[i]+2];
			
			temp=password[i];
			first=Integer.parseInt(temp.substring(0,3),2);
			middle=Integer.parseInt(temp.substring(3,6),2);;
			last=Integer.parseInt(temp.substring(6,8),2);;
			
			pixel=pixel & 248;			
			secPixel=secPixel & 248;
			thiPixel=thiPixel & 252;
			
			pixel+=first;
			secPixel+=middle;
			thiPixel+=last;
			System.out.println(pixel+" "+secPixel+" "+thiPixel);
			
			pixels[3*finalResultPixels[i]]=pixel;
			pixels[3*finalResultPixels[i]+1]=secPixel;
			pixels[3*finalResultPixels[i]+2]=thiPixel;
			
		}
		
		byte[] pp=new byte[pixels.length];
		//System.out.println(pp.length);
		for(int is=0;is<pp.length;is++){
			pp[is]=(byte)pixels[is];
		}
		
		BufferedImage finalImage=null;
		finalImage = new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);
		finalImage.getWritableTile(0, 0).setDataElements(0, 0,width,height, pp);
		
		return finalImage;
	}
	
	

}
