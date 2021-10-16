package cn.nxtools.jwt.config;

import cn.nxtools.common.JsonUtil;
import cn.nxtools.jwt.autoconfigure.SecurityServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ncq
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private SecurityServerProperties securityServerProperties;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //未登陆/登陆token失效会进入该方法
        if (!response.isCommitted()) {
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            String body = JsonUtil.toString(securityServerProperties.getAuthFailure());
            printWriter.write(body);
            printWriter.flush();
        }
    }
}
