package cn.nxtools.jwt.demo.controller;

import cn.nxtools.common.collect.Lists;
import cn.nxtools.jwt.domain.CustomUserDetail;
import cn.nxtools.jwt.domain.JwtTokenDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class LoginController extends BaseController{
    /**
     * 登录
     * 方便测试此处使用get请求
     */
    @GetMapping("/login")
    public JwtTokenDto login() {
        //设置权限
        List<SimpleGrantedAuthority> authorities = Lists.newArrayListWithSize(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        CustomUserDetail userDetail = new CustomUserDetail("admin", authorities);
        userDetail.setUserId("1");
        JwtTokenDto jwtToken = jwtUtil.generateJwtTokenDto(userDetail);
        return jwtToken;
    }
}
