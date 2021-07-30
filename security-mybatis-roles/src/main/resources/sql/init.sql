CREATE TABLE `h_user` (
                          `username` varchar(50) NOT NULL,
                          `password` varchar(500) NOT NULL,
                          `enabled` tinyint(1) NOT NULL,
                          PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `h_authorities` (
                                 `username` varchar(50) NOT NULL,
                                 `authority` varchar(50) NOT NULL,
                                 UNIQUE KEY `ix_auth_username` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


INSERT INTO `h_user` (`username`, `password`, `enabled`) VALUES ('harry', '123456', '1');
INSERT INTO `h_user` (`username`, `password`, `enabled`) VALUES ('mike', '123456', '1');


INSERT INTO `h_authorities` (`username`, `authority`) VALUES ('harry', 'admin');
INSERT INTO `h_authorities` (`username`, `authority`) VALUES ('harry', 'user');
INSERT INTO `h_authorities` (`username`, `authority`) VALUES ('mike', 'user');
