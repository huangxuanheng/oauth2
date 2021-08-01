package com.harry.security.entity;

import lombok.Data;

/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/8/1 0001
 */
@Data
public class Menu {
    //菜单id
    private int id;
    //菜单名称
    private String name;
    //菜单URL
    private String url;
    //上级菜单id
    private int parentId;
}
