## 使用
### Maven
```xml
<dependency>
    <groupId>cn.nxtools</groupId>
    <artifactId>jwt-boot-starter</artifactId>
    <version>1.0.2</version>
</dependency>
```
### Gradle
```groovy
implementation 'cn.nxtools:jwt-boot-starter:1.0.2'
```

## 配置参数
|参数|默认值|描述|
|-----|-------|-----|
|nxtools.jwt.enabled|true|是否默认启动,默认引入jar即可启动|
|nxtools.jwt.secret|null|生成/解析Token密钥,长度要大于等于4。仅对称加密算法时该字段有效,其他算法可以为空|
|nxtools.jwt.expiration|86400|token超时时间,单位秒|
|nxtools.jwt.signatureAlgorithm|SignatureAlgorithm.NONE|jwt token签名算法|
|nxtools.jwt.refreshExpiration|2592000|refresh token超时时间,单位秒|
|nxtools.jwt.header|Authorization|token通过http header传输时的key值|
|nxtools.jwt.tokenPrefix|null|token值固定前缀|
|nxtools.jwt.enabledLogout|false|是否启用注销功能。如果为true, 在调用退出方法时,会进行保存退出数据。支持redis和内存俩种方式,有redis优先redis否则存储内存|
|nxtools.jwt.logoutAllClients|false|是否注销全部客户端token,默认只失效当前token。enabledLogout=true时,该字段才会生效。为true时,一个客户端退出登陆,所有客户端的token都将失效。支持redis和内存俩种方式,有redis优先redis否则存储内存|
|nxtools.jwt.refreshTokenPermissions|ROLE_REFRESH_TOKEN_PERMISSIONS|refresh_token 权限字符串。限制refresh_token的权限，使refresh_token只能用来重置access_token。如: @PreAuthorize("hasAnyRole('ROLE_REFRESH_TOKEN_PERMISSIONS')")|
|nxtools.security.permitUrls|null|不进行拦截url集合 多个之间使用,隔开。如: /login,/internal/**|
|nxtools.security.authFailure|{"code": "401","message": "authentication failed"}|认证失败响应json格式内容|

## 配置样例
```yaml
nxtools:
  jwt:
    secret: test
    signature-algorithm: hs256
    expiration: 1800
  security:
    permit-urls: /user/login,/data/list/*
```

## 生成token 样例
```java
import cn.nxtools.jwt.JwtUtil;
import cn.nxtools.jwt.domain.CustomUserDetail;
import cn.nxtools.jwt.domain.JwtTokenDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    public JwtTokenDto login(LoginRequest request) {
        //校验用户信息密码是否匹配等,该步骤忽略
        //设置权限
        List<SimpleGrantedAuthority> authorities = Lists.newArrayListWithSize(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        CustomUserDetail userDetail = new CustomUserDetail("username", authorities);
        userDetail.setUserId(user.getId());
        JwtTokenDto jwtToken = jwtUtil.generateJwtTokenDto(userDetail)
        return jwtToken;
    }
}
```

## token公私钥方式(示例为RSA方式)
```java
import cn.nxtools.jwt.config.JwtSecretKey;
import io.jsonwebtoken.impl.Base64Codec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
public class SpringConfig implements JwtSecretKey {

    //公钥字符串
    @Value("${test.key.public}")
    private String publicKeyStr;

    //私钥字符串
    @Value("${test.key.private}")
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
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Key getPrivateKey() {
        try {
            return getRsaPrivate();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```