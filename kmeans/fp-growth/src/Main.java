/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Main {

    static int threshold = 10000;
    //static String file = "census.dat";


    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        System.out.println("Select Data File");
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
  		int result=fileChooser.showOpenDialog(null);
  		if(result==JFileChooser.CANCEL_OPTION)
  		return;
  		
  		String file=(fileChooser.getSelectedFile().getAbsolutePath());
  		
  		Scanner scanner=new Scanner(System.in);
  		
  		System.out.println("Enter  threashold, since datasets are large give a threshold of above 5000");
  		threshold=scanner.nextInt();
  		
        new FPGrowth(new File(file), threshold);
        
        
        System.out.println((System.currentTimeMillis() - start));
    }
}
