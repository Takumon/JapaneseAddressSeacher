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
 	`state_name` varchar(100) default NULL,
 	`state_kana` varchar(100) default NULL,
 	`city_name` varchar(100) default NULL,
 	`city_kana` varchar(100) default NULL,
 	`section_name` varchar(100) default NULL,
 	`section_kana` varchar(100) default NULL,
 	`section_memo` varchar(100) default NULL,
 	`kyoto_street` varchar(100) default NULL,
 	`block_name` varchar(100) default NULL,
 	`block_kana` varchar(100) default NULL,
 	`memo` varchar(1000) default NULL,
 	`office_name` varchar(500) default NULL,
 	`office_kana` varchar(500) default NULL,
 	`office_address` varchar(500) default NULL,
 	`new_id` int(11) default NULL,
   PRIMARY KEY  (`id`)
 	) DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS region;
CREATE TABLE region
(
  `region_id` int(2) NOT NULL,
  `region_name` varchar(100) default NULL,
  `region_kana` varchar(100) default NULL,
  PRIMARY KEY  (`region_id`)
) DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS state;
CREATE TABLE state
(
  `state_id` int(2) NOT NULL,
  `state_name` varchar(100) default NULL,
  `state_kana` varchar(100) default NULL,
  `region_id` int(2) NOT NULL,
  FOREIGN KEY (region_id) REFERENCES region(region_id),
  PRIMARY KEY  (`state_id`)
) DEFAULT CHARSET=utf8;
