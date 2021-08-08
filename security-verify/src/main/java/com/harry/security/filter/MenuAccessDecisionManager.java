package com.harry.security.filter;

import com.harry.security.entity.Role;
import com.harry.security.entity.User;
import com.harry.security.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/8/1 0001
 */
@Slf4j
@Component
public class MenuAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
// 当前请求需要的权限
        log.info("collection:{}", collection);
        // 当前用户所具有的权限
        log.info("principal:{} authorities:{}", authentication.getPrincipal().toString());
        Object principal = authentication.getPrincipal();
        if(principal instanceof String){
            throw new BadCredentialsException("未登录");
        }

        List<Role> roleList=null;
        for (ConfigAttribute configAttribute : collection) {
            // 当前请求需要的权限
            String needRole = configAttribute.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)) {
                return;
            }

            // 当前用户所具有的权限
            if(roleList==null){
                User loginUser= (User) authentication.getPrincipal();
                roleList = roleMapper.findRolesByUserId(loginUser.getId());
            }
            for (GrantedAuthority grantedAuthority : roleList) {
                // 包含其中一个角色即可访问
                if (grantedAuthority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("SimpleGrantedAuthority!!");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
