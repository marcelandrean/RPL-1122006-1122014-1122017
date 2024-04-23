-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2024 at 05:31 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_tubes_rpl`
--

-- --------------------------------------------------------

--
-- Table structure for table `concerts`
--

CREATE TABLE `concerts` (
  `ID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Artist` varchar(255) NOT NULL,
  `Location` varchar(255) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT current_timestamp(),
  `ImagePath` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `concerts`
--

INSERT INTO `concerts` (`ID`, `Name`, `Artist`, `Location`, `Date`, `ImagePath`) VALUES
(1, 'TWICE 5TH WORLD TOUR ‘READY TO BE’ IN JAKARTA', 'TWICE', 'Jakarta International Stadium', '2024-04-23 12:00:00', '/img/twice-seating-plan.jpg'),
(2, 'Coldplay Music of the Spheres World Tour Jakarta', 'Coldplay', 'Gelora Bung Karno Stadium Jakarta', '2024-11-15 14:00:00', '/img/coldplay-seating-plan.png'),
(3, '[2024 IU H.E.R. WORLD TOUR CONCERT] IN JAKARTA DAY 1', 'IU', 'ICE BSD HALL 5-6', '2024-04-27 10:00:00', '/img/iu-seating-plan.png'),
(4, '[2024 IU H.E.R. WORLD TOUR CONCERT] IN JAKARTA DAY 2', 'IU', 'ICE BSD HALL 5-6', '2024-04-28 12:00:00', '/img/iu-seating-plan.png');

-- --------------------------------------------------------

--
-- Table structure for table `merchandise`
--

CREATE TABLE `merchandise` (
  `ID` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `Stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `merchandise`
--

INSERT INTO `merchandise` (`ID`, `Name`, `Price`, `Stock`) VALUES
(1, 'T-shirt', 150000, 100),
(2, 'Poster', 75000, 200),
(3, 'Cap', 100000, 50);

-- --------------------------------------------------------

--
-- Table structure for table `merchandiseorders`
--

CREATE TABLE `merchandiseorders` (
  `ID` int(11) NOT NULL,
  `TransactionID` varchar(255) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ticketorders`
--

CREATE TABLE `ticketorders` (
  `ticketid` int(11) NOT NULL,
  `transactionid` varchar(255) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ticketorders`
--

INSERT INTO `ticketorders` (`ticketid`, `transactionid`, `quantity`) VALUES
(3, '68b5f6a5-9f3a-4027-a58d-31de210eca22', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `ID` int(11) NOT NULL,
  `ConcertID` int(11) DEFAULT NULL,
  `Category` varchar(255) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `Stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tickets`
--

INSERT INTO `tickets` (`ID`, `ConcertID`, `Category`, `Price`, `Stock`) VALUES
(1, 1, 'PINK A', 3600000, 77),
(2, 1, 'PINK B', 3600000, 80),
(3, 1, 'BLUE A', 3300000, 98),
(4, 1, 'BLUE B', 3300000, 100),
(5, 1, 'GREEN A', 2900000, 100),
(6, 1, 'GREEN B', 2900000, 100),
(7, 1, 'ORANGE A', 2700000, 100),
(8, 1, 'ORANGE B', 2700000, 100),
(9, 1, 'PURPLE SOUTH', 2700000, 250),
(10, 1, 'PURPLE EAST', 2700000, 500),
(11, 1, 'PURPLE NORTH', 2700000, 200),
(12, 1, 'YELLOW SOUTH', 2000000, 250),
(13, 1, 'YELLOW EAST', 1220000000, 500),
(14, 1, 'YELLOW NORTH', 2000000, 250),
(15, 1, 'GRAY SOUTH', 1200000, 600),
(16, 1, 'GRAY EAST', 1200000, 600),
(17, 1, 'GRAY NORTH', 1200000, 300),
(18, 2, 'ULTIMATE EXPERIENCE', 11000000, 20),
(19, 2, 'MY UNIVERSE (FESTIVAL)', 5700000, 100),
(20, 2, 'CAT 1', 5000000, 50),
(21, 2, 'FESTIVAL', 3500000, 700),
(22, 2, 'CAT 2A', 4000000, 200),
(23, 2, 'CAT 2B', 4000000, 200),
(24, 2, 'CAT 3', 2500000, 500),
(25, 2, 'CAT 4A', 1750000, 400),
(26, 2, 'CAT 4B', 1750000, 400),
(27, 2, 'CAT 5A', 1250000, 180),
(28, 2, 'CAT 5B', 1250000, 180),
(29, 2, 'CAT 6', 1250000, 200),
(30, 2, 'CAT 7A', 1200000, 70),
(31, 2, 'CAT 7B', 1200000, 70),
(32, 3, 'CAT 1A', 2900000, 1000),
(33, 3, 'CAT 1B', 2900000, 1000),
(34, 3, 'CAT 2A', 2700000, 300),
(35, 3, 'CAT 2B', 2700000, 300),
(36, 3, 'CAT 2C', 2700000, 800),
(37, 3, 'CAT 3A', 1900000, 300),
(38, 3, 'CAT 3B', 1900000, 300),
(39, 3, 'CAT 3C', 1900000, 600),
(40, 3, 'CAT 4A', 1500000, 400),
(41, 3, 'CAT 4B', 1500000, 400),
(42, 3, 'CAT 5A', 1100000, 300),
(43, 3, 'CAT 5B', 1100000, 300),
(44, 4, 'CAT 1A', 2900000, 1000),
(45, 4, 'CAT 1B', 2900000, 1000),
(46, 4, 'CAT 2A', 2700000, 300),
(47, 4, 'CAT 2B', 2700000, 300),
(48, 4, 'CAT 2C', 2700000, 800),
(49, 4, 'CAT 3A', 1900000, 300),
(50, 4, 'CAT 3B', 1900000, 300),
(51, 4, 'CAT 3C', 1900000, 600),
(52, 4, 'CAT 4A', 1500000, 400),
(53, 4, 'CAT 4B', 1500000, 400),
(54, 4, 'CAT 5A', 1100000, 300),
(55, 4, 'CAT 5B', 1100000, 300);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `ID` varchar(255) NOT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `TransactionDate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `TotalPrice` double DEFAULT NULL,
  `Status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`ID`, `Username`, `TransactionDate`, `TotalPrice`, `Status`) VALUES
('68b5f6a5-9f3a-4027-a58d-31de210eca22', 'marcelandrean', '2024-04-23 02:44:17', 3800000, 'BOOKED');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Username` varchar(50) NOT NULL,
  `FullName` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `PhoneNumber` varchar(20) NOT NULL,
  `UserType` enum('ADMIN','CUSTOMER') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Username`, `FullName`, `Email`, `Password`, `PhoneNumber`, `UserType`) VALUES
('alexanderg16', 'Mikael Alexander', 'alex@gmail.com', 'ppp', '08789789', 'ADMIN'),
('dasdaweq', 'ADWq', 'weaw@gmail.com', '123', '08312412', 'CUSTOMER'),
('enrcccc', 'Jason Enrico', 'jason@gmail.com', 'abc', '08654321', 'CUSTOMER'),
('marcelandrean', 'Marcel Andrean', 'marcel@gmail.com', '123', '08123456', 'CUSTOMER'),
('qeqwe', 'Aman ajah', 'eqewqwe@gmail.com', '123', '081324125', 'CUSTOMER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `concerts`
--
ALTER TABLE `concerts`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `merchandise`
--
ALTER TABLE `merchandise`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `merchandiseorders`
--
ALTER TABLE `merchandiseorders`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `TransactionID` (`TransactionID`);

--
-- Indexes for table `ticketorders`
--
ALTER TABLE `ticketorders`
  ADD PRIMARY KEY (`ticketid`,`transactionid`),
  ADD KEY `transactionid` (`transactionid`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ConcertID` (`ConcertID`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Username` (`Username`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Username`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `concerts`
--
ALTER TABLE `concerts`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `merchandise`
--
ALTER TABLE `merchandise`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `merchandiseorders`
--
ALTER TABLE `merchandiseorders`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `merchandiseorders`
--
ALTER TABLE `merchandiseorders`
  ADD CONSTRAINT `merchandiseorders_ibfk_1` FOREIGN KEY (`TransactionID`) REFERENCES `transactions` (`ID`);

--
-- Constraints for table `ticketorders`
--
ALTER TABLE `ticketorders`
  ADD CONSTRAINT `ticketorders_ibfk_1` FOREIGN KEY (`ticketid`) REFERENCES `tickets` (`ID`),
  ADD CONSTRAINT `ticketorders_ibfk_2` FOREIGN KEY (`transactionid`) REFERENCES `transactions` (`ID`);

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`ConcertID`) REFERENCES `concerts` (`ID`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `users` (`Username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
