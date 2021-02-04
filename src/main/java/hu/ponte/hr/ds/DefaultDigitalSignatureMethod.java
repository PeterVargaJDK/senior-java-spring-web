package hu.ponte.hr.ds;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.*;

public class DefaultDigitalSignatureMethod implements DigitalSignatureMethod {

    private final DigitalSignatureKeyStore context;
    private final String algorithm;

    public DefaultDigitalSignatureMethod(DigitalSignatureKeyStore context, String algorithm) {
        this.context = context;
        this.algorithm = algorithm;
    }

    @Override
    public byte[] generateSignature(byte[] data) throws DigitalSignatureException {
        try {
            Signature signature = initializeForSigning(data);
            byte[] digitalSignature = signature.sign();
            byte[] encodedSignature = encode(digitalSignature);
            return encodedSignature;
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new DigitalSignatureException("Error happened while generating digital signature.", e);
        }
    }

    private Signature initializeForSigning(byte[] data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(context.getPrivateKey());
        signature.update(data);
        return signature;
    }

    @Override
    public boolean verifySignature(byte[] data, byte[] signatureToVerify) throws DigitalSignatureException {
        try {
            Signature signature = initializeForVerification(data);
            return signature.verify(encode(signatureToVerify));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new DigitalSignatureException("Error happened while signature verification.", e);
        }
    }

    private Signature initializeForVerification(byte[] data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(context.getPublicKey());
        signature.update(data);
        return signature;
    }

    private byte[] encode(byte[] digitalSignature) {
        return Base64.encodeBase64(digitalSignature);
    }


}
