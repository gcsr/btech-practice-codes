


import java.io.*;


public abstract class ValueObject implements Serializable {
    private String _stringifiedRepresentation;
    private boolean _alreadyHashed;
    private int _hashCode;

    public ValueObject(String stringifiedRepresentation) {
        _stringifiedRepresentation = stringifiedRepresentation;
        _alreadyHashed = false;
    }

    public String toString() {
        return _stringifiedRepresentation;
    }

    public int hashCode() {
        if (false == _alreadyHashed) {
            _hashCode = _stringifiedRepresentation.hashCode();
            _alreadyHashed = true;
        }
        return _hashCode;
    }
} 

