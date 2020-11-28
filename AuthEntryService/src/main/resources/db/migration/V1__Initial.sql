CREATE TABLE `user` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(25) NOT NULL,
  `LAST_NAME` varchar(20) NOT NULL,
  `GENDER` varchar(20) NOT NULL,
  `AUTHORITIES` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(200) NOT NULL,
  `MOBILE` varchar(20) NOT NULL,
  `USERNAME` varchar(25) NOT NULL,
  `PASSWORD` varchar(64) NOT NULL,
  `ADDRESS` varchar(300) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `last_loggedin_time` datetime NOT NULL,
  `lastpasswordreset` datetime DEFAULT NULL,
  `enabled` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4393 DEFAULT CHARSET=utf8;
