<%@page import="java.sql.ResultSet"%>
<%@page import="com.util.DbConnector"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.swing.*"%>
<%@page import="java.io.*"%>
<%@page import="java.math.*"%>
<%@page import="java.security.*"%>
<%@page import="java.security.spec.*"%>
<%@page import="javax.crypto.*"%>
<html>
<%!
private static final String PUBLIC_KEY_FILE = "Public.key";  
private static final String PRIVATE_KEY_FILE = "Private.key";
 
%>
<%
		
		try {  
                System.out.println("-------GENRATE PUBLIC and PRIVATE KEY-------------");  
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");  
                keyPairGenerator.initialize(2048); //1024 used for normal securities  
                KeyPair keyPair = keyPairGenerator.generateKeyPair();  
                PublicKey publicKey = keyPair.getPublic();  
                PrivateKey privateKey = keyPair.getPrivate();  
                System.out.println("Public Key - " + publicKey);  
                System.out.println("Private Key - " + privateKey);  
      
                //Pullingout parameters which makes up Key  
                System.out.println("\n------- PULLING OUT PARAMETERS WHICH MAKES KEYPAIR----------\n");  
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
                RSAPublicKeySpec rsaPubKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);  
                RSAPrivateKeySpec rsaPrivKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);  
                System.out.println("PubKey Modulus : " + rsaPubKeySpec.getModulus());  
                System.out.println("PubKey Exponent : " + rsaPubKeySpec.getPublicExponent());  
                System.out.println("PrivKey Modulus : " + rsaPrivKeySpec.getModulus());  
                System.out.println("PrivKey Exponent : " + rsaPrivKeySpec.getPrivateExponent());  
                  
                //Share public key with other so they can encrypt data and decrypt thoses using private key(Don't share with Other)  
                System.out.println("\n--------SAVING PUBLIC KEY AND PRIVATE KEY TO FILES-------\n");  
                saveKeys(PUBLIC_KEY_FILE, rsaPubKeySpec.getModulus(), rsaPubKeySpec.getPublicExponent());  
                saveKeys(PRIVATE_KEY_FILE, rsaPrivKeySpec.getModulus(), rsaPrivKeySpec.getPrivateExponent());  
                  
                //Encrypt Data using Public Key  
				JFileChooser fileChooser1=null;
				fileChooser1=new JFileChooser();
				fileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result=fileChooser1.showOpenDialog(null);
				String ff=(fileChooser1.getSelectedFile().getAbsolutePath());
                File f=new File(ff);
                FileInputStream fin=new FileInputStream(f);
                
                int length=(int)f.length();
                
                byte[] fileData=new byte[length];
                
                int bytesRead = 0;
                int offset=0;
				while (offset < length) {
						
					bytesRead = fin.read(fileData, offset, fileData.length-offset);
					if (bytesRead == -1) break;
					offset += bytesRead;
				}
				fin.close();
				
                byte[] encryptedData = encryptData(fileData);//rsaObj.encryptData("this is working");  

				 FileOutputStream fout=new FileOutputStream(ff.substring(0,ff.lastIndexOf('.'))+".enc",true);
				 fout.write(encryptedData);
				 fout.close(); 
				  
                //Descypt Data using Private Key  
				decryptData(encryptedData);  
                //rsaObj.decryptData(fileData);
                  
            } catch (NoSuchAlgorithmException e) {  
                e.printStackTrace();  
            }catch (InvalidKeySpecException e) {  
                e.printStackTrace();  
            }  
      
%>

<%!

private void saveKeys(String fileName,BigInteger mod,BigInteger exp) throws IOException{  
            FileOutputStream fos = null;  
            ObjectOutputStream oos = null;  
              
            try {  
                System.out.println("Generating "+fileName + "...");  
                fos = new FileOutputStream(fileName);  
                oos = new ObjectOutputStream(new BufferedOutputStream(fos));  
                  
                oos.writeObject(mod);  
                oos.writeObject(exp);             
                  
                System.out.println(fileName + " generated successfully");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            finally{  
                if(oos != null){  
                    oos.close();  
                      
                    if(fos != null){  
                        fos.close();  
                    }  
                }  
            }         
        }  
          
        /** 
         * Encrypt Data 
         * @param data 
         * @throws IOException 
         */  
        private byte[] encryptData(String data) throws IOException {  
            System.out.println("\n----------------ENCRYPTION STARTED------------");  
              
            System.out.println("Data Before Encryption :" + data);  
            byte[] dataToEncrypt = data.getBytes();  
            byte[] encryptedData = null;  
            try {  
                PublicKey pubKey = readPublicKeyFromFile(PUBLIC_KEY_FILE);  
                Cipher cipher = Cipher.getInstance("RSA");  
                cipher.init(Cipher.ENCRYPT_MODE, pubKey);  
                encryptedData = cipher.doFinal(dataToEncrypt);  
                System.out.println("Encryted Data: " + encryptedData);  
                  
            } catch (Exception e) {  
                e.printStackTrace();  
            }     
              
            System.out.println("----------------ENCRYPTION COMPLETED------------");       
            return encryptedData;  
        }  
        
        
        private byte[] encryptData(byte[] data) throws IOException {  
            System.out.println("\n----------------ENCRYPTION STARTED------------");  
              
            System.out.println("Data Before Encryption :" + data);  
            byte[] dataToEncrypt = data;  
            byte[] encryptedData = null;  
            try {  
                PublicKey pubKey = readPublicKeyFromFile(PUBLIC_KEY_FILE);  
                Cipher cipher = Cipher.getInstance("RSA");  
                cipher.init(Cipher.ENCRYPT_MODE, pubKey);  
                encryptedData = cipher.doFinal(dataToEncrypt);  
                System.out.println("Encryted Data: " + encryptedData);  
                  
            } catch (Exception e) {  
                e.printStackTrace();  
            }     
              
            System.out.println("----------------ENCRYPTION COMPLETED------------");       
            return encryptedData;  
        }  
        
      
        /** 
         * Encrypt Data 
         * @param data 
         * @throws IOException 
         */  
        private void decryptData(byte[] data) throws IOException {  
            System.out.println("\n----------------DECRYPTION STARTED------------");  
            byte[] descryptedData = null;  
              
            try {  
                PrivateKey privateKey = readPrivateKeyFromFile(PRIVATE_KEY_FILE);  
                Cipher cipher = Cipher.getInstance("RSA");  
                cipher.init(Cipher.DECRYPT_MODE, privateKey);  
                descryptedData = cipher.doFinal(data);  
                System.out.println("Decrypted Data: " + new String(descryptedData));  
                  
            } catch (Exception e) {  
                e.printStackTrace();  
            }     
              
            System.out.println("----------------DECRYPTION COMPLETED------------");       
        }  
          
        /** 
         * read Public Key From File 
         * @param fileName 
         * @return PublicKey 
         * @throws IOException 
         */  
        public PublicKey readPublicKeyFromFile(String fileName) throws IOException{  
            FileInputStream fis = null;  
            ObjectInputStream ois = null;  
            try {  
                fis = new FileInputStream(new File(fileName));  
                ois = new ObjectInputStream(fis);  
                  
                BigInteger modulus = (BigInteger) ois.readObject();  
                BigInteger exponent = (BigInteger) ois.readObject();  
                  
                //Get Public Key  
                RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponent);  
                KeyFactory fact = KeyFactory.getInstance("RSA");  
                PublicKey publicKey = fact.generatePublic(rsaPublicKeySpec);  
                              
                return publicKey;  
                  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            finally{  
                if(ois != null){  
                    ois.close();  
                    if(fis != null){  
                        fis.close();  
                    }  
                }  
            }  
            return null;  
        }  
          
        /** 
         * read Public Key From File 
         * @param fileName 
         * @return 
         * @throws IOException 
         */  
        public PrivateKey readPrivateKeyFromFile(String fileName) throws IOException{  
            FileInputStream fis = null;  
            ObjectInputStream ois = null;  
            try {  
                fis = new FileInputStream(new File(fileName));  
                ois = new ObjectInputStream(fis);  
                  
                BigInteger modulus = (BigInteger) ois.readObject();  
                BigInteger exponent = (BigInteger) ois.readObject();  
                  
                //Get Private Key  
                RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus, exponent);  
                KeyFactory fact = KeyFactory.getInstance("RSA");  
                PrivateKey privateKey = fact.generatePrivate(rsaPrivateKeySpec);  
                              
                return privateKey;  
                  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            finally{  
                if(ois != null){  
                    ois.close();  
                    if(fis != null){  
                        fis.close();  
                    }  
                }  
            }  
            return null;  
        }
%>
<body>

</body>
</html>
