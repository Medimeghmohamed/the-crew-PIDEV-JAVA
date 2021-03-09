-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 27 fév. 2021 à 22:14
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
  `idclient` varchar(255) NOT NULL,
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
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `nom`, `prenom`, `email`, `tel`) VALUES
('12', 'Ahmed', 'ou', 'aa', 'aa');

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
  `article` longtext NOT NULL,
  `id_coach` varchar(255) NOT NULL,
  `id_client` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `art1` (`id_client`),
  KEY `art2` (`id_coach`)
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
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `coach`
--

DROP TABLE IF EXISTS `coach`;
CREATE TABLE IF NOT EXISTS `coach` (
  `id` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `salaire` varchar(255) NOT NULL,
  `specialite` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int(16) NOT NULL,
  `id_client` varchar(255) NOT NULL,
  `id_coach` varchar(255) NOT NULL,
  `id_article` int(16) NOT NULL,
  `commentaire` longtext NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `com1` (`id_client`),
  KEY `com2` (`id_coach`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `id` int(16) NOT NULL,
  `type` varchar(255) NOT NULL,
  `id_client` varchar(255) NOT NULL,
  `id_coach` varchar(255) NOT NULL,
  `champ` text NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `con1` (`id_client`),
  KEY `con2` (`id_coach`)
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
  `id_coach` varchar(255) NOT NULL,
  `id_client` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `visibilite` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ent1` (`id_client`),
  KEY `ent2` (`id_coach`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ligne_classement`
--

DROP TABLE IF EXISTS `ligne_classement`;
CREATE TABLE IF NOT EXISTS `ligne_classement` (
  `id` int(16) NOT NULL,
  `id_client` int(16) NOT NULL,
  `id_niveau` int(16) NOT NULL,
  `position` int(10) NOT NULL,
  `nb_points` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_client` (`id_client`),
  KEY `id_niveau` (`id_niveau`)
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
-- Structure de la table `reponse`
--

DROP TABLE IF EXISTS `reponse`;
CREATE TABLE IF NOT EXISTS `reponse` (
  `id` int(16) NOT NULL,
  `id_question` int(11) NOT NULL,
  `id_coach` varchar(255) NOT NULL,
  `id_client` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `reponse` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `rep1` (`id_coach`),
  KEY `rep2` (`id_client`)
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

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `activite`
--
ALTER TABLE `activite`
  ADD CONSTRAINT `act1` FOREIGN KEY (`idcoach`) REFERENCES `coach` (`id`);

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `art1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `art2` FOREIGN KEY (`id_coach`) REFERENCES `coach` (`id`);

--
-- Contraintes pour la table `challenge`
--
ALTER TABLE `challenge`
  ADD CONSTRAINT `challenge_ibfk_1` FOREIGN KEY (`id_niveau`) REFERENCES `niveau` (`id`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `com1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `com2` FOREIGN KEY (`id_coach`) REFERENCES `coach` (`id`);

--
-- Contraintes pour la table `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `con1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `con2` FOREIGN KEY (`id_coach`) REFERENCES `coach` (`id`);

--
-- Contraintes pour la table `entraide`
--
ALTER TABLE `entraide`
  ADD CONSTRAINT `ent1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `ent2` FOREIGN KEY (`id_coach`) REFERENCES `coach` (`id`);

--
-- Contraintes pour la table `ligne_classement`
--
ALTER TABLE `ligne_classement`
  ADD CONSTRAINT `ligne_classement_ibfk_1` FOREIGN KEY (`id_niveau`) REFERENCES `niveau` (`id`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `rep1` FOREIGN KEY (`id_coach`) REFERENCES `coach` (`id`),
  ADD CONSTRAINT `rep2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `therapie`
--
ALTER TABLE `therapie`
  ADD CONSTRAINT `th1` FOREIGN KEY (`idcoach`) REFERENCES `coach` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
