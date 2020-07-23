import java.io.IOException;
import java.util.Scanner;

public class FileSystemProject {
    
    public static Shell mainShell = new Shell();
 
    public static void main(String[] args) throws IOException {
        mainShell.input = new Scanner("in ldisk.txt cr foo op foo wr 1 x 192 wr 1 y 10 sk 1 55 rd 1 10 dr sv disk0.txt in disk0.txt op foo rd 1 3 cr foo cl 1 dr");
        mainShell.run();
        
        System.out.print(mainShell.output);
    }
    
}
