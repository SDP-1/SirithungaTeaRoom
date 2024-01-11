mysqlduplocationcreate database if not exists sirithunga_tea_room;
use sirithunga_tea_room;


CREATE TABLE `autobackuplocation` (
  `no` int DEFAULT '1',
  `location` text,
  UNIQUE KEY `AutoBackupLocation_no_uindex` (`no`)
) ;


CREATE TABLE `larstbackuplocation` (
  `no` int DEFAULT '1',
  `location` text,
  UNIQUE KEY `larstBackUpLocation_no_uindex` (`no`)
) ;

CREATE TABLE `mysqlduplocation` (
  `no` int DEFAULT '1',
  `location` text,
  UNIQUE KEY `mysqldupLocation_no_uindex` (`no`)
);

CREATE TABLE `mysqllocation` (
  `no` int DEFAULT '1',
  `location` text,
  UNIQUE KEY `mysqlLocation_no_uindex` (`no`)
)