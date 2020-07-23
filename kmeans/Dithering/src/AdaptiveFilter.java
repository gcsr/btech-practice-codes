import ij.ImagePlus;

import java.awt.image.BufferedImage;
import java.util.Arrays;


public class AdaptiveFilter {
	
	private static final int KERNEL_DIMENSION=5;
	ImagePlus imp;
	BufferedImage image;
	int height,width;
	int[][] blueChannel;
	int[][] redChannel;
	int[][] greenChannel;
	double var_noise;
	int[][] blueOutputChannel;
	int[][] redOutputChannel;
	int[][] greenOutputChannel;
	byte[] pixels;
	int picSize;
	public AdaptiveFilter(BufferedImage image,double var_noise){
		this.var_noise=var_noise;
		//imp=new ImagePlus();
		//imp.setImage(image);
		this.image=image;
		height=image.getHeight();
		width=image.getWidth();
		pixels = (byte[]) image.getData().getDataElements(0, 0, width, height, null);
          //System.out.println("pixels size is "+pixels.length);
			
			 
	}	
	
	public BufferedImage adaptiveImage(){
		
		picSize=height*width;
		
		blueChannel=new int[height][width];
		
		greenChannel=new int[height][width];
		
		redChannel=new int[height][width];
		
		int offset = 0;
		for (int i = 0; i < picSize; i++) {
		  int chevi=i/width;
		  int sesham=i%width;
		  blueChannel[chevi][sesham] = pixels[offset++] & 0xff;
		  greenChannel[chevi][sesham] = pixels[offset++] & 0xff;
		  redChannel[chevi][sesham] = pixels[offset++] & 0xff;	  						  
		}
		
		blueOutputChannel=ApplyAdaptiveFilter(blueChannel,KERNEL_DIMENSION,width,height);
		redOutputChannel=ApplyAdaptiveFilter(redChannel,KERNEL_DIMENSION,width,height);
        greenOutputChannel=ApplyAdaptiveFilter(greenChannel,KERNEL_DIMENSION,width,height);
        
        offset=0;
        for(int i=0;i<height;i++){
        	for(int j=0;j<width;j++){
        		pixels[offset++]=(byte)blueOutputChannel[i][j];
        		pixels[offset++]=(byte)greenOutputChannel[i][j];
        		pixels[offset++]=(byte)redOutputChannel[i][j];
        	}
        }
        
        BufferedImage result=null;
		result= new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		result.getWritableTile(0, 0).setDataElements(0, 0, width, height, pixels);
		return result;
		
	}
	
	 public double varianceGlobal(int here[][]) {
	        double sum=0;
	        double number = height*width;;
	        
	        
	        for(int j=0;j<height;++j)
	        {
	            for(int i=0;i<width;++i)
	            {
		            sum+=here[j][i];
	            }
	        }
	        
	        double avg=sum/number;
	        
	        sum=0;
	        for(int j=0;j<height;++j)
	        {
	            for(int i=0;i<width;++i)
	            {
		            sum+=Math.pow(here[j][i]-avg, 2);
	            }
	        }
	        if(number==0) 
	            return 0;
	        
	        return (sum/number);
	    }
	public int[][] ApplyAdaptiveFilter(int[][] input, int k,int width, int height){
		
		
        double mean_l=0;
        double var_l=0;
        int [][] outputArrays = new int [width][height];
        int value=0;
        
        //for(int rota=0;rota<10;rota++){
        var_noise=varianceGlobal(input);	
        for(int j=0;j<height;++j){
            for(int i=0;i<width;++i){
                mean_l=MeanLocal(input,k,width,height,i,j);
                var_l=VarianceLocal(input,k,width,height,i,j,mean_l);
                double ratio;
                if(var_noise>var_l)
                    ratio=1;
                else
                    ratio=var_noise/var_l;
                value=input[i][j]-(int)(ratio*(input[i][j]-mean_l));
                if(value<0)
                    value=0;
                if(value>255)
                    value=255;
                outputArrays[i][j] = value;
            }
        }
        //input=outputArrays;
        //}
        return outputArrays;
    }
	
	/**
	    * Calculates the mean of a kxk pixel neighbourhood (including centre pixel).
	    *
	    * @param input The input image 2D array
	    * @param k Dimension of the kernel
	    * @param w The image width
	    * @param h The image height
	    * @param x The x coordinate of the centre pixel of the array
	    * @param y The y coordinate of the centre pixel of the array
	    * @return The mean of the kxk pixels
	    */ 
	    public double MeanLocal(int[][] input, int k,int w, int h, int x, int y) {
	        double sum=0;
	        double number = 0;
	        for(int j=0;j<k;++j)
	        {
	            for(int i=0;i<k;++i)
	            {
		            if(((x-1+i)>=0) && ((y-1+j)>=0) && ((x-1+i)<w) && ((y-1+j)<h))
	                {
	                    sum = sum+input[x-1+i][y-1+j];
	                    ++number;
	                }
	            }
	        }
	        if(number==0) 
	            return 0;
	        return (sum/number);
	    }
	    /**
	    * Calculates the variance of a kxk pixel neighbourhood (including centre pixel).
	    *
	    * @param input The input image 2D array
	    * @param k Dimension of the kernel
	    * @param w The image width
	    * @param h The image height
	    * @param x The x coordinate of the centre pixel of the array
	    * @param y The y coordinate of the centre pixel of the array
	    * @param m The mean of a kxk pixel neighbourhood 
	    * @return The variance of the kxk pixels
	    */ 
	    public double VarianceLocal(int[][] input, int k,int w, int h, int x, int y, double m) {
	        double sum=0;
	        double number = 0;
	        for(int j=0;j<k;++j)
	        {
	            for(int i=0;i<k;++i)
	            {
		            if(((x-1+i)>=0) && ((y-1+j)>=0) && ((x-1+i)<w) && ((y-1+j)<h))
	                {
	                    sum = sum+Math.pow((input[x-1+i][y-1+j]-m),2);
	                    ++number;
	                }
	            }
	        }
	        if(number==0) 
	            return 0;
	        return (sum/number);
	    }
	    /**
	    * Calculates the median of a kxk pixel neighbourhood (including centre pixel).
	    *
	    * @param input The input image 2D array
	    * @param k Dimension of the kernel
	    * @param w The image width
	    * @param h The image height
	    * @param x The x coordinate of the centre pixel of the array
	    * @param y The y coordinate of the centre pixel of the array
	    * @return The median of the kxk pixels
	    */ 
	    public int median(int[][] input, int k,int w, int h, int x, int y) {
	        int[] supp=new int[k*k];
	        int t=0;
	        int number = 0;
	        for(int j=0;j<k;++j)
	        {
	            for(int i=0;i<k;++i)
	            {
		            if(((x-1+i)>=0) && ((y-1+j)>=0) && ((x-1+i)<w) && ((y-1+j)<h))
	                {
	                    supp[t]=input[x-1+i][y-1+j];
	                    t++;
		                ++number;
		            }
	            }
	        }
	        if(number==0) 
	            return 0;
	        Arrays.sort(supp);
	        return supp[((k*k-1)/2)];
	    }
	    
	    
	    /*public int[][] ApplyAdaptiveMedianFilter(int[][] input, int k,int width, int height)
	    {
	        int z_min;
	        int z_max;
	        int z_med;
	        int z;
	        boolean supp=false;
	        int [][] outputArrays = new int [width][height];
	        for(int j=0;j<height;++j)
	            for(int i=0;i<width;++i)
	            {
	                int dim=k;
	                int value=0;
	                while(dim<S_max+1)
	                {  
	                    z_min=min(input,dim,width,height,i,j);
	                    z_max=max(input,dim,width,height,i,j);
	                    z_med=median(input,dim,width,height,i,j);
	                    z=input[i][j];
	                    int A1=z_med-z_min;
	                    int A2=z_med-z_max;
	                    if((A1>0)&&(A2<0))
	                    {  
	                        int B1=z-z_min;
	                        int B2=z-z_max;
	                        if((B1>0)&&(B2<0))
	                            value=z;
	                        else 
	                            value=z_med;
	                        break;
	                    }
	                    else
	                    {
	                        dim=dim+2;
	                        if(dim<=S_max)
	                            continue;
	                        else 
	                        {
	                            value=z;
	                            break;
	                        }
	                    }
	                }
	                if(value<0)
	                    value=0;
	                if(value>255)
	                    value=255;
	                outputArrays[i][j] = value;
	            }
	        return outputArrays;
	    }
	    */
	    


}
