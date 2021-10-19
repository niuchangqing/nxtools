package cn.nxtools.jwt.autoconfigure;

import cn.nxtools.common.StringUtil;
import cn.nxtools.common.base.Preconditions;
import cn.nxtools.jwt.JwtUtil;
import cn.nxtools.jwt.config.*;
import cn.nxtools.jwt.enums.SignatureAlgorithm;
import cn.nxtools.jwt.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (jwtServerProperties.getSignatureAlgorithm() == SignatureAlgorithm.HS256 ||
            jwtServerProperties.getSignatureAlgorithm() == SignatureAlgorithm.HS384 ||
            jwtServerProperties.getSignatureAlgorithm() == SignatureAlgorithm.HS512) {
            Preconditions.checkState(StringUtil.isNotEmpty(jwtServerProperties.getSecret()) && jwtServerProperties.getSecret().length() >= 4,"对称加密算法情况下,需配置[nxtools.jwt.secret]不能为空或长度小于4");
        } else {
            //非对称加密
            Preconditions.checkState(jwtSecretKey != null, "非对称加密算法情况,需配置共私钥。实现JwtSecretKey并注入容器中");
        }
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

    @Bean
    public AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler() {
        return new AuthenticationAccessDeniedHandler();
    }
}
