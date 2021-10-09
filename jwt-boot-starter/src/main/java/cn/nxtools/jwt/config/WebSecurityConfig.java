package cn.nxtools.jwt.config;

import cn.nxtools.common.base.Splitter;
import cn.nxtools.jwt.autoconfigure.SecurityServerProperties;
import cn.nxtools.jwt.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static cn.nxtools.common.StringUtil.isEmpty;

/**
 * @author ncq
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private SecurityServerProperties securityServerProperties;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private CustomUserDetailService customUserDetailsService;

    private String[] extralPermitUrls = null;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (this.extralPermitUrls == null) {
            if (isEmpty(securityServerProperties.getPermitUrls())) {
                extralPermitUrls = new String[0];
            } else {
                extralPermitUrls = Splitter.on(",").split(securityServerProperties.getPermitUrls());
            }
        }
        // 由于使用的是JWT，我们这里不需要csrf
        http.csrf().disable();
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //对于指定过滤的api 允许匿名访问
                .antMatchers(extralPermitUrls).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        // 禁用缓存
        http.headers().cacheControl();
        // 解决不允许显示在iframe的问题
        http.headers().frameOptions().disable();
        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(customUserDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(bCryptPasswordEncoder);
    }
}
