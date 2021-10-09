package cn.nxtools.jwt.domain;

/**
 * @author ncq
 */
public class JwtTokenDto {
    /**
     * token
     */
    private String access_token;

    /**
     * 刷新token
     */
    private String refresh_token;

    /**
     * token超时时间,秒时间戳
     * 具体到指定时间
     */
    private Long expires_in;

    /**
     * 刷新token超时时间,秒时间戳
     * 具体到指定时间
     */
    private Long refresh_expires_in;

    /**
     * token的唯一Id
     */
    private String jti;

    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public Long getRefresh_expires_in() {
        return refresh_expires_in;
    }

    public String getJti() {
        return jti;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public void setRefresh_expires_in(Long refresh_expires_in) {
        this.refresh_expires_in = refresh_expires_in;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }
}
