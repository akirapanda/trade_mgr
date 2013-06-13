drop table if exists s_task;
drop table if exists s_user;

create table s_task (
	id bigint auto_increment,
	title varchar(128) not null,
	description varchar(255),
	user_id bigint not null,
    primary key (id)
) engine=InnoDB;

create table s_user (
	id bigint auto_increment,
	login_name varchar(64) not null unique,
	name varchar(64) not null,
	password varchar(255) not null,
	salt varchar(64) not null,
	roles varchar(255) not null,
	register_date timestamp not null default 0,
	primary key (id)
) engine=InnoDB;


CREATE  TABLE `s_lc` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `customer_name` VARCHAR(100) NOT NULL ,
  `customer_address` VARCHAR(255) NOT NULL ,
  `bene_name` VARCHAR(100) NOT NULL ,
  `bene_address` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`)
 )engine=InnoDB;


CREATE  TABLE s_company (
   id BIGINT NOT NULL AUTO_INCREMENT ,
   chinese_name VARCHAR(100) NULL ,
   chinese_address VARCHAR(255) NULL ,
   english_name VARCHAR(100) NULL ,
   english_address VARCHAR(255) NULL ,
   register_date timestamp not null default 0,
   update_date timestamp not null default 0,
  PRIMARY KEY (id)
  ) engine=InnoDB;
  
  

CREATE TABLE IF NOT EXISTS `s_contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) DEFAULT NULL,
  `contact_date` date DEFAULT NULL,
  `act_contact_date` date DEFAULT NULL,
  `buy_name` varchar(255) DEFAULT NULL,
  `sell_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

