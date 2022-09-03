package cn.nxtools.jwt.autoconfigure;

import cn.nxtools.common.collect.Maps;
import cn.nxtools.jwt.JwtUtil;
import cn.nxtools.jwt.enums.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author ncq
 */
@ConfigurationProperties(prefix = "nxtools.jwt", ignoreInvalidFields = true)
public class JwtServerProperties {

    /**
     * jwt配置是否生效,为false不会进行启动
     */
    private Boolean enabled = true;

    /**
     * 生成/解析Token密钥
     * 仅对称加密算法时该字段有效, 对称加密方式必须有值
     */
    private String secret;

    /**
     * token超时时间,单位秒
     */
    private Long expiration = 86400L;

    /**
     * refresh token超时时间,单位秒
     */
    private Long refreshExpiration = 2592000L;

    /**
     * token通过header传入时的key值
     */
    private String header = "Authorization";

    /**
     * token值固定前缀
     */
    private String tokenPrefix;

    /**
     * 是否启用注销功能
     * 如果为true, 在调用退出方法时,会进行保存退出数据
     * 支持redis和内存俩种方式,有redis优先redis否则存储内存
     */
    private Boolean enabledLogout = false;

    /**
     * 是否注销全部客户端token,默认只失效当前token。
     * enabledLogout=true时,该字段才会生效
     * 为true时,一个客户端退出登陆,所有客户端的token都将失效
     * 支持redis和内存俩种方式,有redis优先redis否则存储内存
     */
    private Boolean logoutAllClients = false;

    /**
     * jwt token签名算法枚举
     */
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.NONE;

    /**
     * refresh_token 权限字符串
     * 限制refresh_token的权限，使refresh_token只能用来重置access_token
     * @PreAuthorize("hasAnyRole('ROLE_REFRESH_TOKEN_PERMISSIONS')")
     */
    private String refreshTokenPermissions = JwtUtil.REFRESH_TOKEN_PERMISSIONS;

    /**
     * 自定义token中的claims内容
     */
    private Map<String, Object> claims = Maps.newHashMap();

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public Long getRefreshExpiration() {
        return refreshExpiration;
    }

    public String getHeader() {
        return header;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public void setRefreshExpiration(Long refreshExpiration) {
        this.refreshExpiration = refreshExpiration;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Boolean getEnabledLogout() {
        return this.enabledLogout;
    }

    public void setEnabledLogout(Boolean enabledLogout) {
        this.enabledLogout = enabledLogout;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public Boolean getLogoutAllClients() {
        return this.logoutAllClients;
    }

    public void setLogoutAllClients(Boolean logoutAllClients) {
        this.logoutAllClients = logoutAllClients;
    }

    public String getRefreshTokenPermissions() {
        return this.refreshTokenPermissions;
    }

    public void setRefreshTokenPermissions(String refreshTokenPermissions) {
        this.refreshTokenPermissions = refreshTokenPermissions;
    }

    public Map<String, Object> getClaims() {
        return this.claims;
    }

    public void setClaims(Map<String, Object> claims) {
        this.claims = claims;
    }
}
