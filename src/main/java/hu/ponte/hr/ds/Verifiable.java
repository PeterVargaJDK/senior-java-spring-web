package hu.ponte.hr.ds;

public interface Verifiable {
    byte[] getContent();
    byte[] getSignature();
    void setSignature(byte[] signature);
}
