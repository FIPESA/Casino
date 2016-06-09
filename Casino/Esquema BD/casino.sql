-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 31-05-2016 a las 10:11:52
-- Versión del servidor: 5.5.49-0+deb8u1
-- Versión de PHP: 5.6.20-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `casino`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Jugadas_Blackjack`
--

CREATE TABLE IF NOT EXISTS `Jugadas_Blackjack` (
`id_Jugada` int(11) NOT NULL,
  `CantidadApostada` double NOT NULL,
  `ResCartasUser` int(11) NOT NULL,
  `ResCartasCrupier` int(11) NOT NULL,
  `CantidadGanada` double NOT NULL,
  `Fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Username` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Jugadas_Ruleta`
--

CREATE TABLE IF NOT EXISTS `Jugadas_Ruleta` (
`Id_Jugada` int(11) NOT NULL,
  `Resultado` int(11) NOT NULL,
  `CantidadApostada` double NOT NULL,
  `Fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CantidadGanada` int(11) NOT NULL,
  `Username` varchar(45) NOT NULL,
  `NumerosApostados` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Jugadas_Slots`
--

CREATE TABLE IF NOT EXISTS `Jugadas_Slots` (
`Id_Jugada` int(11) NOT NULL,
  `Resultado1` int(11) NOT NULL,
  `Resultado2` int(11) NOT NULL,
  `Resultado3` int(11) NOT NULL,
  `CantidadGanada` double NOT NULL,
  `Username` varchar(45) NOT NULL,
  `Fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Transacciones`
--

CREATE TABLE IF NOT EXISTS `Transacciones` (
`Id_Transaccion` int(11) NOT NULL,
  `Fondos` double NOT NULL,
  `Fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Username` varchar(45) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuarios`
--

CREATE TABLE IF NOT EXISTS `Usuarios` (
  `Username` varchar(45) NOT NULL,
  `Pass` varchar(45) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellido1` varchar(45) NOT NULL,
  `Apellido2` varchar(45) DEFAULT NULL,
  `Fecha_Registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Email` varchar(45) NOT NULL,
  `Renta_num` double NOT NULL,
  `Aceptado` tinyint(1) NOT NULL DEFAULT '0',
  `Tipo` int(1) NOT NULL DEFAULT '0',
  `Fondos` double NOT NULL DEFAULT '0',
  `Ban_Date` date DEFAULT NULL,
  `Fecha_fin_premium` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Jugadas_Blackjack`
--
ALTER TABLE `Jugadas_Blackjack`
 ADD PRIMARY KEY (`id_Jugada`), ADD KEY `username_idx` (`Username`);

--
-- Indices de la tabla `Jugadas_Ruleta`
--
ALTER TABLE `Jugadas_Ruleta`
 ADD PRIMARY KEY (`Id_Jugada`), ADD KEY `username_idx` (`Username`);

--
-- Indices de la tabla `Jugadas_Slots`
--
ALTER TABLE `Jugadas_Slots`
 ADD PRIMARY KEY (`Id_Jugada`), ADD KEY `username_idx` (`Username`);

--
-- Indices de la tabla `Transacciones`
--
ALTER TABLE `Transacciones`
 ADD PRIMARY KEY (`Id_Transaccion`), ADD KEY `username_idx` (`Username`);

--
-- Indices de la tabla `Usuarios`
--
ALTER TABLE `Usuarios`
 ADD PRIMARY KEY (`Username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Jugadas_Blackjack`
--
ALTER TABLE `Jugadas_Blackjack`
MODIFY `id_Jugada` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Jugadas_Ruleta`
--
ALTER TABLE `Jugadas_Ruleta`
MODIFY `Id_Jugada` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Jugadas_Slots`
--
ALTER TABLE `Jugadas_Slots`
MODIFY `Id_Jugada` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Transacciones`
--
ALTER TABLE `Transacciones`
MODIFY `Id_Transaccion` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Jugadas_Blackjack`
--
ALTER TABLE `Jugadas_Blackjack`
ADD CONSTRAINT `usernameblack` FOREIGN KEY (`Username`) REFERENCES `Usuarios` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Jugadas_Ruleta`
--
ALTER TABLE `Jugadas_Ruleta`
ADD CONSTRAINT `usernamerul` FOREIGN KEY (`Username`) REFERENCES `Usuarios` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Jugadas_Slots`
--
ALTER TABLE `Jugadas_Slots`
ADD CONSTRAINT `usernameslo` FOREIGN KEY (`Username`) REFERENCES `Usuarios` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Transacciones`
--
ALTER TABLE `Transacciones`
ADD CONSTRAINT `Username` FOREIGN KEY (`Username`) REFERENCES `Usuarios` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
