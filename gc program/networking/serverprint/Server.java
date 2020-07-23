




import java.io.*;
import java.rmi.*;
import java.rmi.server.*;


public class Server{
    public static void main(String args[]) {
        try {
            File logfile = new File("D:\gc\chapter3\Printer.java");
            OutputStream outputStream = new FileOutputStream(logfile);
            Printer printer = new NullPrinter(outputStream);

            Naming.rebind("sprinter", printer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 

