package com.harry.security;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SecurityApplicationTests {
    @Value("${harry.jwt.secret: huangxuanheng@163.com}")
    private String secret;
    @Test
    void contextLoads() {
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println(encode);
    }
    @Test
    void testJwt() {
        Map<String, Object> claums=new HashMap<>();
        claums.put("username","mike");
        claums.put("mobile","15994643438");
        claums.put("expires_in",14400000);

        MacSigner rsaSigner=new MacSigner(secret);
        Jwt encode = JwtHelper.encode(JSON.toJSONString(claums), rsaSigner);
        String token = encode.getEncoded();
        System.out.println(token);
        String claims = encode.getClaims();
        System.out.println(claims);

        Jwt decode = JwtHelper.decode(token);
        System.out.println(decode.getClaims());


    }

}
