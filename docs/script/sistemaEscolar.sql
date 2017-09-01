-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.7.4-m14 - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura do banco de dados para sistemaescolar
CREATE DATABASE IF NOT EXISTS `sistemaescolar` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sistemaescolar`;


-- Copiando estrutura para tabela sistemaescolar.aluno
CREATE TABLE IF NOT EXISTS `aluno` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL DEFAULT '0',
  `matricula` varchar(20) NOT NULL DEFAULT '0',
  `curso` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistemaescolar.aluno: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` (`id`, `nome`, `matricula`, `curso`) VALUES
	(3, 'Saulo2', '123', 'Fisica'),
	(6, 'Roney', '789', 'Computação'),
	(7, 'Gustavo', '100', 'Computação'),
	(10, 'Teste', '12345', '1');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;


-- Copiando estrutura para tabela sistemaescolar.professor
CREATE TABLE IF NOT EXISTS `professor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(15) NOT NULL DEFAULT '0',
  `nome` varchar(50) NOT NULL DEFAULT '0',
  `lotacao` varchar(50) NOT NULL DEFAULT '0',
  `titulo` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistemaescolar.professor: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;


-- Copiando estrutura para tabela sistemaescolar.turma
CREATE TABLE IF NOT EXISTS `turma` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departamento` varchar(50) NOT NULL,
  `horario` int(11) NOT NULL,
  `quantAlunos` int(11) NOT NULL,
  `idProfessor` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistemaescolar.turma: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;


-- Copiando estrutura para tabela sistemaescolar.turma_aluno
CREATE TABLE IF NOT EXISTS `turma_aluno` (
  `turma_id` int(11) NOT NULL,
  `aluno_id` int(11) NOT NULL,
  PRIMARY KEY (`turma_id`,`aluno_id`),
  KEY `fk_turma_aluno_aluno1_idx` (`aluno_id`),
  CONSTRAINT `fk_turma_aluno_turma` FOREIGN KEY (`turma_id`) REFERENCES `turma` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_turma_aluno_aluno1` FOREIGN KEY (`aluno_id`) REFERENCES `aluno` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistemaescolar.turma_aluno: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `turma_aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `turma_aluno` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
