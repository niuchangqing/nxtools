package cn.nxtools.jwt.autoconfigure;

import cn.nxtools.common.collect.Maps;
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
    private Map<String, String> authFailureResp = new HashMap<String, String>(){{
        put("code", "401");
        put("message", "authentication failed");
    }};

    public String getPermitUrls() {
        return permitUrls;
    }

    public void setPermitUrls(String permitUrls) {
        this.permitUrls = permitUrls;
    }

    public Map<String, String> getAuthFailureResp() {
        return authFailureResp;
    }

    public void setAuthFailureResp(Map<String, String> authFailureResp) {
        this.authFailureResp = authFailureResp;
    }
}
