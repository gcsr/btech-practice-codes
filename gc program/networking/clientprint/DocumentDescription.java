


import java.io.*;


public class DocumentDescription implements Serializable, PrinterConstants {
    private transient InputStream _actualDocument;
    private int _length;
    private int _documentType;
    private boolean _printTwoSided;
    private int _printQuality;

    public DocumentDescription(InputStream actualDocument)
        throws IOException {
        this (actualDocument, DEFAULT_DOCUMENT_TYPE, DEFAULT_PRINT_TWO_SIDED,
            DEFAULT_PRINT_QUALITY);  
    }

    public DocumentDescription(InputStream actualDocument,
        int documentType, boolean printTwoSided, int printQuality)
        throws IOException {
        _documentType = documentType;
        _printTwoSided = printTwoSided;
        _printQuality = printQuality;
        BufferedInputStream buffer = new BufferedInputStream(actualDocument);
        DataInputStream dataInputStream = new DataInputStream(buffer);
        ByteArrayOutputStream temporaryBuffer = new ByteArrayOutputStream();

        _length = copy(dataInputStream, new DataOutputStream(temporaryBuffer));
        _actualDocument = new DataInputStream(new ByteArrayInputStream(temporaryBuffer.toByteArray()));
    }

    public int getDocumentType() {
        return _documentType;
    }

    public boolean isPrintTwoSided() {
        return _printTwoSided;
    }

    public int getPrintQuality() {
        return _printQuality;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        copy(_actualDocument, out);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        ByteArrayOutputStream temporaryBuffer = new ByteArrayOutputStream();

        copy(in, temporaryBuffer, _length);
        _actualDocument = new DataInputStream(new ByteArrayInputStream(temporaryBuffer.toByteArray()));
    }

    private int copy(InputStream source, OutputStream destination)
        throws IOException {
        int nextByte;
        int numberOfBytesCopied = 0;

        while (-1 != (nextByte = source.read())) {
            destination.write(nextByte);
            numberOfBytesCopied++;
        }
        destination.flush();
        return numberOfBytesCopied;
    }

    private void copy(InputStream source, OutputStream destination, int length)
        throws IOException {
        int counter;
        int nextByte;

        for (counter = 0; counter < length; counter++) {
            nextByte = source.read();
            destination.write(nextByte);
        }
        destination.flush();
    }

}
