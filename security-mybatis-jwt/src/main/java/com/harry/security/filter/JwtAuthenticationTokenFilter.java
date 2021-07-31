package com.harry.security.filter;

import com.harry.security.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/7/31 0031
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    private String tokenHead = "Bearer ";

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!StringUtils.hasText(tokenValue)){
            filterChain.doFilter(request,response);
            return;
        }
        String token = tokenValue.substring(tokenHead.length());
        Map<String, Object> parseJWT = JWTUtils.parseToken(token);

        if(JWTUtils.isExpiresIn((long)parseJWT.get("expiresIn"))){
            //token 已经过期
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(request,response);
            return;
        }
        String username = (String) parseJWT.get("username");
        if(StringUtils.hasText(username)&& SecurityContextHolder.getContext().getAuthentication() == null){
            //正常用户
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(userDetails!=null&&userDetails.isEnabled()){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //设置用户登录状态
                log.info("authenticated user {}, setting security context",username);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}
