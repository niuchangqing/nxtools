package cn.nxtools.jwt.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ncq
 */
@ConfigurationProperties(prefix = "nxtools.security", ignoreInvalidFields = true)
public class SecurityServerProperties {
    /**
     * 不进行拦截url集合 多个之间使用,隔开
     */
    private String permitUrls = "";

    /**
     * 认证失败响应内容
     */
    private Map<String, String> authFailure = new HashMap<String, String>(){{
        put("code", "401");
        put("message", "authentication failed");
    }};

    /**
     * 权限不足响应内容
     */
    private Map<String, String> accessDenied = new HashMap<String, String>(){{
        put("code", "403");
        put("message", "access denied");
    }};

    public String getPermitUrls() {
        return permitUrls;
    }

    public void setPermitUrls(String permitUrls) {
        this.permitUrls = permitUrls;
    }

    public Map<String, String> getAuthFailure() {
        return authFailure;
    }

    public void setAuthFailure(Map<String, String> authFailure) {
        this.authFailure = authFailure;
    }

    public Map<String, String> getAccessDenied() {
        return accessDenied;
    }

    public void setAccessDenied(Map<String, String> accessDenied) {
        this.accessDenied = accessDenied;
    }
}
