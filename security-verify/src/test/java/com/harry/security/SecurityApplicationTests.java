package com.harry.security;

import com.alibaba.fastjson.JSON;
import com.harry.security.utils.MD5Util;
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

        Jwt decode2 = JwtHelper.decode(token);
        Jwt decode = JwtHelper.decode("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYXBpIl0sImNvcnBJZCI6bnVsbCwidXNlcl9pZCI6IjJjMmM4MDg1NzcyMzdiYjYwMTc3MzMyYmFjM2UwODljIiwidXNlcl9uYW1lIjoibGl1amlhbnFpYW5nIiwic2NvcGUiOlsicmVhZCJdLCJtb2JpbGUiOmZhbHNlLCJpc0FkbWluIjp0cnVlLCJleHAiOjE2Mjc4OTU2NDksImlzQXBwQWRtaW4iOmZhbHNlLCJhdXRob3JpdGllcyI6WyJVU0VSIiwiQVVUSF9TWVNURU1fTUFOQUdFIl0sImp0aSI6IjFhOTNiOGQ0LTVkMzctNDMxMS04ZGFjLTIyOWZlNmExNjFkMSIsImNsaWVudF9pZCI6ImFwaSJ9.IkdpCIed5tcRNLCZIfdNdFDM6xtKZExA33cDuyNf22i-Gb1czIKzPv_31esafh8gWyxdIpm8nT91S0_LW2FZSvMHBtQcr0Yo7Dpow2YUm5o3GGC4gKSarpQfqawMhVo-VDZuwb3nZOWvaDQzX-1fSJ9ZCv9Lx6Y3o7L45fyt5S2B1dCODzL_2BlJ2BPSiyvdWd8VAawvgwUc1Pmj-PJrtWBwW8q81svMgR32ZjfKet4WSBHOufIdGCmgP6pSRuKB7bklOGK1DpjR5bZmeMHjsSuDcJSL7Gpmak4YoelHM3v0rotrER96-NCTQN8cG-24OY1sPLz4Vm1PsS67izr5Fw");
        System.out.println(decode.getClaims());
        System.out.println(decode2.getClaims());

        String md5 = MD5Util.MD5("{bcrypt}$2a$10$gfIl.MUC2aT8LugeLCyE7O48zjFeKAbBgfSboTmGy1gHpgb.bUt5m");
        System.out.println(md5);
    }

}
