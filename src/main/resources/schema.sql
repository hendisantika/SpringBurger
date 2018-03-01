
-- Database Creation
CREATE DATABASE burgers;
USE burgers_db;


-- Table Creation of Burgers
CREATE TABLE burgers(
id INTEGER AUTO_INCREMENT PRIMARY KEY,
burgerName VARCHAR(50),
devoured BOOLEAN);


-- Table Creation of Devourers
CREATE TABLE devourers(
id INTEGER AUTO_INCREMENT PRIMARY KEY,
devourerName VARCHAR(50),
burgerId INTEGER);


-- Seed Burgers into Database
INSERT INTO burgers(burgerName, devoured)
VALUES ("Big Mac", false),
  ("Whooper", false),
  ("Cheezburger", false),
  ("Baconator", false),
  ("Quarter Pounder", false),
  ("McRoyal", false),
  ("Double Double", true);


-- Seed Devourers into Database
INSERT INTO devourers(devourerName, burgerId)
VALUES ("Tommy", 7);