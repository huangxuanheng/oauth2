package com.harry.security.mapper;

import com.harry.security.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper//指定这是一个操作数据库的mapper
public interface UserMapper {

    User findUserByUsername(String username);
}