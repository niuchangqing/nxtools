package cn.nxtools.jwt.enums;

/**
 * jwt token签名算法枚举
 * {@link io.jsonwebtoken.SignatureAlgorithm}
 */
public enum SignatureAlgorithm {
    NONE("NONE", "No digital signature or MAC performed", "None", null, io.jsonwebtoken.SignatureAlgorithm.NONE),
    HS256("HS256", "HMAC using SHA-256", "HMAC", "HmacSHA256", io.jsonwebtoken.SignatureAlgorithm.HS256),
    HS384("HS384", "HMAC using SHA-384", "HMAC", "HmacSHA384", io.jsonwebtoken.SignatureAlgorithm.HS384),
    HS512("HS512", "HMAC using SHA-512", "HMAC", "HmacSHA512", io.jsonwebtoken.SignatureAlgorithm.HS512),
    RS256("RS256", "RSASSA-PKCS-v1_5 using SHA-256", "RSA", "SHA256withRSA", io.jsonwebtoken.SignatureAlgorithm.RS256),
    RS384("RS384", "RSASSA-PKCS-v1_5 using SHA-384", "RSA", "SHA384withRSA", io.jsonwebtoken.SignatureAlgorithm.RS384),
    RS512("RS512", "RSASSA-PKCS-v1_5 using SHA-512", "RSA", "SHA512withRSA", io.jsonwebtoken.SignatureAlgorithm.RS512),
    ES256("ES256", "ECDSA using P-256 and SHA-256", "Elliptic Curve", "SHA256withECDSA", io.jsonwebtoken.SignatureAlgorithm.ES256),
    ES384("ES384", "ECDSA using P-384 and SHA-384", "Elliptic Curve", "SHA384withECDSA", io.jsonwebtoken.SignatureAlgorithm.ES384),
    ES512("ES512", "ECDSA using P-512 and SHA-512", "Elliptic Curve", "SHA512withECDSA", io.jsonwebtoken.SignatureAlgorithm.ES512),
    PS256("PS256", "RSASSA-PSS using SHA-256 and MGF1 with SHA-256", "RSA", "SHA256withRSAandMGF1", io.jsonwebtoken.SignatureAlgorithm.PS256),
    PS384("PS384", "RSASSA-PSS using SHA-384 and MGF1 with SHA-384", "RSA", "SHA384withRSAandMGF1", io.jsonwebtoken.SignatureAlgorithm.PS384),
    PS512("PS512", "RSASSA-PSS using SHA-512 and MGF1 with SHA-512", "RSA", "SHA512withRSAandMGF1", io.jsonwebtoken.SignatureAlgorithm.PS512);
    ;

    private final String  value;
    private final String  description;
    private final String  familyName;
    private final String  jcaName;
    /**
     * 对应jjwt支持的签名枚举
     */
    private final io.jsonwebtoken.SignatureAlgorithm jwtSignatureAlgorithm;

    SignatureAlgorithm(String value, String description, String familyName, String jcaName, io.jsonwebtoken.SignatureAlgorithm jwtSignatureAlgorithm) {
        this.value = value;
        this.description = description;
        this.familyName = familyName;
        this.jcaName = jcaName;
        this.jwtSignatureAlgorithm = jwtSignatureAlgorithm;
    }

    public boolean isHmac() {
        return this.name().startsWith("HS");
    }

    public boolean isNone() {
        return this.name().equalsIgnoreCase("NONE");
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getJcaName() {
        return this.jcaName;
    }

    public io.jsonwebtoken.SignatureAlgorithm getJwtSignatureAlgorithm() {
        return this.jwtSignatureAlgorithm;
    }
}
