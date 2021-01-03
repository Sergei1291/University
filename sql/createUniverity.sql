create database if not exists university;

use university;

create table faculty(id INT AUTO_INCREMENT, name VARCHAR(40) NOT NULL UNIQUE, recruitment INT NOT NULL, PRIMARY KEY (id));

create table subject(id INT AUTO_INCREMENT, name VARCHAR(40) NOT NULL UNIQUE, PRIMARY KEY (id));

create table faculty_subject(faculty INT NOT NULL, subject INT NOT NULL);

create table user(id INT AUTO_INCREMENT, login VARCHAR(40) NOT NULL UNIQUE, password VARCHAR(100) NOT NULL, role ENUM('enrollee','committee') NOT NULL,
name VARCHAR(40) NOT NULL, surname VARCHAR(40) NOT NULL, faculty INT, average_mark INT, application_status ENUM('waiting', 'registered'), PRIMARY KEY (id));

create table certificate(user INT NOT NULL, subject INT NOT NULL, mark INT NOT NULL);