drop database university;

create database if not exists university;

use university;

create table faculty(id INT AUTO_INCREMENT, name VARCHAR(40) NOT NULL UNIQUE, recruitment INT NOT NULL, PRIMARY KEY (id));

create table subject(id INT AUTO_INCREMENT, name VARCHAR(40) NOT NULL UNIQUE, PRIMARY KEY (id));

create table faculty_subject(faculty INT NOT NULL, subject INT NOT NULL);

create table user(id INT AUTO_INCREMENT, login VARCHAR(40) NOT NULL UNIQUE, password VARCHAR(100) NOT NULL,
role ENUM('enrollee','committee') NOT NULL, name VARCHAR(40) NOT NULL, surname VARCHAR(40) NOT NULL, PRIMARY KEY (id));

create table certificate(id INT AUTO_INCREMENT, application INT NOT NULL, subject INT NOT NULL, mark INT NOT NULL, PRIMARY KEY (id));

create table application(id INT AUTO_INCREMENT, user INT NOT NULL, faculty INT NOT NULL, average_mark INT NOT NULL,
status ENUM('applied', 'cancelled', 'registered', 'entered', 'unentered'), PRIMARY KEY (id));