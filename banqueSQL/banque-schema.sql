
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


DROP SCHEMA IF EXISTS banque;
CREATE SCHEMA banque;
USE banque;

--
-- Table structure for table `banque`
--

CREATE TABLE banque (
  banque_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nom VARCHAR(45) NOT NULL,
  ville VARCHAR(45) NOT NULL,
  PRIMARY KEY  (banque_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `personnel`
--

CREATE TABLE personnel (
  personnel_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nom VARCHAR(50) NOT NULL,
  motdepasse VARCHAR(50) DEFAULT NULL,
  role ENUM('Gerant','Employe','Admin'),
  banque_id SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY  (personnel_id),
  KEY idx_fk_banque_id (banque_id),
  CONSTRAINT `fk_personnel_banque` FOREIGN KEY (banque_id) REFERENCES banque (banque_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `clientBanque`
--

CREATE TABLE clientBanque (
  clientBanque_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nom VARCHAR(50) NOT NULL,
  prenom VARCHAR(50) NOT NULL,
  motdepasse VARCHAR(50) DEFAULT NULL,
  email VARCHAR(50) DEFAULT NULL,
  codePostal VARCHAR(50) DEFAULT NULL,
  banque_id SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY  (clientBanque_id),
  KEY idx_fk_banque_id (banque_id),
  CONSTRAINT `fk_clientBanque_banque` FOREIGN KEY (banque_id) REFERENCES banque (banque_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `compteCourant`
--

CREATE TABLE compteCourant (
  compteCourant_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  montant FLOAT,
  bloque BOOL,
  iban SMALLINT UNSIGNED NOT NULL,
  clientBanque_id SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY  (compteCourant_id),
  KEY idx_fk_clientBanque_id (clientBanque_id),
  CONSTRAINT `fk_compteCourant_clientBanque` FOREIGN KEY (clientBanque_id) REFERENCES clientBanque (clientBanque_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `compteEpargne`
--

CREATE TABLE compteEpargne (
  compteEpargne_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  montant FLOAT,
  bloque BOOL,
  tauxInteret SMALLINT UNSIGNED,
  iban SMALLINT UNSIGNED NOT NULL,
  clientBanque_id SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY  (compteEpargne_id),
  KEY idx_fk_clientBanque_id (clientBanque_id),
  CONSTRAINT `fk_compteEpargne_clientBanque` FOREIGN KEY (clientBanque_id) REFERENCES clientBanque (clientBanque_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
-- Table structure for table `operation`
--


CREATE TABLE operation (
  operation_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  type VARCHAR(50) NOT NULL,
  compte_debiteur_id SMALLINT UNSIGNED NOT NULL,
  compte_crediteur_id SMALLINT UNSIGNED NOT NULL,
  montant FLOAT,
  PRIMARY KEY  (operation_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
-- Table structure for table `credit`
--

CREATE TABLE credit (
  credit_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  compteEpargne_id SMALLINT UNSIGNED NOT NULL,
  montant FLOAT,
  taux SMALLINT UNSIGNED,
  dateEmprunt DATETIME NOT NULL,
  duree SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY  (credit_id),
  KEY idx_fk_compteEpargne_id (compteEpargne_id),
  CONSTRAINT fk_credit_compteEpargne FOREIGN KEY (compteEpargne_id) REFERENCES compteEpargne (compteEpargne_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

