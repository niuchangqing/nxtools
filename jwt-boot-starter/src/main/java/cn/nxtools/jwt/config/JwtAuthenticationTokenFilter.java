package cn.nxtools.jwt.config;

import cn.nxtools.common.StringUtil;
import cn.nxtools.jwt.JwtUtil;
import cn.nxtools.jwt.autoconfigure.JwtServerProperties;
import cn.nxtools.jwt.domain.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验token是否合法
 * @author ncq
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Autowired
    private JwtServerProperties jwtServerProperties;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //token信息校验
        String authToken = request.getHeader(jwtServerProperties.getHeader());
        String token = null;
        if (StringUtil.isNotEmpty(authToken)) {
            if (StringUtil.isNotNull(jwtServerProperties.getTokenPrefix()) && authToken.startsWith(jwtServerProperties.getTokenPrefix())) {
                token = authToken.replaceFirst(jwtServerProperties.getTokenPrefix(), StringUtil.EMPTY);
            }else {
                //无特定前缀token
                token = authToken;
            }
        }

        if (StringUtil.isNotEmpty(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
            token = token.trim();
            CustomUserDetail userDetail = jwtUtil.tokenToUser(token);
            if (jwtUtil.checkToken(token, userDetail)) {
                //token有效
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.debug("Authenticated user " + userDetail.getUserId() + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
