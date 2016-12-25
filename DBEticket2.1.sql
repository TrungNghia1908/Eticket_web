-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.17 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ebusticket
DROP DATABASE IF EXISTS `ebusticket`;
CREATE DATABASE IF NOT EXISTS `ebusticket` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ebusticket`;

-- Dumping structure for table ebusticket.bus
DROP TABLE IF EXISTS `bus`;
CREATE TABLE IF NOT EXISTS `bus` (
  `Bus_id` int(11) NOT NULL AUTO_INCREMENT,
  `Bus_no` int(11) NOT NULL,
  `Trip_id` int(11) NOT NULL DEFAULT '0',
  `Driver_name` varchar(50) DEFAULT NULL,
  `Run_time` time DEFAULT NULL,
  PRIMARY KEY (`Bus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table ebusticket.bus: ~5 rows (approximately)
DELETE FROM `bus`;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` (`Bus_id`, `Bus_no`, `Trip_id`, `Driver_name`, `Run_time`) VALUES
	(1, 12345, 1, 'Tuan Nguyen', '18:00:00'),
	(2, 12367, 1, 'Viet Truong', '04:00:00'),
	(3, 22434, 1, 'Nghia Nguyen', '20:00:00'),
	(4, 23532, 1, 'Meside', '12:00:00'),
	(5, 33565, 1, 'Linh Tran', '16:00:00');
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;

-- Dumping structure for table ebusticket.credit_card
DROP TABLE IF EXISTS `credit_card`;
CREATE TABLE IF NOT EXISTS `credit_card` (
  `Card_id` int(11) NOT NULL,
  `Amount` int(11) DEFAULT '10000',
  PRIMARY KEY (`Card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ebusticket.credit_card: ~3 rows (approximately)
DELETE FROM `credit_card`;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
INSERT INTO `credit_card` (`Card_id`, `Amount`) VALUES
	(555555, 1000),
	(123456789, 2000),
	(1122334455, 185000);
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;

-- Dumping structure for table ebusticket.customer
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `Cus_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `User_name` varchar(50) DEFAULT NULL,
  `Cus_email` varchar(50) DEFAULT NULL,
  `Cus_name` varchar(50) DEFAULT NULL,
  `Cus_phone` int(11) DEFAULT NULL,
  `Cus_pass` varchar(50) DEFAULT NULL,
  `Cus_hashSaltPass` varchar(100) DEFAULT NULL,
  `Cus_salt` varchar(100) DEFAULT NULL,
  `Cus_valid` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`Cus_id`),
  UNIQUE KEY `User_name` (`User_name`),
  UNIQUE KEY `Cus_email` (`Cus_email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Dumping data for table ebusticket.customer: ~11 rows (approximately)
DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`Cus_id`, `User_name`, `Cus_email`, `Cus_name`, `Cus_phone`, `Cus_pass`, `Cus_hashSaltPass`, `Cus_salt`, `Cus_valid`) VALUES
	(1, 'Linh', 'north@gmail.com', 'Linh', 999, '123', 'a8d0fee1f9befe282a58ddac70930b2e3350bdcf2fbe1c7e8920d40470b5bfb6', 'rs4as73m8frgpqrtied48epbda', 'Y'),
	(3, 'Viet Nam', 'viet@gmail.com', 'Truong Anh Viet', 12347768, '9900', '667a40abec6fc061f8e7f9ac8cfc5f65f88eebecf89d22ea32c5b410cd6480ec', 'guaipt1soa0g4cctmmkdds0ig4', 'Y'),
	(4, 'An Tran', 'phuan@gmail.com', 'Tran Nhu Phu An', 1663557673, 'an321', 'ba79fe7415773191e534433a5a3f95548133f67c1c18d90d6fe567c4ac2c971c', 'rhf918jr89t9d5u9uq7nevvbjv', 'Y'),
	(5, 'haiNguyen', 'hainguyen@gmail.com', 'Nguyen Ngoc Hai', 122335363, 'hai345', '30484dec726d65773e14759f7c8041e93e12b3ae43f38ecfe59153806dd04b0a', 'v0ggia0s7tevokihdb5romv6ip', 'Y'),
	(6, 'memeo', 'meoconthichhoc@gmail.com', 'Nguyen Trung Nghia', 1225400756, 'adidaphat48', 'a0d814b778c320c5ab47bfe33003f50a757875770f79af5b9bd8c2f7cd395e7e', '8vseemj30rghl94vukmh1p5ago', 'Y'),
	(8, 'nghia1234', 'lala@gmail.com', 'Nguyen Trung Nghia', 1234567890, '123', '15af1fe553e60c3a709a2de6cb87e48ca3b1f179728b50384e3d4b96b0dc615e', 'ppqqopd2oriuq7m9vvc2qomice', 'N'),
	(9, 'meomeo', 'meomeo@gmail.com', 'Nguyen Trung Nghia', 1234567890, '123', '0b864116ff9aca7c373f8c9a4e3dd30995069044c449f24d38171303bd3a87d3', 'd1np20fmdtupdf8u5bnnttk14o', 'N'),
	(10, 'triTin', 'tritin@gmail.com', 'Tri Tin Nguyen', 12345678, 'tinnguyen', '9f7a1050ffe73a3360b075a310a9616e355255c5761bf28936f3e199951960d5', 'qbq0vlbutu7uv4hjkv2fj9id7m', 'N'),
	(11, 'trongYem', 'phamtrongyem@gmail.com', 'Pham Trong Yem', 99999999, 'trongyem123', '549c032d4494918c5ac09b01e0a60999c4786703148578ef1bb8dcee7413cb45', 'vcc6btjgrl07qjlug8hov0uelh', 'N'),
	(12, 'tinnghia', 'tuminhthapduoc@gmail.com', 'Tin Nghia', 123333333, 'nghiatin', 'aed711aede67bbb31fc964c50809d32b45bb763abcf96e288ad1ec8255f1bd9c', 'jc56mer4svjg990akfrgnhkhr3', 'Y'),
	(13, 'baothong', 'baothong.bp@gmail.com', 'Pham Bao Thong', 123456789, '123', 'b6aa356daeb221f7d872896887a9704f61ed112ad08208f85734ee1314dd3783', 'nn7mno17ngmov24p2pj0g1uk82', 'N');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table ebusticket.feedback
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE IF NOT EXISTS `feedback` (
  `Feedback_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Cus_id` int(10) unsigned NOT NULL,
  `Comment` text,
  `Subject` varchar(50) DEFAULT NULL,
  `Date_feedback` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table ebusticket.feedback: ~6 rows (approximately)
DELETE FROM `feedback`;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` (`Feedback_id`, `Cus_id`, `Comment`, `Subject`, `Date_feedback`) VALUES
	(1, 1, 'first feedback', '1 st', '2016-12-09 23:01:46'),
	(3, 10, 'second feedback', '2 st', '2016-12-09 23:01:28'),
	(4, 11, 'third feedback', '3 rd', '2016-12-08 20:11:50'),
	(5, 12, 'third feedback', '4th', '2016-12-09 23:04:18'),
	(7, 6, 'very good job', 'good job', '2016-12-09 23:33:26'),
	(10, 4, 'this wed site very good', 'hello', '2016-12-12 12:12:02');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;

-- Dumping structure for table ebusticket.seat
DROP TABLE IF EXISTS `seat`;
CREATE TABLE IF NOT EXISTS `seat` (
  `Seat_id` int(11) NOT NULL AUTO_INCREMENT,
  `Seat_no` varchar(50) NOT NULL,
  `Status` varchar(1) NOT NULL,
  `Pin_code` varchar(50) NOT NULL DEFAULT '0',
  `Bus_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Seat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;

-- Dumping data for table ebusticket.seat: ~100 rows (approximately)
DELETE FROM `seat`;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` (`Seat_id`, `Seat_no`, `Status`, `Pin_code`, `Bus_id`) VALUES
	(1, '1', 'N', '0', 2),
	(2, '2', 'N', '0', 2),
	(3, '3', 'N', '0', 2),
	(4, '4', 'N', '0', 2),
	(5, '5', 'N', '0', 2),
	(6, '6', 'N', '0', 2),
	(7, '7', 'N', '0', 2),
	(8, '8', 'N', '0', 2),
	(9, '9', 'N', '0', 2),
	(10, '10', 'N', '0', 2),
	(11, '11', 'N', '0', 2),
	(12, '12', 'N', '0', 2),
	(13, '13', 'N', '0', 2),
	(14, '14', 'N', '0', 2),
	(15, '15', 'N', '0', 2),
	(16, '16', 'N', '0', 2),
	(17, '17', 'N', '0', 2),
	(18, '18', 'N', '0', 2),
	(19, '19', 'N', '0', 2),
	(20, '20', 'N', '0', 2),
	(21, '1', 'N', '0', 1),
	(22, '2', 'N', '0', 1),
	(23, '3', 'N', '0', 1),
	(24, '4', 'N', '0', 1),
	(25, '5', 'N', '0', 1),
	(26, '6', 'N', '0', 1),
	(27, '7', 'N', '0', 1),
	(28, '8', 'N', '0', 1),
	(29, '9', 'N', '0', 1),
	(30, '10', 'N', '0', 1),
	(31, '11', 'N', '0', 1),
	(32, '12', 'N', '0', 1),
	(33, '13', 'N', '0', 1),
	(34, '14', 'N', '0', 1),
	(35, '15', 'N', '0', 1),
	(36, '16', 'N', '0', 1),
	(37, '17', 'N', '0', 1),
	(38, '18', 'N', '0', 1),
	(39, '19', 'N', '0', 1),
	(40, '20', 'N', '0', 1),
	(41, '1', 'N', '0', 3),
	(42, '2', 'N', '0', 3),
	(43, '3', 'N', '0', 3),
	(44, '4', 'N', '0', 3),
	(45, '5', 'N', '0', 3),
	(46, '6', 'N', '0', 3),
	(47, '7', 'N', '0', 3),
	(48, '8', 'N', '0', 3),
	(49, '9', 'N', '0', 3),
	(50, '10', 'N', '0', 3),
	(51, '11', 'N', '0', 3),
	(52, '12', 'N', '0', 3),
	(53, '13', 'N', '0', 3),
	(54, '14', 'N', '0', 3),
	(55, '15', 'N', '0', 3),
	(56, '16', 'N', '0', 3),
	(57, '17', 'N', '0', 3),
	(58, '18', 'N', '0', 3),
	(59, '19', 'N', '0', 3),
	(60, '20', 'N', '0', 3),
	(61, '1', 'N', '0', 4),
	(62, '2', 'N', '0', 4),
	(63, '3', 'N', '0', 4),
	(64, '4', 'N', '0', 4),
	(65, '5', 'N', '0', 4),
	(66, '6', 'N', '0', 4),
	(67, '7', 'Y', 'o37j9', 4),
	(68, '8', 'N', '0', 4),
	(69, '9', 'N', '0', 4),
	(70, '10', '0', '0', 4),
	(71, '11', 'N', '0', 4),
	(72, '12', 'N', '0', 4),
	(73, '13', 'N', '0', 4),
	(74, '14', 'N', '0', 4),
	(75, '15', 'N', '0', 4),
	(76, '16', 'N', '0', 4),
	(77, '17', 'N', '0', 4),
	(78, '18', 'N', '0', 4),
	(79, '19', 'N', '0', 4),
	(80, '20', 'N', '0', 4),
	(81, '1', 'N', '0', 5),
	(82, '2', 'N', '0', 5),
	(83, '3', 'N', '0', 5),
	(84, '4', 'N', '0', 5),
	(85, '5', 'N', '0', 5),
	(86, '6', 'N', '0', 5),
	(87, '7', 'N', '0', 5),
	(88, '8', 'N', '0', 5),
	(89, '9', 'N', '0', 5),
	(90, '10', 'N', '0', 5),
	(91, '11', 'N', '0', 5),
	(92, '12', 'N', '0', 5),
	(93, '13', 'N', '0', 5),
	(94, '14', 'N', '0', 5),
	(95, '15', 'N', '0', 5),
	(96, '16', 'N', '0', 5),
	(97, '17', 'N', '0', 5),
	(98, '18', 'N', '0', 5),
	(99, '19', 'N', '0', 5),
	(100, '20', 'N', '0', 5);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;

-- Dumping structure for table ebusticket.ticket
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE IF NOT EXISTS `ticket` (
  `Ticket_id` int(11) NOT NULL AUTO_INCREMENT,
  `Cus_id` int(11) NOT NULL DEFAULT '0',
  `Date_booking` datetime DEFAULT CURRENT_TIMESTAMP,
  `Time_available` timestamp NULL DEFAULT NULL,
  `Num_of_seats` int(11) DEFAULT NULL,
  `Ticket_price` int(11) DEFAULT NULL,
  `Pin_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Ticket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

-- Dumping data for table ebusticket.ticket: ~20 rows (approximately)
DELETE FROM `ticket`;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` (`Ticket_id`, `Cus_id`, `Date_booking`, `Time_available`, `Num_of_seats`, `Ticket_price`, `Pin_code`) VALUES
	(39, 12, '2016-12-22 23:44:54', '2016-12-23 12:00:00', 1, 1000, 'o37j9');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;

-- Dumping structure for table ebusticket.trip
DROP TABLE IF EXISTS `trip`;
CREATE TABLE IF NOT EXISTS `trip` (
  `TripID` int(11) NOT NULL AUTO_INCREMENT,
  `Arrival` varchar(50) DEFAULT NULL,
  `Destination` varchar(50) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  PRIMARY KEY (`TripID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Dumping data for table ebusticket.trip: ~6 rows (approximately)
DELETE FROM `trip`;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` (`TripID`, `Arrival`, `Destination`, `Price`) VALUES
	(1, 'Bac Lieu', 'Ca Mau', 1000),
	(2, 'Nghe An', 'Dong Thap', 700),
	(3, 'Ha Noi', 'Ho Chi Minh', 1500),
	(4, 'Ha Noi', 'Hai Phong', 200),
	(5, 'Ho Chi Minh', 'Ha Noi', 1300),
	(7, 'Ho Chi Minh', 'Ca Mau', 500),
	(8, 'Bac Lieu', 'Nghe An', 1000),
	(9, 'An Giang', 'Ho Chi Minh', 1000),
	(10, 'An Giang', 'Vinh Long', 1500),
	(11, 'An Giang', 'Can Tho', 500),
	(12, 'An Giang', 'Ha Noi', 2000),
	(13, 'An Giang', 'Nghe An', 1500);
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
