package cn.nxtools.jwt.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ncq
 */
@ConfigurationProperties(prefix = "nxtools.security", ignoreInvalidFields = true)
public class SecurityServerProperties {
    /**
     * 不进行拦截url集合 多个之间使用,隔开
     */
    private String permitUrls = "";

    public String getPermitUrls() {
        return permitUrls;
    }

    public void setPermitUrls(String permitUrls) {
        this.permitUrls = permitUrls;
    }
}
