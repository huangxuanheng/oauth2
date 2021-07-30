package com.harry.security.mapper;

import com.harry.security.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/7/27 0027
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User harry = userMapper.findUserByUsername("harry");
        System.out.println(harry);
    }

    @Test
    public void testPasswordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
        encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        DelegatingPasswordEncoder encoder1 = new DelegatingPasswordEncoder("bcrypt", encoders);
        DelegatingPasswordEncoder encoder2 = new DelegatingPasswordEncoder("MD5", encoders);
        DelegatingPasswordEncoder encoder3 = new DelegatingPasswordEncoder("SHA-256", encoders);
        DelegatingPasswordEncoder encoder4 = new DelegatingPasswordEncoder("noop", encoders);
        String e1 = encoder1.encode("123456");
        String e2 = encoder2.encode("123456");
        String e3 = encoder3.encode("123456");
        String e4 = encoder4.encode("123456");
        System.out.println("e1 = " + e1);
        System.out.println("e2 = " + e2);
        System.out.println("e3 = " + e3);
        System.out.println("e4 = " + e4);
    }
}