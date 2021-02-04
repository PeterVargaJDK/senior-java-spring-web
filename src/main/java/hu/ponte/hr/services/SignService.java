package hu.ponte.hr.services;

import hu.ponte.hr.ds.DigitalSignatureException;
import hu.ponte.hr.ds.DigitalSignatureMethod;
import hu.ponte.hr.ds.Verifiable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignService {

    @Autowired
    private DigitalSignatureMethod digitalSignatureMethod;

    public void sign(Verifiable verifiable) {
        try {
            byte[] signature = digitalSignatureMethod.generateSignature(verifiable.getContent());
            verifiable.setSignature(signature);
        } catch (DigitalSignatureException e) {
            throw new ServiceException("Error happpened while generating signature", e);
        }
    }

    public void signAll(Iterable<? extends Verifiable> verifiables) {
        try {
            for (Verifiable verifiable : verifiables) {
                byte[] signature = digitalSignatureMethod.generateSignature(verifiable.getContent());
                verifiable.setSignature(signature);
            }
        } catch (DigitalSignatureException e) {
            throw new ServiceException("Error happpened while generating signature", e);
        }
    }

}
