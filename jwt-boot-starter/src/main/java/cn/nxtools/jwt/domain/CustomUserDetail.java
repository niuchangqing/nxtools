package cn.nxtools.jwt.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Map;

/**
 * @author ncq
 */
public class CustomUserDetail implements UserDetails {

    /**
     * 权限集合
     */
    private Collection<? extends GrantedAuthority> authorities;

    private String username;

    /**
     * 用户唯一标识
     */
    private String userId;

    private String password;

    /**
     * 是否启用,默认等于true
     */
    private boolean enabled = true;

    /**
     * 自定义字段
     */
    private Map<String, String> attached;

    public CustomUserDetail(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getUserId() {
        return this.userId;
    }

    public Map<String, String> getAttached() {
        return attached;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAttached(Map<String, String> attached) {
        this.attached = attached;
    }

    /**
     * 获取Long类型用户ID
     * @return      Long类型用户ID
     */
    public Long getUserIdLong() {
        return Long.valueOf(this.userId);
    }
}
