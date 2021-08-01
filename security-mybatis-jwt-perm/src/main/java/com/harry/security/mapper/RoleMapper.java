package com.harry.security.mapper;

import com.harry.security.entity.Role;
import com.harry.security.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper//指定这是一个操作数据库的mapper
public interface RoleMapper {
    /**
     * 根据用户id查找角色
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(int userId);

    /**
     * 根据URL查找角色
     * @param url
     * @return
     */
    List<Role> findRolesByUrl(String url);
}