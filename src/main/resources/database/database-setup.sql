CREATE DATABASE todoDB;
CREATE USER 'todo'@'localhost' IDENTIFIED BY 'todo';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, INDEX, DROP, ALTER, CREATE TEMPORARY TABLES, LOCK TABLES ON todoDB.* TO 'todo'@'localhost';


