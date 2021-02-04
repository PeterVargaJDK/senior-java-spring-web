package hu.ponte.hr.ds;

public interface DigitalSignatureMethod {

    byte[] generateSignature(byte[] data) throws DigitalSignatureException;
    boolean verifySignature(byte[] data, byte[] signatureToVerify) throws DigitalSignatureException;

}
