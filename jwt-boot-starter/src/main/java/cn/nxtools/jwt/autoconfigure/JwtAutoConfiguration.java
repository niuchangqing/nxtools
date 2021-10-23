package cn.nxtools.jwt.autoconfigure;

import cn.nxtools.common.StringUtil;
import cn.nxtools.common.base.Preconditions;
import cn.nxtools.jwt.JwtUtil;
import cn.nxtools.jwt.config.*;
import cn.nxtools.jwt.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ncq
 */
@Configuration
@ConditionalOnProperty(name = "nxtools.jwt.enabled", matchIfMissing = true)
@EnableConfigurationProperties({JwtServerProperties.class, SecurityServerProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtAutoConfiguration {

    @Autowired
    private JwtServerProperties jwtServerProperties;

    @Autowired(required = false)
    private JwtSecretKey jwtSecretKey;


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
    public JwtUtil jwtUtil() {
        //jwt配置参数校验
        if (jwtServerProperties.getSignatureAlgorithm().isHmac()) {
            //校验secret字符串是否为空或长度小于4
            boolean flag = StringUtil.isNotEmpty(jwtServerProperties.getSecret()) && jwtServerProperties.getSecret().length() >= 4;
            Preconditions.checkState(flag ,String.format("使用%s签名,需配置[nxtools.jwt.secret]不能为空或长度小于4", jwtServerProperties.getSignatureAlgorithm()));
        } else if (jwtServerProperties.getSignatureAlgorithm().isNone()) {
            //无需校验
        } else {
            //使用公私钥签名,需要初始化公私钥配置
            Preconditions.checkState(jwtSecretKey != null, String.format("使用%s签名,需配置公私钥。实现JwtSecretKey接口并注入容器中", jwtServerProperties.getSignatureAlgorithm()));
        }
        return new JwtUtil();
    }

    @Bean
    public CustomUserDetailService customUserDetailService() {
        return new CustomUserDetailService();
    }

    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler() {
        return new AuthenticationAccessDeniedHandler();
    }
}
