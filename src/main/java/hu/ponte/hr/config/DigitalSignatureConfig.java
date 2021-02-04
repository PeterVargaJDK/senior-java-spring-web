package hu.ponte.hr.config;

import hu.ponte.hr.ds.DefaultDigitalSignatureKeyStore;
import hu.ponte.hr.ds.DefaultDigitalSignatureMethod;
import hu.ponte.hr.ds.DigitalSignatureKeyStore;
import hu.ponte.hr.ds.DigitalSignatureMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DigitalSignatureConfig {

    private static final String KEY_LOCATION = "/config/keys";
    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    @Bean
    public DigitalSignatureMethod digitalSignatureProcess() {
        DigitalSignatureKeyStore context = new DefaultDigitalSignatureKeyStore(KEY_LOCATION, KEY_ALGORITHM);
        DigitalSignatureMethod digitalSignatureMethod = new DefaultDigitalSignatureMethod(context, SIGNATURE_ALGORITHM);
        return digitalSignatureMethod;
    }

}
