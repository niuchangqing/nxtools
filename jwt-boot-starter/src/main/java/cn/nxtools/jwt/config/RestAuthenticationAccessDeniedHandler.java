package cn.nxtools.jwt.config;

import cn.nxtools.common.IOUtil;
import cn.nxtools.jwt.exception.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ncq
 */
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = Logger.getLogger(RestAuthenticationAccessDeniedHandler.class.getName());

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.log(Level.WARNING,"权限不足");
        throw new AccessDeniedException("access denied", accessDeniedException);
    }
}
