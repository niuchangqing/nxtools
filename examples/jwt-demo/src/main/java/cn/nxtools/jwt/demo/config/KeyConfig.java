package cn.nxtools.jwt.demo.config;

import cn.nxtools.jwt.config.JwtSecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import io.jsonwebtoken.impl.Base64Codec;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
public class KeyConfig implements JwtSecretKey {

    //公钥字符串
    @Value("${key.public}")
    private String publicKeyStr;

    //私钥字符串
    @Value("${key.private}")
    private String privateKeyStr;

    private RSAPrivateKey getRsaPrivate() throws NoSuchAlgorithmException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Codec.BASE64.decode(privateKeyStr));
        RSAPrivateKey privateKey = null;
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        try {
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return privateKey;
    }

    private RSAPublicKey getRsaPublic() throws NoSuchAlgorithmException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Codec.BASE64.decode(publicKeyStr));
        RSAPublicKey publicKey = null;
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        try {
            publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return publicKey;
    }


    @Override
    public Key getPublicKey() {
        try {
            return getRsaPublic();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("公钥错误");
        }
    }

    @Override
    public Key getPrivateKey() {
        try {
            return getRsaPrivate();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("私钥错误");
        }
    }
}
