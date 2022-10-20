-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 20-10-2022 a las 15:30:15
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de datos: `andamio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `developer`
--

CREATE TABLE `developer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `administrador` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `developer`
--

INSERT INTO `developer` (`id`, `name`, `surname`, `last_name`, `email`, `administrador`) VALUES
(1, 'raimon', 'vilar', 'morera', 'test@email.com', 0),
(2, 'alvaro', 'talaya', 'romance', 'test@email.com', 0),
(3, 'mario', 'tomas', 'zanon', 'test@email.com', 0),
(4, 'aitana', 'collado', 'soler', 'test@email.com', 0),
(5, 'carlos', 'merlos', 'pilar', 'test@email.com', 0),
(6, 'luis', 'perez', 'derecho', 'test@email.com', 0),
(7, 'estefania', 'boriko', 'izquierdo', 'test@email.com', 0),
(8, 'quique', 'aroca', 'garcia', 'test@email.com', 0),
(9, 'adrian', 'duyang', 'liang', 'test@email.com', 0),
(10, 'rafael', 'aznar', 'caballero', 'test@email.com', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `help`
--

CREATE TABLE `help` (
  `id` bigint(20) NOT NULL,
  `id_resolution` bigint(20) NOT NULL,
  `id_developer` bigint(20) NOT NULL,
  `percentage` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `help`
--

INSERT INTO `help` (`id`, `id_resolution`, `id_developer`, `percentage`) VALUES
(1, 1, 1, 10),
(2, 5, 8, 8),
(3, 3, 9, 0.1),
(4, 9, 2, 0.5),
(5, 7, 5, 5),
(6, 6, 8, 15),
(7, 2, 6, 12),
(8, 8, 2, 3),
(9, 4, 7, 1),
(10, 10, 4, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `project`
--

CREATE TABLE `project` (
  `id` bigint(20) NOT NULL,
  `project_code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `project_description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `project`
--

INSERT INTO `project` (`id`, `project_code`, `project_description`, `url`) VALUES
(1, 'aabb', 'Example1', 'https://example1/andamios.net'),
(2, 'aaabbb', 'Example2', 'https://example2/andamios.net'),
(3, 'ccdd', 'Example3', 'https://example3/andamios.net'),
(4, 'cccddd', 'Example4', 'https://example4/andamios.net'),
(5, 'eeff', 'Example5', 'https://example5/andamios.net'),
(6, 'eeefff', 'Example6', 'https://example6/andamios.net'),
(7, 'gghh', 'Example7', 'https://example7/andamios.net'),
(8, 'ggghhh', 'Example8', 'https://example8/andamios.net'),
(9, 'iijj', 'Example9', 'https://example9/andamios.net'),
(10, 'iiijjj', 'Example10', 'https://example10/andamios.net');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `task`
--

CREATE TABLE `task` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `id_project` bigint(20) NOT NULL,
  `priority` int(11) NOT NULL DEFAULT 0,
  `complexity` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `task`
--

INSERT INTO `task` (`id`, `description`, `id_project`, `priority`, `complexity`) VALUES
(1, 'SQL db test', 2, 4, 8),
(2, 'Inno db is cool', 4, 3, 9),
(3, 'administrador SQL test', 2, 4, 8),
(4, 'MongoDB', 2, 4, 8),
(5, 'Hola mundo!', 4, 4, 8),
(6, 'Adios Mundo!', 6, 3, 8),
(7, 'Say hello!', 9, 5, 8),
(8, 'My cat bigotillos', 2, 4, 8),
(9, 'The mexican', 9, 9, 8),
(10, 'Another one', 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `issue`
--

CREATE TABLE `issue`(
    `id` bigint(20) NOT NULL,
    `open_datetime` datetime,
    `close_datetime` datetime,
    `id_developer_author` bigint(20) NOT NULL,
    `observations` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `id_developer_assigned` bigint(20) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `issue`
--

INSERT INTO `issue` (`id`, `open_datetime`, `close_datetime`, `id_developer_author`, `observations`, `id_developer_assigned`) VALUES
(1, '2022-09-25', '2022-10-02', 1, 'example observation 1', 2),
(2, '2022-09-10', '2022-09-24', 2, 'example observation 2', 3),
(3, '2022-10-01', '2022-10-05', 3, 'example observation 3', 4),
(4, '2022-10-06', '2022-10-15', 4, 'example observation 4', 5),
(5, '2022-10-15', '2022-10-18', 5, 'example observation 5', 6),
(6, '2022-10-25', '2022-10-29', 6, 'example observation 6', 7),
(7, '2022-11-11', '2022-11-15', 7, 'example observation 7', 8),
(8, '2022-11-25', '2022-11-28', 8, 'example observation 8', 9),
(9, '2022-12-02', '2022-12-20', 9, 'example observation 9', 10),
(10, '2022-12-15', '2022-12-20', 10,'example observation 10', 2);

--
-- Estructura de tabla para la tabla `resolution`
--

CREATE TABLE IF NOT EXISTS `resolution` (
  `id` bigint(20) NOT NULL,
  `id_issue` bigint(20) NOT NULL,
  `observations` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `integration_turn` bigint(20) DEFAULT NULL,
  `integration_datetime` datetime DEFAULT NULL,
  `pullrequest_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `value` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `resolution`
--

INSERT INTO `resolution` (`id`, `id_issue`, `observations`, `integration_turn`, `integration_datetime`, `pullrequest_url`, `value`) VALUES
(1, 4, 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.', 1, '2022-09-10 20:13:33', 'https://www.example.com/', 4),
(2, 7, 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.', 2, '2022-10-03 20:13:33', 'https://www.example.com/', 3),
(3, 5, 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.', 3, '2022-11-03 20:13:33', 'https://www.example.com/', 5),
(4, 1, 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.', 4, '2022-12-25 20:13:33', 'https://www.example.com/', 2),
(5, 3, NULL, NULL, NULL, NULL, 0),
(6, 3, 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.', 5, '2023-01-03 20:13:33', 'https://www.example.com/', 5),
(7, 10, 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.', 6, '2023-02-03 20:13:33', 'https://www.example.com/', 2),
(8, 2, 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.', 7, '2023-03-03 20:13:33', 'https://www.example.com/', 0),
(9, 6, 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.', 8, '2023-04-03 20:13:33', 'https://www.example.com/', 3),
(10, 8, 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.', 9, '2023-05-03 20:13:33', 'https://www.example.com/', 5);


--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `developer`
--
ALTER TABLE `developer`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `help`
--
ALTER TABLE `help`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `issue`
--
ALTER TABLE `issue`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `resolution`
--
ALTER TABLE `resolution`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `developer`
--
ALTER TABLE `developer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `help`
--
ALTER TABLE `help`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `project`
--
ALTER TABLE `project`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `task`
--
ALTER TABLE `task`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;


--
-- AUTO_INCREMENT de la tabla `task`
--
ALTER TABLE `issue`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

--
-- AUTO_INCREMENT de la tabla `resolution`
--
ALTER TABLE `resolution`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

