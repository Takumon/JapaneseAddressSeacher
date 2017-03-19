DROP TABLE IF EXISTS address;
CREATE TABLE address
 	(
 	`id` int(9) NOT NULL default 0,
 	`state_id` int(2) default NULL,
 	`city_id` int(5) default NULL,
 	`section_id` int(9) default NULL,
 	`address_zip_code` varchar(8) default NULL,
 	`office_flg` tinyint(1) default NULL,
 	`delete_flg` tinyint(1) default NULL,
 	`state_name` varchar(8) default NULL,
 	`state_kana` varchar(8) default NULL,
 	`city_name` varchar(24) default NULL,
 	`city_kana` varchar(24) default NULL,
 	`section_name` varchar(32) default NULL,
 	`section_kana` varchar(32) default NULL,
 	`section_memo` varchar(16) default NULL,
 	`kyoto_street` varchar(32) default NULL,
 	`block_name` varchar(64) default NULL,
 	`block_kana` varchar(64) default NULL,
 	`memo` varchar(255) default NULL,
 	`office_name` varchar(255) default NULL,
 	`office_kana` varchar(255) default NULL,
 	`office_address` varchar(255) default NULL,
 	`new_id` int(11) default NULL, PRIMARY KEY  (`id`)
 	) DEFAULT CHARSET=utf8;