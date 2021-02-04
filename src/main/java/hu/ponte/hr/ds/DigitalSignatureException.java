package hu.ponte.hr.ds;

public class DigitalSignatureException extends Exception {

    public DigitalSignatureException() {
    }

    public DigitalSignatureException(String message) {
        super(message);
    }

    public DigitalSignatureException(String message, Throwable cause) {
        super(message, cause);
    }

    public DigitalSignatureException(Throwable cause) {
        super(cause);
    }
}
