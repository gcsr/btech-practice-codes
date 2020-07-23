/* Implementation of RSA algorithm in java 
 * Author name:-Manish Goyal 
 * System Name:-HCL 
 * Operating system used:-Window Vista 
 * Language used:-java 
 * This Program will take any string as input then encrypt it 
 * and finally decrypt the data */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;

import javax.crypto.Cipher;
public class RSA { 
	public static void main(String args[]) { 
		String srci=""; 
		try{ 
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
			System.out.println("Please enter any string you want to encrypt"); 
			srci=br.readLine(); 
			} 
		catch(IOException ioe) { 
			System.out.println(ioe.getMessage()); 
			} 
		try{ 
			KeyPairGenerator kpg=KeyPairGenerator.getInstance("RSA");
			kpg.initialize(512);
			//initialize key pairs to 512 bits ,you can also take 1024 or 2048 bits 
			KeyPair kp=kpg.genKeyPair(); 
			
			PublicKey publi=kp.getPublic(); 
			
			
			
			
			byte[] bb=publi.getEncoded();
			String b=new String(bb);
			System.out.println("key is "+b);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publi);
			byte[]src=srci.getBytes();
			//converting source data into byte array 
			byte[] cipherData = cipher.doFinal(src);
			//use this method to finally encrypt data
			String srco=new String(cipherData);
			//converting byte array into string 
			System.out.println(); 
			System.out.println("Encrypted data is:-"+srco);
			PrivateKey privatei=kp.getPrivate();
			
			RSAPrivateKey priv = (RSAPrivateKey) kp.getPrivate();
			System.out.println(priv.getModulus());
			System.out.println("private key is "+priv.getPrivateExponent());
			
			//Generating private key 
			Cipher cipheri=Cipher.getInstance("RSA");
			//Intializing 2nd instance of Cipher class 
			cipheri.init(Cipher.DECRYPT_MODE, privatei);
			//Setting to decrypt_mode 
			byte[] cipherDat = cipheri.doFinal(cipherData);
			//Finally decrypting data 
			String decryptdata=new String(cipherDat); 
			System.out.println("Decrypted data:-"+decryptdata); 
			} catch(Exception e) { 
				System.out.println(e.getMessage()); 
			} 
	} 
} 
		
 	