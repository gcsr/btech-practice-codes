



import java.io.*;
import java.rmi.*;
import java.rmi.server.*;


public class NullPrinter extends UnicastRemoteObject implements Printer {
    private PrintWriter _log;
    public NullPrinter(OutputStream log)
        throws RemoteException {
        _log = new PrintWriter(log);
    }

    public boolean printerAvailable() {
        return true;
    }

    public boolean printDocument(DocumentDescription documentDescription)
        throws PrinterException {
        if (null == _log) {
            throw new NoLogException();
        }
        if (null == documentDescription) {
            throw new NoDocumentException();
        }
        _log.println("Printed file");
        _log.flush();
        System.out.println("server printing");
        if (_log.checkError()) {
            throw new CantWriteToLogException();
        }
        return true;
    }

    private class NoLogException extends PrinterException {
        public NoLogException() {
            super (0, "Null printer failure. No log to record print request.");
        }
    }


    private class NoDocumentException extends PrinterException {
        public NoDocumentException() {
            super (0, "Null printer failure. No document received as part of print request.");
        }
    }


    private class CantWriteToLogException extends PrinterException {
        public CantWriteToLogException() {
            super (0, "Null printer failure. Attempt to record print request to log failed");
        }
    }
}
