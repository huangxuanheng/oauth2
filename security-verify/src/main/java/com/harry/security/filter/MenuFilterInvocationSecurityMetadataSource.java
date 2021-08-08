package com.harry.security.filter;

import com.harry.security.entity.Menu;
import com.harry.security.entity.Role;
import com.harry.security.mapper.MenuMapper;
import com.harry.security.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/8/1 0001
 */
@Component
@Slf4j
public class MenuFilterInvocationSecurityMetadataSource extends DefaultFilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;

    //主要存储白名单
    private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
    /**
     * Sets the internal request map from the supplied map. The key elements should be of
     * type {@link RequestMatcher}, which. The path stored in the key will depend on the
     * type of the supplied UrlMatcher.
     *
     * @param requestMap order-preserving map of request definitions to attribute lists
     */
    public MenuFilterInvocationSecurityMetadataSource(LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
        super(requestMap);
        this.requestMap=requestMap;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        int count = 0;
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : this.requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
            else {
                if (this.logger.isTraceEnabled()) {
                    this.logger.trace(LogMessage.format("Did not match request to %s - %s (%d/%d)", entry.getKey(),
                            entry.getValue(), ++count, this.requestMap.size()));
                }
            }
        }


        Set<ConfigAttribute> set = new HashSet<>();
        // 获取请求地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        log.info("requestUrl >> {}", requestUrl);
        List<Menu> allMenus = menuMapper.findAllMenus();
        if (!CollectionUtils.isEmpty(allMenus)) {
            List<String> urlList = allMenus.stream().filter(f->f.getUrl().endsWith("**")?requestUrl.startsWith(f.getUrl().substring(0,f.getUrl().lastIndexOf("/"))):requestUrl.equals(f.getUrl())).map(menu -> menu.getUrl()).collect(Collectors.toList());
            for (String url:urlList){
                List<Role> roles = roleMapper.findRolesByUrl(url); //当前请求需要的权限
                if(!CollectionUtils.isEmpty(roles)){
                    roles.forEach(role -> {
                        SecurityConfig securityConfig = new SecurityConfig(role.getAuthority());
                        set.add(securityConfig);
                    });
                }
            }

        }

        if (ObjectUtils.isEmpty(set)) {
            return SecurityConfig.createList("ROLE_LOGIN");
        }

        return set;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }


}
