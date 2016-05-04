SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE banque;

--
-- Dumping data for table banque
--

SET AUTOCOMMIT=0;
INSERT INTO banque VALUES (1,'BNP','Paris'),
(2,'BNP','Rouen'),
(3,'Societe Generale','Evreux');
COMMIT;

--
-- Dumping data for table personnel
--

SET AUTOCOMMIT=0;
INSERT INTO personnel VALUES (1,'pauline','pmouches','Gerant',1),
(2,'quentin','qlerebours','Gerant',2),(3,'kafui','kaf','Gerant',3),(4,'admin','admin','Admin',3);
COMMIT;

--
-- Dumping data for table clientBanque
--

SET AUTOCOMMIT=0;
INSERT INTO clientBanque VALUES (1,'rochette','guillaume','grochette','rochette@gmail.com','76100',1),(2,'mouches','pauline','rochette','mouches@gmail.com','76100',1);
COMMIT;

--
-- Dumping data for table compteCourant
--

SET AUTOCOMMIT=0;
INSERT INTO compteCourant VALUES (1,100,false,11,1);
INSERT INTO compteCourant VALUES (2,500,false,22,2);
INSERT INTO compteCourant VALUES (3,10,false,21,1);
COMMIT;

--
-- Dumping data for table compteEpargne
--

SET AUTOCOMMIT=0;
INSERT INTO compteEpargne VALUES (1,100,false,0.4,11,1);
COMMIT;

--
-- Dumping data for table credit
--

SET AUTOCOMMIT=0;
INSERT INTO credit VALUES (1,1,5000,0.4,'2006-02-15 05:05:03',365);
COMMIT;





