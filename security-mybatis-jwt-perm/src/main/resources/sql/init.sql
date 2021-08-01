CREATE TABLE `h_user` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `username` varchar(50) NOT NULL COMMENT '用户名',
                          `password` varchar(500) NOT NULL COMMENT '密码',
                          `enabled` tinyint(1) NOT NULL COMMENT '是否启动，0-不启用，1-启用',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT '用户表';

CREATE TABLE `h_role` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `name` varchar(50) NOT NULL COMMENT '角色名称',
                                 `code` varchar(50) NOT NULL COMMENT '角色编码',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT '角色表';


CREATE TABLE `h_menu` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(50) NOT NULL COMMENT '菜单名称',
                          `url` varchar(200) NOT NULL COMMENT '菜单URL',
                          `parent_id` int NOT NULL default 0 COMMENT '上级菜单id',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT '菜单表';



CREATE TABLE `h_role_menu` (
                               `role_id` int NOT NULL COMMENT '角色id',
                               `menu_id` int NOT NULL COMMENT '菜单id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT '角色菜单表';


CREATE TABLE `h_role_user` (
                               `role_id` int NOT NULL COMMENT '角色id',
                               `user_id` int NOT NULL COMMENT '菜单id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT '角色用户表';


INSERT INTO `h_user` (`username`, `password`, `enabled`) VALUES ('harry', '{bcrypt}$2a$10$gQv1oUFK/LvbV7p4Nk0xE.Gn8H1lYV1hqVJfReWSUYUQBfCkGq2uy', '1');
INSERT INTO `h_user` (`username`, `password`, `enabled`) VALUES ('mike', '{bcrypt}$2a$10$gQv1oUFK/LvbV7p4Nk0xE.Gn8H1lYV1hqVJfReWSUYUQBfCkGq2uy', '1');


INSERT INTO `h_role` (`name`, `code`) VALUES ('管理员', 'admin'),('普通用户','user');

INSERT INTO `h_menu` (`name`, `url`) VALUES ('后台管理', '/admin'),('用户管理','/user');

INSERT INTO `h_role_menu` (`role_id`, `menu_id`) VALUES (1, 1),(1,2),(2,2);


INSERT INTO `h_role_user` (`role_id`, `user_id`) VALUES (1, 1),(1,2),(2,2);



