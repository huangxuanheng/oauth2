CREATE TABLE `h_user` (
                          `username` varchar(50) NOT NULL,
                          `password` varchar(500) NOT NULL,
                          `enabled` tinyint(1) NOT NULL,
                          PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


INSERT INTO `h_user` (`username`, `password`, `enabled`) VALUES ('harry', '123456', '1');
INSERT INTO `h_user` (`username`, `password`, `enabled`) VALUES ('mike', '123456', '1');
