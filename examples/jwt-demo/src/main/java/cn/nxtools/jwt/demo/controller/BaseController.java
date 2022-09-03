package cn.nxtools.jwt.demo.controller;

import cn.nxtools.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class BaseController {

    @Autowired
    protected JwtUtil jwtUtil;
}
