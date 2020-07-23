


import java.rmi.*;


public interface Printer extends PrinterConstants, Remote {
    public boolean printerAvailable()
        throws RemoteException;
    public boolean printDocument(DocumentDescription document)
        throws RemoteException, PrinterException;
}
