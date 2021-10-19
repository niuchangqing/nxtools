package cn.nxtools.jwt.enums;

/**
 * jwt token签名算法枚举
 * {@link io.jsonwebtoken.SignatureAlgorithm}
 */
public enum SignatureAlgorithm {
    HS256,
    HS384,
    HS512,
    RS256,
    RS384,
    RS512,
    ES256,
    ES384,
    ES512,
//    PS256,
//    PS384,
//    PS512,
    ;
}
