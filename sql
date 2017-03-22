CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `salt` varchar(128) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `role_code` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `role_permissions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_code` VARCHAR(45) NULL,
  `permission_code` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `role` (
  `role_code` VARCHAR(45) NOT NULL,
  `role_name` VARCHAR(45) NULL,
  PRIMARY KEY (`role_code`));

CREATE TABLE `permission` (
  `permission_code` VARCHAR(45) NOT NULL,
  `permission_name` VARCHAR(45) NULL,
  PRIMARY KEY (`permission_code`));

