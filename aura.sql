-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 09 mars 2021 à 16:48
-- Version du serveur :  5.7.26
-- Version de PHP :  7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `aura`
--

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

DROP TABLE IF EXISTS `activite`;
CREATE TABLE IF NOT EXISTS `activite` (
  `id` int(16) NOT NULL,
  `idcoach` varchar(255) NOT NULL,
  `duree` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `nombremax` int(16) NOT NULL,
  `type` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `act1` (`idcoach`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id` int(16) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `theme` varchar(255) NOT NULL,
  `nom_auteur` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `article` varchar(255) NOT NULL,
  `id_user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `art2` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `challenge`
--

DROP TABLE IF EXISTS `challenge`;
CREATE TABLE IF NOT EXISTS `challenge` (
  `id` int(16) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `img` varchar(255) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `nb_participants` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL,
  `id_niveau` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_niveau` (`id_niveau`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int(16) NOT NULL,
  `id_user` varchar(255) NOT NULL,
  `id_article` int(16) NOT NULL,
  `commentaire` longtext NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `com1` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `id` int(16) NOT NULL,
  `type` varchar(255) NOT NULL,
  `id_user` varchar(255) NOT NULL,
  `champ` text NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `con1` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `entraide`
--

DROP TABLE IF EXISTS `entraide`;
CREATE TABLE IF NOT EXISTS `entraide` (
  `id` int(16) NOT NULL,
  `question` text NOT NULL,
  `categorie` varchar(255) NOT NULL,
  `id_user` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `visibilite` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ent1` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ligne_classement`
--

DROP TABLE IF EXISTS `ligne_classement`;
CREATE TABLE IF NOT EXISTS `ligne_classement` (
  `id` int(16) NOT NULL,
  `id_client` varchar(255) NOT NULL,
  `id_niveau` int(16) NOT NULL,
  `position` int(10) NOT NULL,
  `nb_points` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_niveau` (`id_niveau`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

DROP TABLE IF EXISTS `niveau`;
CREATE TABLE IF NOT EXISTS `niveau` (
  `id` int(11) NOT NULL,
  `titre` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `objectif`
--

DROP TABLE IF EXISTS `objectif`;
CREATE TABLE IF NOT EXISTS `objectif` (
  `id` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `reponse` int(11) NOT NULL,
  `dateDebut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `duree` int(11) NOT NULL,
  `idClient` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_objCli` (`idClient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `objectif_pred`
--

DROP TABLE IF EXISTS `objectif_pred`;
CREATE TABLE IF NOT EXISTS `objectif_pred` (
  `id` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `reponse` int(11) NOT NULL,
  `duree` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `participationactivté`
--

DROP TABLE IF EXISTS `participationactivté`;
CREATE TABLE IF NOT EXISTS `participationactivté` (
  `id` int(16) NOT NULL,
  `id_client` varchar(255) NOT NULL,
  `id_activite` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `part1` (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `participationtherapie`
--

DROP TABLE IF EXISTS `participationtherapie`;
CREATE TABLE IF NOT EXISTS `participationtherapie` (
  `id` int(16) NOT NULL,
  `id_client` varchar(255) NOT NULL,
  `id_therapie` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `part2` (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `participation_challenge`
--

DROP TABLE IF EXISTS `participation_challenge`;
CREATE TABLE IF NOT EXISTS `participation_challenge` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `id_challenge` int(16) NOT NULL,
  `id_client` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_challenge` (`id_challenge`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reminder`
--

DROP TABLE IF EXISTS `reminder`;
CREATE TABLE IF NOT EXISTS `reminder` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `id_challenge` int(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_challenge` (`id_challenge`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

DROP TABLE IF EXISTS `reponse`;
CREATE TABLE IF NOT EXISTS `reponse` (
  `id` int(16) NOT NULL,
  `id_question` int(11) NOT NULL,
  `id_user` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `reponse` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `rep1` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `suivi`
--

DROP TABLE IF EXISTS `suivi`;
CREATE TABLE IF NOT EXISTS `suivi` (
  `id` varchar(11) NOT NULL,
  `valeur` int(11) NOT NULL,
  `idClient` varchar(50) NOT NULL,
  `idObjectif` varchar(50) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_SuivObj` (`idObjectif`),
  KEY `fk_SuivCli` (`idClient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `therapie`
--

DROP TABLE IF EXISTS `therapie`;
CREATE TABLE IF NOT EXISTS `therapie` (
  `id` int(16) NOT NULL,
  `sujet` varchar(254) NOT NULL,
  `date` date NOT NULL,
  `lieu` varchar(254) NOT NULL,
  `nombremax` int(11) NOT NULL,
  `idcoach` varchar(254) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `th1` (`idcoach`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `specialite` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `password`, `tel`, `specialite`, `adresse`, `role`) VALUES
('12345670', 'seif', 'Ben Salah', 'seifeddine.bensalah@hotmail.com', 'afc677037be3d92324fa6597d6c1506b534e306b', '23123123', 'test', ' ', 'SAdmin'),
('12345671', 'sefi', 'mdptest', 'seifeddine.bensalah@gmail.com', 'afc677037be3d92324fa6597d6c1506b534e306b', '23232321', '', 'test ', 'Client'),
('12345672', 'seif', 'mdptest', 'seifeddine.bensalah@esprit.fr', 'afc677037be3d92324fa6597d6c1506b534e306b', '95223112', 'test', ' ', 'CoachV'),
('12345673', 'aa', 'aa', 'seifeddine.bensalah@esprit.tn', 'afc677037be3d92324fa6597d6c1506b534e306b', '23123126', '', 'aouina', 'Client'),
('12345674', 'ss', 'ss', 'ss@gm.com', 'afc677037be3d92324fa6597d6c1506b534e306b', '03232323', 'test', ' ', 'CoachNV'),
('12345675', 'seif', 'sha-1', 'seif@yahoo.fr', 'afc677037be3d92324fa6597d6c1506b534e306b', '23123127', 'test', ' ', 'CoachNV'),
('12345679', 'seif', 'admin', 'seifeddine.bensalah@yahoo.com', 'afc677037be3d92324fa6597d6c1506b534e306b', '23123124', '', ' ', 'Admin');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `activite`
--
ALTER TABLE `activite`
  ADD CONSTRAINT `act1` FOREIGN KEY (`idcoach`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `art2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `challenge`
--
ALTER TABLE `challenge`
  ADD CONSTRAINT `challenge_ibfk_1` FOREIGN KEY (`id_niveau`) REFERENCES `niveau` (`id`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `com1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `con1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `entraide`
--
ALTER TABLE `entraide`
  ADD CONSTRAINT `ent1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `ligne_classement`
--
ALTER TABLE `ligne_classement`
  ADD CONSTRAINT `id_client` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `ligne_classement_ibfk_1` FOREIGN KEY (`id_niveau`) REFERENCES `niveau` (`id`);

--
-- Contraintes pour la table `objectif`
--
ALTER TABLE `objectif`
  ADD CONSTRAINT `fk_objCli` FOREIGN KEY (`idClient`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `participationactivté`
--
ALTER TABLE `participationactivté`
  ADD CONSTRAINT `part1` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `participationtherapie`
--
ALTER TABLE `participationtherapie`
  ADD CONSTRAINT `part2` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `participation_challenge`
--
ALTER TABLE `participation_challenge`
  ADD CONSTRAINT `participation_challenge_ibfk_1` FOREIGN KEY (`id_challenge`) REFERENCES `challenge` (`id`),
  ADD CONSTRAINT `participation_challenge_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `reminder`
--
ALTER TABLE `reminder`
  ADD CONSTRAINT `reminder_ibfk_1` FOREIGN KEY (`id_challenge`) REFERENCES `challenge` (`id`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `rep1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `suivi`
--
ALTER TABLE `suivi`
  ADD CONSTRAINT `fk_SuivCli` FOREIGN KEY (`idClient`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `therapie`
--
ALTER TABLE `therapie`
  ADD CONSTRAINT `th1` FOREIGN KEY (`idcoach`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
