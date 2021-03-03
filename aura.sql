-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 03 mars 2021 à 09:39
-- Version du serveur :  5.7.31
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `aura`
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
('12', 'Ahmed', 'ou', 'aa', 'aa'),
('2', 'nasri', 'chirine', 'aaaa', '55');

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

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom`, `prenom`, `email`, `tel`, `adresse`) VALUES
('1', 'nasri', 'chirine', 'chirine.nasri@esprit.tn', '53452940', 'aaaaa');

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
-- Structure de la table `objectif`
--

DROP TABLE IF EXISTS `objectif`;
CREATE TABLE IF NOT EXISTS `objectif` (
  `id` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `reponse` int(11) NOT NULL,
  `dateDebut` varchar(50) NOT NULL,
  `duree` int(11) NOT NULL,
  `idClient` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ObjCli` (`idClient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `objectif`
--

INSERT INTO `objectif` (`id`, `description`, `reponse`, `dateDebut`, `duree`, `idClient`) VALUES
('1', 'bbbb', 3, '01/03/2021', 2, '1'),
('10', 'vv', 2, '01/03/2021', 4, '1'),
('100', 'Je veux lire pendant X heures', 2, '03/03/2021', 5, '1'),
('2', 'bbbb', 2, '01/03/2021', 2, '1'),
('3', 'hh', 1, '01/03/2021', 7, '1'),
('4', 'aaaa', 1, '01/03/2021', 7, '1'),
('5', 'null', 2, '01/03/2021', 5, '1'),
('55', 'Je veux parler à X personnes', 2, '03/03/2021', 5, '1'),
('6', 'null', 1, '01/03/2021', 3, '1'),
('7', '4', 4, '01/03/2021', 4, '1'),
('9', 'Je veux parler à X personnes', 3, '03/03/2021', 2, '1');

-- --------------------------------------------------------

--
-- Structure de la table `objectif_pred`
--

DROP TABLE IF EXISTS `objectif_pred`;
CREATE TABLE IF NOT EXISTS `objectif_pred` (
  `id` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `duree` int(11) NOT NULL,
  `idAdmin` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ObjPAdmin` (`idAdmin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `objectif_pred`
--

INSERT INTO `objectif_pred` (`id`, `description`, `duree`, `idAdmin`) VALUES
('P1', 'Je veux attribuer la note X à ma journée', 1, '2'),
('P2', 'Je veux parler à X personnes', 3, '2'),
('P3', 'Je veux lire pendant X heures', 7, '2'),
('P4', 'Je veux diminuer mon temps d écran de X heures', 7, '2'),
('P5', 'Je veux boire X litres d\'eau', 1, '2'),
('P6', 'Je veux faire X pas', 1, '2'),
('P7', 'Je veux faire X minutes de médiation', 1, '2');

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
-- Structure de la table `suivi`
--

DROP TABLE IF EXISTS `suivi`;
CREATE TABLE IF NOT EXISTS `suivi` (
  `id` varchar(11) NOT NULL,
  `valeur` int(11) NOT NULL,
  `idClient` varchar(50) NOT NULL,
  `idObjectif` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_SuivCli` (`idClient`),
  KEY `fk_SuivObj` (`idObjectif`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `suivi`
--

INSERT INTO `suivi` (`id`, `valeur`, `idClient`, `idObjectif`, `date`) VALUES
('4', 3, '1', '100', '03/03/2021');

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
-- Contraintes pour la table `objectif`
--
ALTER TABLE `objectif`
  ADD CONSTRAINT `fk_ObjCli` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `objectif_pred`
--
ALTER TABLE `objectif_pred`
  ADD CONSTRAINT `fk_ObjPAdmin` FOREIGN KEY (`idAdmin`) REFERENCES `admin` (`id`);

--
-- Contraintes pour la table `participationactivté`
--
ALTER TABLE `participationactivté`
  ADD CONSTRAINT `part1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `participationtherapie`
--
ALTER TABLE `participationtherapie`
  ADD CONSTRAINT `part2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `rep1` FOREIGN KEY (`id_coach`) REFERENCES `coach` (`id`),
  ADD CONSTRAINT `rep2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `suivi`
--
ALTER TABLE `suivi`
  ADD CONSTRAINT `fk_SuivCli` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `fk_SuivObj` FOREIGN KEY (`idObjectif`) REFERENCES `objectif` (`id`);

--
-- Contraintes pour la table `therapie`
--
ALTER TABLE `therapie`
  ADD CONSTRAINT `th1` FOREIGN KEY (`idcoach`) REFERENCES `coach` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
