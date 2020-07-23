import ij.ImagePlus;
import com.jhlabs.*;
import ij.plugin.filter.PlugInFilter;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;



public class GaussianFilter {
	
	ImagePlus imp;
	public int width;
	public int height;
	private int kernel_size;
	private double sum;
	private float sigma;
	private double [][]gauss_matrix= new double[kernel_size][kernel_size];
	String directory;
	String title;
	boolean rep;
	int radius;
	

	public void setRadius(int radius){
		this.radius=radius;
	}
	private double [][] getMatrixGauss(){
		sum=0;		
		int k=radius;
		double [][] matrix = new double[k][k];
		for(int j=-(k/2);j<k/2;++j)
        {
            for(int i=-(k/2);i<k/2;++i)
            {
				matrix[i+k/2][j+k/2]=Math.pow(Math.E,-((Math.pow(i,2)+Math.pow(j,2))/2*Math.pow(sigma,2)));
	            sum=sum+matrix[i+k/2][j+k/2];
            }
        }
		return matrix;
	}
	
	public void setSigma(float sigma){
		this.sigma=sigma;
	}
	
	public BufferedImage getBlurredImage(BufferedImage scr){
		double[][] ker=getMatrixGauss();
		float[] kernel=new float[9];
		
		int k=0;
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				kernel[k++]=(float)ker[i][j];
			}
		}
		
		ConvolveOp meanOp = new ConvolveOp(
				  new Kernel(3, 3, kernel),ConvolveOp.EDGE_ZERO_FILL, null);
		BufferedImage blurImage=meanOp.filter(scr,null);
		return blurImage;
				 
	}
	
	public int gaussiano_pixel(int[][] input, double[][] matrix, int k,int w, int h, int x, int y) 
    {
        double somma = 0;
		//int [] intorno=new int [k*k];;
        for(int j=0;j<k;++j)
        {
            for(int i=0;i<k;++i)
            {
	            if(((x-1+i)>=0) && ((y-1+j)>=0) && ((x-1+i)<w) && ((y-1+j)<h))
                {
	                somma = somma + ((input[x-1+i][y-1+j])*matrix[i][j]);
	            }
            }
        }
		//Arrays.sort(intorno);
		//IJ.showMessage("array ordinato "+intorno);
        if(somma==0) 
            return 0;
		//int indice= (int)(number/2);
        return ((int)(somma/sum));
    }
	//Genera il report in HTML e lo salva nella cartella in cui si trovano le immagini
	}
	
	
	
