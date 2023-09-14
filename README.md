# user-info
This project is made on spring boot and uses spring-rest.It is used for managing user data.
It uses MySql for storing data.
Database name is user and there is single table name as user_info.
To create table use below mentioned query:-
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(80) NOT NULL,
  `last_name` varchar(80) DEFAULT NULL,
  `height` float DEFAULT NULL,
  `department` varchar(80) NOT NULL,
  `city` varchar(80) NOT NULL,
  `bank_currency` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
