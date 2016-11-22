#CREATE DATABASE IF NOT EXISTS demo;

#USE demo;

CREATE TABLE IF NOT EXISTS resource(
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL DEFAULT '',
	sign varchar(25) NOT NULL DEFAULT '',
	parent_id int(11) NOT NULL DEFAULT 0,
	create_time datetime NOT NULL,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS sys_role(
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS user(
	id int(11) NOT NULL AUTO_INCREMENT,
	username varchar(36) NOT NULL,
	password varchar(36) NOT NULL,
	PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS third_party_info(
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	client_id varchar(255) NOT NULL,
	client_secret varchar(100) NOT NULL,
	redirect_url varchar(255) NOT NULL,
	create_time datetime NOT NULL,
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS token(
	id int(11) NOT NULL AUTO_INCREMENT,
	user_id int(11) NOT NULL,
	CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES user(id)
	ON DELETE CASCADE,
	access_token varchar(500) NOT NULL,
	refresh_token varchar(500) NOT NULL,
	avaliable bit NOT NULL DEFAULT 0,
	resource_ids varchar(500) NOT NULL,
	expires datetime NOT NULL,
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS role_resource(
	`role_id` int(11) NOT NULL,
	`resource_id` int(11) NOT NULL,
	PRIMARY KEY(`role_id`,`resource_id`),
	CONSTRAINT FOREIGN KEY (`role_id`) REFERENCES `sys_role`(`id`) 
	ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (`resource_id`) REFERENCES `resource`(`id`)
	ON DELETE CASCADE
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS order_info(
	id int(11) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(`id`),
	title varchar(200) NOT NULL,
	content text NOT NULL,
	create_time datetime NOT NULL,
	user_id int(11) NOT NULL,
	CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS user_role(
	role_id int(11) NOT NULL,
	CONSTRAINT FOREIGN KEY (role_id) REFERENCES sys_role(`id`)
	ON DELETE CASCADE,
	resource_id int(11) NOT NULL,
	CONSTRAINT FOREIGN KEY (resource_id) REFERENCES resource(`id`)
	ON DELETE CASCADE,
	PRIMARY KEY(`role_id`,`resource_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS auth_code(
	id int(11) NOT NULL AUTO_INCREMENT,
	code varchar(255) NOT NULL,
	expires datetime NOT NULL,
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
