drop database university;

create database if not exists university;

use university;

create table faculty(id INT AUTO_INCREMENT, name VARCHAR(40) NOT NULL UNIQUE, recruitment INT NOT NULL, PRIMARY KEY (id));

create table subject(id INT AUTO_INCREMENT, name VARCHAR(40) NOT NULL UNIQUE, PRIMARY KEY (id));

create table faculty_subject(faculty INT NOT NULL, subject INT NOT NULL);

create table user(id INT AUTO_INCREMENT, login VARCHAR(40) NOT NULL UNIQUE, password VARCHAR(100) NOT NULL,
role ENUM('enrollee','committee') NOT NULL, name VARCHAR(40) NOT NULL, surname VARCHAR(40) NOT NULL,
faculty INT, average_mark INT, application_status ENUM('waiting', 'registered', 'entered'), PRIMARY KEY (id));

create table certificate(id INT AUTO_INCREMENT, user INT NOT NULL, subject INT NOT NULL, mark INT NOT NULL, PRIMARY KEY (id));

insert into user (login, password, role, name, surname) values 
( 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'committee', 'Admin', 'Adminov'),
( 'user', '12dea96fec20593566ab75692c9949596833adc9', 'enrollee', 'Andrei', 'Andreev');

insert into user (login, password, role, name, surname, faculty, average_mark, application_status) values 
( 'userA', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiA', 'Andreev', 1, 50, 'waiting'),
( 'userB', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiB', 'Andreev', 1, 80, 'waiting'),
( 'userC', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiC', 'Andreev', 1, 30, 'waiting'),
( 'userD', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiD', 'Andreev', 1, 20, 'waiting'),
( 'userE', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiE', 'Andreev', 1, 50, 'waiting'),
( 'userF', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiF', 'Andreev', 1, 80, 'waiting'),
( 'userG', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiG', 'Andreev', 1, 90, 'waiting'),
( 'userH', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiH', 'Andreev', 1, 70, 'waiting'),
( 'userI', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiI', 'Andreev', 1, 80, 'waiting'),
( 'userJ', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiJ', 'Andreev', 1, 60, 'waiting'),
( 'userK', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiK', 'Andreev', 1, 70, 'waiting'),
( 'userL', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiL', 'Andreev', 1, 40, 'waiting'),
( 'userM', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiM', 'Andreev', 1, 10, 'waiting'),
( 'userN', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiN', 'Andreev', 1, 40, 'waiting'),
( 'userO', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiO', 'Andreev', 1, 50, 'waiting'),
( 'userP', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiP', 'Andreev', 1, 90, 'waiting'),
( 'userQ', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiQ', 'Andreev', 1, 90, 'waiting'),
( 'userR', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiR', 'Andreev', 1, 90, 'waiting'),
( 'userS', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiS', 'Andreev', 1, 80, 'waiting'),
( 'userT', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiT', 'Andreev', 1, 70, 'waiting'),
( 'userU', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiU', 'Andreev', 1, 80, 'waiting'),
( 'userV', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiV', 'Andreev', 1, 90, 'waiting'),
( 'userW', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiW', 'Andreev', 1, 80, 'waiting'),
( 'userX', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiX', 'Andreev', 1, 80, 'waiting'),
( 'userY', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiY', 'Andreev', 1, 40, 'waiting'),
( 'userZ', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiZ', 'Andreev', 1, 60, 'waiting'),
( 'userAA', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiA', 'Andropov', 3, 50, 'waiting'),
( 'userBB', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiB', 'Andropov', 3, 60, 'waiting'),
( 'userCC', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiC', 'Andropov', 3, 70, 'waiting'),
( 'userDD', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiD', 'Andropov', 3, 80, 'waiting'),
( 'userEE', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiE', 'Andropov', 3, 10, 'waiting'),
( 'userFF', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiF', 'Andropov', 3, 40, 'waiting'),
( 'userGG', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiG', 'Andropov', 3, 50, 'waiting'),
( 'userHH', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiH', 'Andropov', 3, 90, 'waiting'),
( 'userII', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiI', 'Andropov', 3, 40, 'waiting'),
( 'userJJ', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiJ', 'Andropov', 3, 20, 'waiting'),
( 'userKK', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiK', 'Andropov', 3, 30, 'waiting'),
( 'userLL', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiL', 'Andropov', 3, 20, 'waiting'),
( 'userMM', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiM', 'Andropov', 3, 10, 'waiting'),
( 'userNN', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiN', 'Andropov', 3, 50, 'waiting'),
( 'userOO', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiO', 'Andropov', 3, 20, 'waiting'),
( 'userPP', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiP', 'Andropov', 3, 50, 'waiting'),
( 'userQQ', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiQ', 'Andropov', 3, 50, 'waiting'),
( 'userRR', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiR', 'Andropov', 3, 30, 'waiting'),
( 'userSS', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiS', 'Andropov', 3, 50, 'waiting'),
( 'userTT', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiT', 'Andropov', 3, 40, 'waiting'),
( 'userUU', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiU', 'Andropov', 3, 90, 'waiting'),
( 'userVV', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiV', 'Andropov', 3, 80, 'waiting'),
( 'userWW', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiW', 'Andropov', 3, 80, 'waiting'),
( 'userXX', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiX', 'Andropov', 3, 80, 'waiting'),
( 'userYY', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiY', 'Andropov', 3, 70, 'waiting'),
( 'userZZ', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiZ', 'Andropov', 3, 50, 'waiting'),
( 'userAAA', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiAAA', 'Androp', 2, 50, 'waiting'),
( 'userBBB', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiBBB', 'Androp', 2, 60, 'waiting'),
( 'userCCC', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiCCC', 'Androp', 4, 70, 'waiting'),
( 'userDDD', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiDDD', 'Androp', 4, 80, 'waiting'),
( 'userAAB', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiAAB', 'Androp', 5, 15, 'waiting'),
( 'userBBC', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiBBC', 'Androp', 5, 30, 'waiting'),
( 'userCCD', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiCCD', 'Androp', 5, 40, 'waiting'),
( 'userDDE', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiDDE', 'Androp', 5, 90, 'waiting');

insert into certificate (user, subject, mark) values
(3, 1, 50), (3, 2, 50), (3, 3, 50),
(4, 1, 50), (4, 2, 50), (4, 3, 50),
(5, 1, 50), (5, 2, 50), (5, 3, 50),
(6, 1, 50), (6, 2, 50), (6, 3, 50),
(7, 1, 50), (7, 2, 50), (7, 3, 50),
(8, 1, 50), (8, 2, 50), (8, 3, 50),
(9, 1, 50), (9, 2, 50), (9, 3, 50),
(10, 1, 50), (10, 2, 50), (10, 3, 50),
(11, 1, 50), (11, 2, 50), (11, 3, 50),
(12, 1, 50), (12, 2, 50), (12, 3, 50),
(13, 1, 50), (13, 2, 50), (13, 3, 50),
(14, 1, 50), (14, 2, 50), (14, 3, 50),
(15, 1, 50), (15, 2, 50), (15, 3, 50),
(16, 1, 50), (16, 2, 50), (16, 3, 50),
(17, 1, 50), (17, 2, 50), (17, 3, 50),
(18, 1, 50), (18, 2, 50), (18, 3, 50),
(19, 1, 50), (19, 2, 50), (19, 3, 50),
(20, 1, 50), (20, 2, 50), (20, 3, 50),
(21, 1, 50), (21, 2, 50), (21, 3, 50),
(22, 1, 50), (22, 2, 50), (22, 3, 50),
(23, 1, 50), (23, 2, 50), (23, 3, 50),
(24, 1, 50), (24, 2, 50), (24, 3, 50),
(25, 1, 50), (25, 2, 50), (25, 3, 50),
(26, 1, 50), (26, 2, 50), (26, 3, 50),
(27, 1, 50), (27, 2, 50), (27, 3, 50),
(28, 1, 50), (28, 2, 50), (28, 3, 50),
(29, 1, 50), (29, 2, 50), (29, 3, 50),
(30, 1, 50), (30, 2, 50), (30, 3, 50),
(31, 1, 50), (31, 2, 50), (31, 3, 50),
(32, 1, 50), (32, 2, 50), (32, 3, 50),
(33, 1, 50), (33, 2, 50), (33, 3, 50),
(34, 1, 50), (34, 2, 50), (34, 3, 50),
(35, 1, 50), (35, 2, 50), (35, 3, 50),
(36, 1, 50), (36, 2, 50), (36, 3, 50),
(37, 1, 50), (37, 2, 50), (37, 3, 50),
(38, 1, 50), (38, 2, 50), (38, 3, 50),
(39, 1, 50), (39, 2, 50), (39, 3, 50),
(40, 1, 50), (40, 2, 50), (40, 3, 50),
(41, 1, 50), (41, 2, 50), (41, 3, 50),
(42, 1, 50), (42, 2, 50), (42, 3, 50),
(43, 1, 50), (43, 2, 50), (43, 3, 50),
(44, 1, 50), (44, 2, 50), (44, 3, 50),
(45, 1, 50), (45, 2, 50), (45, 3, 50),
(46, 1, 50), (46, 2, 50), (46, 3, 50),
(47, 1, 50), (47, 2, 50), (47, 3, 50),
(48, 1, 50), (48, 2, 50), (48, 3, 50),
(49, 1, 50), (49, 2, 50), (49, 3, 50),
(50, 1, 50), (50, 2, 50), (50, 3, 50),
(51, 1, 50), (51, 2, 50), (51, 3, 50),
(52, 1, 50), (52, 2, 50), (52, 3, 50),
(53, 1, 50), (53, 2, 50), (53, 3, 50),
(54, 1, 50), (54, 2, 50), (54, 3, 50),
(55, 1, 50), (55, 4, 50), (55, 2, 50),
(56, 1, 50), (56, 4, 50), (56, 2, 50),
(57, 1, 50), (57, 4, 50), (57, 2, 50),
(58, 1, 50), (58, 4, 50), (58, 2, 50),
(59, 1, 50), (59, 5, 50), (59, 6, 50),
(60, 1, 50), (60, 5, 50), (60, 6, 50),
(61, 1, 50), (61, 5, 50), (61, 6, 50),
(62, 1, 50), (62, 5, 50), (62, 6, 50);

insert into faculty (name, recruitment) values
( 'mathematical', 20),
( 'linguistic', 25),
( 'physical', 22),
( 'economic', 10),
( 'biological', 10),
( 'geographical', 10);

insert into subject (name) values
('language'),
('mathematics'),
('physics'),
('foreign_language'),
('chemistry'),
('biology'),
('geography');

insert into faculty_subject (faculty, subject) values
(1 , 1), (1 , 2), (1 , 3),
(2 , 1), (2 , 4), (2 , 2),
(3 , 1), (3 , 2), (3 , 3),
(4 , 1), (4 , 4), (4 , 2),
(5 , 1), (5 , 5), (5 , 6),
(6 , 1), (6 , 6), (6 , 7);

use university;

select subject.id, subject.name from faculty_subject inner join subject on faculty_subject.subject = subject.id where faculty_subject.faculty = 6;

select (user.average_mark + marks.sum) as amount from user 
inner join (select user, sum(mark) as sum from certificate group by user) marks on marks.user=user.id
 where user.faculty = 5;