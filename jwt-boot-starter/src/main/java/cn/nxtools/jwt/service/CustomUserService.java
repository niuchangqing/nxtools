package cn.nxtools.jwt.service;


import cn.nxtools.jwt.domain.CustomUserDetail;

/**
 * @author ncq
 */
public interface CustomUserService {

    CustomUserDetail loadByUsername(String username);
}
