package hu.ponte.hr.ds;

import hu.ponte.hr.services.SignService;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class DefaultDigitalSignatureKeyStore implements DigitalSignatureKeyStore {

    private static final String KEY_PRIVATE = "key.private";
    private static final String KEY_PUB = "key.pub";
    private static final String SLASH = "/";

    private final String keyPath;
    private final String algorithm;

    public DefaultDigitalSignatureKeyStore(String keyPath, String algorithm) {
        this.keyPath = keyPath;
        this.algorithm = algorithm;
    }

    @Override
    public PrivateKey getPrivateKey() {
        try (InputStream privateKeyStream = SignService.class.getResourceAsStream(keyPath + SLASH + KEY_PRIVATE)) {
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyStream.readAllBytes());
            KeyFactory kf = KeyFactory.getInstance(algorithm);
            return kf.generatePrivate(spec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PublicKey getPublicKey() {
        try (InputStream publicKeyStream = SignService.class.getResourceAsStream(keyPath + SLASH + KEY_PUB)) {
            X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyStream.readAllBytes());
            KeyFactory kf = KeyFactory.getInstance(algorithm);
            return kf.generatePublic(spec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
