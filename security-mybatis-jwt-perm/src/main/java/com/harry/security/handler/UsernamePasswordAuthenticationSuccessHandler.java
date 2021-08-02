package com.harry.security.handler;

import com.alibaba.fastjson.JSON;
import com.harry.security.entity.User;
import com.harry.security.utils.JWTUtils;
import com.harry.security.utils.MD5Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/7/31 0031
 */
@Component
public class UsernamePasswordAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Value("${harry.jwt.secret: huangxuanheng@163.com}")
    private String secret;

    @Value("${harry.jwt.expMillis: 7200000}")
    private long expMillis;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        User user= (User) principal;
        //生成token
        Map<String, Object> claims=new HashMap<>();
        claims.put("username",user.getUsername());
        claims.put("authorities",user.getAuthorities());
        claims.put("enabled",user.isEnabled());
        claims.put("expiresIn",(System.currentTimeMillis()+expMillis));
        claims.put("passwordModifyTarget", MD5Util.MD5(MD5Util.MD5(user.getPassword())));

        String token = JWTUtils.getAccessToken(secret, claims);

        Map<String,Object>result=new HashMap<>();
        result.put("accessToken",token);
        out.write(JSON.toJSONString(result));
        out.flush();
        out.close();
    }


}
