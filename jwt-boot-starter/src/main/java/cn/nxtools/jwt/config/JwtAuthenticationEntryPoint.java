package cn.nxtools.jwt.config;

import cn.nxtools.jwt.exception.NotLoginException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ncq
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //未登陆状态会进入该方法
        if (!response.isCommitted()) {
            throw new NotLoginException("not logged in");
        }
    }
}
