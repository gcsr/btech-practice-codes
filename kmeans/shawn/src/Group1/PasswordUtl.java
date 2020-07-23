/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group1;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class PasswordUtl {
     
    public String encryptPasswordReceivedFromLoginPage(String unecryptedPassword){
        String salt = "RandomStringForExtraSecurity@#$!%^&*(*)1234567890";
        MessageDigest messageDigest=null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update((unecryptedPassword+salt).getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return (new BigInteger(messageDigest.digest())).toString(16);
         
    }
     
    public String retrieveEncryptedAndSaltedPasswordFromDatabase(String userID){
        return "51fdef7d07bef69c03c3ad9337951c63";
    }
     
    public boolean verifyPassword(String userId, String unecryptedPassword){
        String encryptedLoginPagePassword = encryptPasswordReceivedFromLoginPage(unecryptedPassword);
        String encryptedPasswordFromDatabase = retrieveEncryptedAndSaltedPasswordFromDatabase(userId);
        if (encryptedLoginPagePassword.equals(encryptedPasswordFromDatabase))
            return true;
        return false;
    }
     
     
 
}
