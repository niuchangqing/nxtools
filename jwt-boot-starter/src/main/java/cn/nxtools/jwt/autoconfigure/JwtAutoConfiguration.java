package cn.nxtools.jwt.autoconfigure;

import cn.nxtools.jwt.JwtUtil;
import cn.nxtools.jwt.config.JwtAuthenticationEntryPoint;
import cn.nxtools.jwt.config.JwtAuthenticationTokenFilter;
import cn.nxtools.jwt.config.RestAuthenticationAccessDeniedHandler;
import cn.nxtools.jwt.config.WebSecurityConfig;
import cn.nxtools.jwt.service.CustomUserDetailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ncq
 */
@Configuration
@ConditionalOnProperty(name = "nxtools.jwt.enabled", matchIfMissing = true)
@EnableConfigurationProperties({JwtServerProperties.class, SecurityServerProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtAutoConfiguration {


    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public WebSecurityConfig webSecurityConfig() {
        return new WebSecurityConfig();
    }

    @Bean
    public RestAuthenticationAccessDeniedHandler restAuthenticationAccessDeniedHandler() {
        return new RestAuthenticationAccessDeniedHandler();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public CustomUserDetailService customUserDetailService() {
        return new CustomUserDetailService();
    }

    @Bean
    @ConditionalOnMissingBean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
