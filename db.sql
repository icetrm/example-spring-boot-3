CREATE DATABASE IF NOT EXISTS `spring` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `spring`;

CREATE TABLE IF NOT EXISTS `role` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(50) NOT NULL,
    `create_author` VARCHAR(255) NOT NULL,
    `create_date` DATETIME NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `mobile` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `gender` VARCHAR(10) NOT NULL,
    `age` INT NOT NULL,
    `last_login` DATETIME NULL,
    `create_date` DATETIME NOT NULL DEFAULT NOW(),
    `role_id` BIGINT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(id)
);


INSERT INTO role (title, create_author) VALUES ('admin', 'system');
INSERT INTO role (title, create_author) VALUES ('guest', 'system');

INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `mobile`, `email`, `gender`, `age`, `last_login`, `create_date`, `role_id`) VALUES
(1, 'admin', '$2a$12$wVdmouWca7GPgB6m75vQMO1Rv3cPiPoGuGdjkw8jlgpkM2JAGn9OK', 'ธีxxxx', 'บุญxxx', '088280xxxx', 'th@gmail.com', 'M', 20, '2024-02-17 10:55:35', '2024-02-05 12:04:24', 1),
(2, 'guest1', '$2a$12$wVdmouWca7GPgB6m75vQMO1Rv3cPiPoGuGdjkw8jlgpkM2JAGn9OK', 'อxxx', 'สxxx', '088280xxxx', 'th@gmail.com', 'M', 30, NULL, '2024-02-17 11:04:13', 2);
