    import java.io.*;
    public class CreateFolder {
     
        public static void main(String args[]){
        File f = new File("C:\\TEST");
        try{
            if(f.exists()==false){
                f.mkdir();
            System.out.println("Directory Created");
           }
          else{
          System.out.println("Directory is not created");
           }
       }catch(Exception e){
           e.printStackTrace();
          }
       }
   }

