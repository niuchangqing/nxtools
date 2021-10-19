package cn.nxtools.jwt.config;

import java.security.Key;

/**
 * 非对称公私钥匙配置
 */
public interface JwtSecretKey {

    Key getPublicKey();

    Key getPrivateKey();
}
