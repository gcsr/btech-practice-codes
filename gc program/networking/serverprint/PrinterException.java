


import java.io.*;


public class PrinterException extends Exception {
    private int _numberOfPagesPrinted;
    private String _humanReadableErrorDescription;

    public PrinterException(int numberOfPagesPrinted,
        String humanReadableErrorDescription) {
        _numberOfPagesPrinted = numberOfPagesPrinted;
        _humanReadableErrorDescription = humanReadableErrorDescription;
    }

    public int getNumberOfPagesPrinted() {
        return _numberOfPagesPrinted;
    }

    public String getHumanReadableErrorDescription() {
        return _humanReadableErrorDescription;
    }
}
