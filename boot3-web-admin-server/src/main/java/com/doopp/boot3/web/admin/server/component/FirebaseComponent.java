package com.doopp.boot3.web.admin.server.component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.doopp.boot3.web.core.exception.AssertException;

import jakarta.annotation.Resource;

@Component
public class FirebaseComponent {

    final static private String secureTokenUrl = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com";

    final static private Map<String, String> certificateMap = new ConcurrentHashMap<>();

    @Resource
    public RestTemplate restTemplate;

    // public LoginInfo verifyIdTokenGetInfo(String idToken) {
    //     try {
    //         DecodedJWT decodedJWT = JWT.decode(idToken);
    //         Algorithm algorithm = Algorithm.RSA256(getPublicKey(decodedJWT.getKeyId()), null);
    //         JWTVerifier verifier = JWT.require(algorithm).build();
    //         verifier.verify(decodedJWT);
    //         // build info
    //         return LoginInfo.builder()
    //             .email(decodedJWT.getClaim("email").asString())
    //             .openId(decodedJWT.getClaim("user_id").asString())
    //             // .authProvider(decodedJWT.getClaim("firebase").asMap().get("sign_in_provider").toString())
    //             .build();
    //     }
    //     catch (Exception e) {
    //         throw new AssertException(e);
    //     }
    // }

    private RSAPublicKey getPublicKey(String key) {
        try{
            InputStream is = new ByteArrayInputStream(getCertificate(key).getBytes());
            CertificateFactory ft = CertificateFactory.getInstance("X.509");
            Certificate certificate = ft.generateCertificate(is);
            return (RSAPublicKey) certificate.getPublicKey();
        }
        catch (Exception e) {
            throw new AssertException(e);
        }
    }

    private String getCertificate(String key) {
        String certificate = certificateMap.get(key);
        if (certificate!=null) {
            return certificate;
        }
        // ret try
        // updateCertificates();
        certificate = certificateMap.get(key);
        if (certificate!=null) {
            return certificate;
        }
        throw new AssertException("certificate(id:" + key + ") not found");
    }

    // public void updateCertificates(){
    //     ResponseEntity<Map<String, String>> response = restTemplate.getForEntity(secureTokenUrl, new TypeReference<Map<String, String>>(){});
    //     // Map<String, String> certificates = HttpClient.jsonGet(secureTokenUrl, null, new TypeReference<Map<String, String>>(){});
    //     if (!ObjectUtils.isEmpty(response.getBody())) {
    //         certificateMap.putAll(response.getBody());
    //     }
    // }
}

