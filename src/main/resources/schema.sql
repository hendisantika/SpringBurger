
-- Database Creation
CREATE
DATABASE IF NOT EXISTS burgers;
USE burgers;


-- Table Creation of Burgers
CREATE TABLE if NOT EXISTS burgers
(
id INTEGER AUTO_INCREMENT PRIMARY KEY,
burger_name
VARCHAR
(
50
),
devoured BOOLEAN);


-- Table Creation of Devourers
CREATE TABLE if NOT EXISTS devourers
(
id INTEGER AUTO_INCREMENT PRIMARY KEY,
devourer_name
VARCHAR
(
50
),
    burger_id INTEGER);


-- Seed Burgers into Database
INSERT INTO burgers(burger_name, devoured)
VALUES ("Big Mac", false),
  ("Whooper", false),
  ("Cheezburger", false),
  ("Baconator", false),
  ("Quarter Pounder", false),
  ("McRoyal", false),
  ("Double Double", true);


-- Seed Devourers into Database
INSERT INTO devourers(devourer_name, burger_id)
VALUES ("Tommy", 7);