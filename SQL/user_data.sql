-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 29, 2022 at 07:07 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mvc-test`
--

-- --------------------------------------------------------

--
-- Table structure for table `user_data`
--

CREATE TABLE `user_data` (
  `REF_ID` int(11) NOT NULL,
  `FNAME` varchar(60) DEFAULT NULL,
  `LNAME` varchar(60) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `COMMENT` varchar(255) NOT NULL,
  `TIMESTAMP` varchar(40) DEFAULT NULL,
  `EXPIRED` varchar(40) NOT NULL,
  `STATE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_data`
--

INSERT INTO `user_data` (`REF_ID`, `FNAME`, `LNAME`, `EMAIL`, `COMMENT`, `TIMESTAMP`, `EXPIRED`, `STATE`) VALUES
(1, 'Anwuatr', 'Treecharoenrat', '63050206@kmitl.ac.th', 'i want more food', '2022/10/29 10:43:20', '2022/11/05 10:43:20', 'CLOSE'),
(2, 'Kue', 'Laosinwattana', '63050102@kmitl.ac.th', 'i want more girls', '2022/10/29 10:43:35', '2022/11/05 10:43:35', 'CLOSE'),
(3, 'P', 'Nove', 'krataiza@kmitl.ac.th', 'kratai', '2022/10/29 10:45:33', '2022/11/05 10:45:33', 'CLOSE'),
(4, 'Siriwat', 'Kantasit', 'beat@kmitl.ac.th', 'hew kaaw', '2022/10/22 10:43:20', '2022/10/29 10:43:20', 'CLOSE'),
(5, 'Dylanon', 'Wichiramara', 'dylanon@kmitl.ac.th', 'ggwp', '2022/10/29 10:54:57', '2022/11/05 10:54:57', 'CLOSE'),
(6, 't', 't', 't@t.gmail.com', 'st', '2022/10/29 11:46:45', '2022/11/05 11:46:45', 'OPEN'),
(7, 'House', 'Chan', 'hc@ph.com', 'yark dai green grass', '2022/10/29 11:58:38', '2022/11/05 11:58:38', 'OPEN'),
(8, 'Gotji', 'Belt', 'gb@suphran.in.th', 'need more smoke', '2022/10/29 11:59:06', '2022/11/05 11:59:06', 'ESCALATE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user_data`
--
ALTER TABLE `user_data`
  ADD PRIMARY KEY (`REF_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user_data`
--
ALTER TABLE `user_data`
  MODIFY `REF_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
