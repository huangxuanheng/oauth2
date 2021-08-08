package com.harry.security.mapper;

import com.harry.security.entity.Menu;
import com.harry.security.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper//指定这是一个操作数据库的mapper
public interface MenuMapper {

    /**
     * 根据角色id查找菜单
     * @param roleId
     * @return
     */
    List<Menu> findMenusByRoleId(int roleId);

    /**
     * 查询所有配置菜单
     * @return
     */
    List<Menu> findAllMenus();

}