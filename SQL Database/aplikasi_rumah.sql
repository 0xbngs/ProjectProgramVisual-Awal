-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 23, 2025 at 07:34 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aplikasi_rumah`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `nik` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `nokk` varchar(20) NOT NULL,
  `alamat` text NOT NULL,
  `npwp` varchar(20) DEFAULT NULL,
  `asuransi` varchar(100) DEFAULT NULL,
  `gaji` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `nik`, `nama`, `nokk`, `alamat`, `npwp`, `asuransi`, `gaji`) VALUES
(1, '8892882', 'John Does', '321729', 'jakarta', '01292092', 'jiwaseraya', 1090911),
(2, '4444', 'beru', '5555', 'jakarta', '090909', 'jiwaseraya', 99999),
(3, '12345', 'abi', '54321', 'Bandoeng', '898981', 'Prudentials', 1890000);

-- --------------------------------------------------------

--
-- Table structure for table `jabatan`
--

CREATE TABLE `jabatan` (
  `id` int(11) NOT NULL,
  `jabatan` varchar(100) NOT NULL,
  `gaji_pokok` double NOT NULL,
  `bonus` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jabatan`
--

INSERT INTO `jabatan` (`id`, `jabatan`, `gaji_pokok`, `bonus`) VALUES
(1, 'Tukang Ketik Enginner', 1000000, 109900),
(2, 'UI', 999999, 11111),
(3, 'Full stack dev', 111111, 9999),
(4, 'Network Engineer', 500000, 200000),
(5, 'sales', 800000, 200000),
(6, 'Cysec', 40000, 10000);

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jabatan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id`, `nama`, `jabatan`) VALUES
(1, 'Bambang Sutrisna', 'Cysec'),
(2, 'Cryl', 'UI'),
(3, 'Roy natanael', 'Full Stack dev'),
(4, 'Adit', 'Network Engineer'),
(5, 'Abiyu Sofyan', 'Full Stack Mobile Dev'),
(6, 'mubina', 'sales');

-- --------------------------------------------------------

--
-- Table structure for table `rab`
--

CREATE TABLE `rab` (
  `id` int(11) NOT NULL,
  `tipe` varchar(100) NOT NULL,
  `keterangan` text NOT NULL,
  `harga` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rab`
--

INSERT INTO `rab` (`id`, `tipe`, `keterangan`, `harga`) VALUES
(1, 'Zo01', 'Siap', 192921);

-- --------------------------------------------------------

--
-- Table structure for table `tipe_rumah`
--

CREATE TABLE `tipe_rumah` (
  `id` int(11) NOT NULL,
  `tipe` varchar(100) NOT NULL,
  `harga_pokok` double NOT NULL,
  `luas_bangunan` double NOT NULL,
  `luas_tanah` double NOT NULL,
  `kamar_tidur` int(11) NOT NULL,
  `deskripsi` text NOT NULL,
  `kamar_mandi` int(11) NOT NULL,
  `lantai` int(11) NOT NULL,
  `listrik` varchar(100) NOT NULL,
  `sumber_air` varchar(100) NOT NULL,
  `harga_rumah` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tipe_rumah`
--

INSERT INTO `tipe_rumah` (`id`, `tipe`, `harga_pokok`, `luas_bangunan`, `luas_tanah`, `kamar_tidur`, `deskripsi`, `kamar_mandi`, `lantai`, `listrik`, `sumber_air`, `harga_rumah`) VALUES
(1, 'Modern', 981911, 400, 600, 8, 'Rumah ini di buat dengan desain modern ', 8, 3, '1000', '1000', 9811999222),
(2, 'classic', 11, 22, 33, 4, 'wowow', 5, 3, '1111', 'PDAM', 54231);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `client_id` int(11) NOT NULL,
  `tipe_rumah_id` int(11) NOT NULL,
  `karyawan_id` int(11) NOT NULL,
  `harga_rumah` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `timestamp`, `client_id`, `tipe_rumah_id`, `karyawan_id`, `harga_rumah`) VALUES
(5, '2025-03-22 04:18:12', 2, 1, 3, 9811999222),
(6, '2025-03-22 04:25:07', 3, 2, 5, 54231),
(7, '2025-03-22 11:37:04', 1, 2, 1, 54231),
(8, '2025-03-22 11:45:26', 2, 2, 6, 54231),
(9, '2025-03-22 11:46:19', 2, 1, 1, 9811999222),
(10, '2025-03-22 11:47:05', 2, 1, 1, 9811999222),
(11, '2025-03-22 11:50:35', 2, 2, 1, 54231),
(12, '2025-03-22 14:13:35', 2, 1, 1, 9811999222),
(13, '2025-03-22 14:14:43', 2, 1, 1, 9811999222);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jabatan`
--
ALTER TABLE `jabatan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rab`
--
ALTER TABLE `rab`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tipe_rumah`
--
ALTER TABLE `tipe_rumah`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `tipe_rumah_id` (`tipe_rumah_id`),
  ADD KEY `karyawan_id` (`karyawan_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `jabatan`
--
ALTER TABLE `jabatan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `karyawan`
--
ALTER TABLE `karyawan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `rab`
--
ALTER TABLE `rab`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tipe_rumah`
--
ALTER TABLE `tipe_rumah`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`tipe_rumah_id`) REFERENCES `tipe_rumah` (`id`),
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`karyawan_id`) REFERENCES `karyawan` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
