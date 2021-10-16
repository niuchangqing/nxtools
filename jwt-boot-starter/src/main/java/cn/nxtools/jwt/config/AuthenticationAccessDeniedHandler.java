package cn.nxtools.jwt.config;

import cn.nxtools.common.JsonUtil;
import cn.nxtools.jwt.autoconfigure.SecurityServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private SecurityServerProperties securityServerProperties;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        //在登陆状态,权限不足时会抛出该异常
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        //可通过配置自定义返回权限不足信息
        String body = JsonUtil.toString(securityServerProperties.getAccessDenied());
        printWriter.write(body);
        printWriter.flush();
    }
}
