package cn.nxtools.jwt.demo.controller;

import cn.nxtools.jwt.domain.CustomUserDetail;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController extends BaseController {
    @GetMapping("/query")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String query(@AuthenticationPrincipal CustomUserDetail userDetail) {
        System.out.println(userDetail.getUserId());
        return "success";
    }
}
