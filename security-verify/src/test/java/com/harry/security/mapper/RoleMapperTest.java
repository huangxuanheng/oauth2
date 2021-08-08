package com.harry.security.mapper;

import com.harry.security.entity.Menu;
import com.harry.security.entity.Role;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/8/1 0001
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void getRole(){
        List<Role> mapperRoleById = roleMapper.findRolesByUserId(1);
        System.out.println(mapperRoleById.size());
    }
    @Test
    public void getMenu(){
        List<Menu> mapperRoleById = menuMapper.findMenusByRoleId(1);
        System.out.println(mapperRoleById.size());
    }

}