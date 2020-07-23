import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shell 
{
    Scanner input;
    String command;
    FileSystem fs;
    String output;
    int status;
    
    public Shell()
    {
        //input = new Scanner();
        command = new String();
        fs = new FileSystem();
        output = new String();
        status = 0;
    }
    
    
    public void run()
    {
        while(input.hasNext())
        {
        //Get command
        command = input.next();
        
        //Process command
        if(command.equals("cr")) //cd <name>
        {
            String name = input.next();
            //create a new file withl the name <name>
            status = fs.create(name);
            
            if(status < 0) output += "error\n"; //Output:error
            else output += "file "+ name +" created\n"; //Output: file <name> created
        }
        else if(command.equals("de")) //de <name>
        {
            String name = input.next();
            //destroy the named file <name>
            status = fs.destroy(name);
            
            if(status < 0) output += "error\n"; //Output:error
            else output += "file "+ name +" destroyed\n"; //Output: file <name> destroyed
        }
        else if(command.equals("op")) //op <name>
        {
            String name = input.next();
            //open the named file <name> for reading and writing; display an index value
            status = fs.open(name);
            
            if(status < 0) output += "error\n"; //Output:error
            else output += "file "+ name +" opened, index=" + status + "\n"; //Output: file <name> opened, index=<index>
        }
        else if(command.equals("cl")) //cl <index>
        {   
            int index = input.nextInt();
            //close the specified file <index>
            status = fs.close(index);
            
            if(status < 0) output += "error\n"; //Output:error
            else output += "file "+ index +" closed\n"; //Output: file <index> closed
        }
        else if(command.equals("rd")) //rd <index> <count>
        {
            int index = input.nextInt();
            int count = input.nextInt();
            
            byte [] bytesRead = new byte[count];
            
            //sequentially read a number of bytes <count> from the specified file <index> and display them on the terminal
            status = fs.read(index, bytesRead, count);
           
            if(status < 0) output += "error\n"; //Output:error
            else //Output: <count> bytes read: <xx...x>
            {
                String stringRepresentation = new String();
                output += index + " read: ";
                try {
                    stringRepresentation = new String(bytesRead, "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
                }
                output += stringRepresentation + "\n";
            } 
        }
        else if(command.equals("wr")) //wr <index> <char> <count>
        {
            int index = input.nextInt();
            String character = input.next();
            int count = input.nextInt();
            
            //sequentially write <count> number of <char>s into the specified file <index> at its current position
            byte [] myByte = character.getBytes();
            status = fs.write(index, myByte[0], count);
            
            if(status < 0) output += "error\n"; //Output:error
            else output += status + " bytes written\n"; //Output: <count> bytes written
            
        }
        else if(command.equals("sk")) //sk <index> <pos>
        {
            //seek: set the current position of the specified file <index> to <pos>
            status = fs.lseek(input.nextInt(), input.nextInt());
            
            if(status < 0) output += "error\n"; //Output:error
            else output += "current position is " + status + "\n"; //Output: current position is <pos>
            
        }
        else if(command.equals("dr")) //dr
        {
            String directoryList;
            //directory: list the names of all file
            directoryList = fs.directory();
            
            if(status < 0) output += "error\n"; //Output:error
            else output += directoryList + "\n"; //Output: <file0> <file1> ... <fileN>       
        }
        else if(command.equals("in")) //in <disk_cont.txt>
        {
            try {
                status = fs.init(input.next());
            } catch (IOException ex) {
                Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(status < 0) output += "error\n"; //Output:error             
            else if(status == 0) output += "disk restored\n"; //If file does not exist, output: disk initialized
            else if(status == 1) output += "disk initialized\n"; //If file does exist, output: disk restored
        }
        else if(command.equals("sv")) //sv <disk_cont.txt>
        {
            //close all files
            for(int i = 1; i <= fs.SIZE_OFT; i++)
            {
                fs.close(i);
            }
            
            //save the contents of the disk in the specified file
            status = fs.save(input.next());

            
            if(status < 0) output += "error\n"; //Output:error             
            else if(status == 0) output += "disk saved\n"; //Output: disk saved
        }
        else //Invalid command
        {
            output += "error\n"; //output: error
        }
      }
    }
}
