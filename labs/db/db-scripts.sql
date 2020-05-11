CREATE DATABASE jdbctraining;

USE jdbctraining;

CREATE TABLE employee (	
	id int,
	name text,
	age int,
	designation text,
	department text,
	country text,
	PRIMARY KEY(id)
);

CREATE TABLE COMPANY(
   ID INT PRIMARY KEY     NOT NULL,
   NAME           TEXT    NOT NULL,
   AGE            INT     NOT NULL,
   ADDRESS        CHAR(50),
   SALARY         REAL
);

CREATE SCHEMA hibernate_training;

USE hibernate_training;

CREATE TABLE hibernate_training.employee (
	id int(11) AUTO_INCREMENT,
	name varchar(255),
	age int(3),
	gender varchar(10),
	designation varchar(255),
	department varchar(255),
	address varchar(255),
	country varchar(255),
	contractor boolean,
	PRIMARY KEY(id)
);

SELECT * FROM employee;

TRUNCATE employee;

SELECT * FROM employee;
INSERT INTO employee (id, name, age, designation, department, country) VALUES (100, 'Anand', 25, 'Developer', 'IT', 'India'); 
UPDATE employee SET designation = 'IT' WHERE id = 100;
DELETE FROM employee WHERE id = 100;

DROP TABLE employee;
DROP DATABASE jdbctraining;