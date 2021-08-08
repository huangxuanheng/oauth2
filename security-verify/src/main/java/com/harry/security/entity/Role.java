package com.harry.security.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/7/27 0027
 */
public class Role implements GrantedAuthority {
    private int id;

    private String name;
    //权限编码
    private String code;

    @Override
    public String getAuthority() {
        return "ROLE_"+code;
    }
}
