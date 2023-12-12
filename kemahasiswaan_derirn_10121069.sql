-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 03 Agu 2022 pada 14.11
-- Versi server: 10.4.21-MariaDB
-- Versi PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kemahasiswaan_derirn_10121069`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nim` varchar(15) NOT NULL,
  `nama` varchar(25) DEFAULT NULL,
  `tempat_lahir` varchar(15) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `nama`, `tempat_lahir`, `tanggal_lahir`, `alamat`) VALUES
('10121067', 'Asep', 'Bandung', '2004-04-20', 'KP Salamanjah'),
('10121069', 'Deri Rizky Nugraha', 'Bandung', '2003-03-28', 'Cangkuang');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mata_kuliah`
--

CREATE TABLE `mata_kuliah` (
  `kode_mk` varchar(15) NOT NULL,
  `nama_mk` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mata_kuliah`
--

INSERT INTO `mata_kuliah` (`kode_mk`, `nama_mk`) VALUES
('1', 'ALGORITMA'),
('2', 'MSD'),
('3', 'PBO'),
('4', 'PEMOGRAMAN WEB'),
('5', 'STATISTIKA'),
('6', 'PPD'),
('7', 'Matematika');

-- --------------------------------------------------------

--
-- Struktur dari tabel `nilai`
--

CREATE TABLE `nilai` (
  `nim` varchar(20) NOT NULL,
  `kode_mk` varchar(20) DEFAULT NULL,
  `absensi` int(15) DEFAULT NULL,
  `tgs1` int(15) DEFAULT NULL,
  `tgs2` int(15) DEFAULT NULL,
  `tgs3` int(15) DEFAULT NULL,
  `uts` int(15) DEFAULT NULL,
  `uas` int(15) DEFAULT NULL,
  `nilai_absensi` int(15) DEFAULT NULL,
  `nilai_tugas` int(15) DEFAULT NULL,
  `nilai_uts` int(15) DEFAULT NULL,
  `nilai_uas` int(15) DEFAULT NULL,
  `nilai_akhir` int(15) DEFAULT NULL,
  `indeks` varchar(15) DEFAULT NULL,
  `keterangan` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `nilai`
--

INSERT INTO `nilai` (`nim`, `kode_mk`, `absensi`, `tgs1`, `tgs2`, `tgs3`, `uts`, `uas`, `nilai_absensi`, `nilai_tugas`, `nilai_uts`, `nilai_uas`, `nilai_akhir`, `indeks`, `keterangan`) VALUES
('10121069', '3', 14, 80, 85, 88, 80, 85, 5, 21, 24, 34, 84, 'A', 'LULUS');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
--

CREATE TABLE `pengguna` (
  `nama_pengguna` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`nama_pengguna`, `password`) VALUES
('admin', 'admin'),
('deri', 'rizky');

-- --------------------------------------------------------

--
-- Struktur dari tabel `simulasi_nilai`
--

CREATE TABLE `simulasi_nilai` (
  `nama_mk` varchar(20) NOT NULL,
  `p_absen` int(15) DEFAULT NULL,
  `p_tugas` int(15) DEFAULT NULL,
  `p_uts` int(15) DEFAULT NULL,
  `p_uas` int(15) DEFAULT NULL,
  `absensi` int(15) DEFAULT NULL,
  `tgs1` int(15) DEFAULT NULL,
  `tgs2` int(15) DEFAULT NULL,
  `tgs3` int(15) DEFAULT NULL,
  `uts` int(15) DEFAULT NULL,
  `uas` int(15) DEFAULT NULL,
  `n_absen` int(15) DEFAULT NULL,
  `n_tugas` int(15) DEFAULT NULL,
  `n_uts` int(15) DEFAULT NULL,
  `n_uas` int(15) DEFAULT NULL,
  `n_akhir` int(15) DEFAULT NULL,
  `indeks` varchar(15) DEFAULT NULL,
  `keterangan` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `simulasi_nilai`
--

INSERT INTO `simulasi_nilai` (`nama_mk`, `p_absen`, `p_tugas`, `p_uts`, `p_uas`, `absensi`, `tgs1`, `tgs2`, `tgs3`, `uts`, `uas`, `n_absen`, `n_tugas`, `n_uts`, `n_uas`, `n_akhir`, `indeks`, `keterangan`) VALUES
('OSK', 25, 25, 25, 25, 14, 87, 86, 85, 86, 88, 25, 22, 22, 22, 90, 'A', 'LULUS'),
('PBO', 25, 25, 25, 25, 14, 80, 85, 86, 87, 88, 25, 21, 22, 22, 90, 'A', 'LULUS'),
('STATISTIKA', 25, 25, 25, 25, 14, 80, 87, 89, 90, 91, 25, 21, 23, 23, 92, 'A', 'LULUS');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indeks untuk tabel `mata_kuliah`
--
ALTER TABLE `mata_kuliah`
  ADD PRIMARY KEY (`kode_mk`);

--
-- Indeks untuk tabel `nilai`
--
ALTER TABLE `nilai`
  ADD PRIMARY KEY (`nim`);

--
-- Indeks untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`nama_pengguna`);

--
-- Indeks untuk tabel `simulasi_nilai`
--
ALTER TABLE `simulasi_nilai`
  ADD PRIMARY KEY (`nama_mk`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
