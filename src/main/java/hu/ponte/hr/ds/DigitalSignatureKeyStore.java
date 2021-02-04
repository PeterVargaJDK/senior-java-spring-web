package hu.ponte.hr.ds;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface DigitalSignatureKeyStore {
    PrivateKey getPrivateKey();
    PublicKey getPublicKey();
}
