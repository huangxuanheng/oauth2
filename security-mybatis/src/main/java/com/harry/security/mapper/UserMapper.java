package com.harry.security.mapper;

import com.harry.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper//指定这是一个操作数据库的mapper
public interface UserMapper {

    @Select("select * from h_user where username=#{username}")
    User findUserByUsername(String username);
}