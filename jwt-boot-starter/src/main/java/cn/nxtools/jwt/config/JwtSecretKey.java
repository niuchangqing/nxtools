package cn.nxtools.jwt.config;

import java.security.Key;

/**
 * 非对称公私钥匙配置
 * 在签名类型为RS256,RS384,RS512,ES256,ES384,ES512是需要继承并注入该接口
 */
public interface JwtSecretKey {

    Key getPublicKey();

    Key getPrivateKey();
}
