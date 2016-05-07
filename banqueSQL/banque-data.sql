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
INSERT INTO clientBanque VALUES (1,'banque','banque','banque','banque@gmail.com','76000',1),(2,'rochette','guillaume','grochette','rochette@gmail.com','76100',1),(3,'mouches','pauline','rochette','mouches@gmail.com','76100',1),
(4,'dupont','jean','jdupont','dupontj@gmail.com','91360',2),
(5,'renou','marie','mrenou','m.renou@gmail.com','75000',2),
(6,'jules','andre','ajules','andre.jules@gmail.com','91160',2),
(7,'moura','laurie','lmoura','lmoura@gmail.com','76100',3);
COMMIT;

--
-- Dumping data for table compteCourant
--

SET AUTOCOMMIT=0;
INSERT INTO compteCourant VALUES (1,100000,false,11,1);
INSERT INTO compteCourant VALUES (2,500,false,22,2);
INSERT INTO compteCourant VALUES (3,10,false,31,1);
INSERT INTO compteCourant VALUES (4,100,false,44,4);
INSERT INTO compteCourant VALUES (5,150,false,55,5);
INSERT INTO compteCourant VALUES (6,700,false,67,7);
COMMIT;

--
-- Dumping data for table compteEpargne
--

SET AUTOCOMMIT=0;
INSERT INTO compteEpargne VALUES (1,1000000,false,0,11,1),(2,100,false,0.4,11,2),(3,5000,false,0.2,11,3),(4,5500,false,0.12,11,4),(5,15000,false,0.2,11,7);
COMMIT;

--
-- Dumping data for table credit
--

SET AUTOCOMMIT=0;
INSERT INTO credit VALUES (1,1,5000,0.4,'2006-02-15 05:05:03',365);
COMMIT;





