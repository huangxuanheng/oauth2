package com.harry.security.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/7/27 0027
 */
public class Authority implements GrantedAuthority {
    private String username;
    //权限名称
    private String authority;
    @Override
    public String getAuthority() {
        return "ROLE_"+authority;
    }
}
