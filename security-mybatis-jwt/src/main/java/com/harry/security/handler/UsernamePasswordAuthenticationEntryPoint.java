package com.harry.security.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/7/31 0031
 */
@Component
public class UsernamePasswordAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("WWW-Authenticate", "Bearer");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter out = response.getWriter();
        Map<String,Object> data = new HashMap<>();
        data.put("path", request.getRequestURI());
        data.put("time", LocalDateTime.now().toString());
        data.put("errCode", HttpStatus.UNAUTHORIZED.value());
        data.put("errMsg", HttpStatus.UNAUTHORIZED.getReasonPhrase());
        out.write(JSON.toJSONString(data));
        out.flush();
        out.close();
    }
}
