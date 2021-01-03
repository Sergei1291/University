insert into user (login, password, role, name, surname) values 
( 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'committee', 'Admin', 'Adminov'),
( 'user', '12dea96fec20593566ab75692c9949596833adc9', 'enrollee', 'Andrei', 'Andreev'),
( 'user1', '12dea96fec20593566ab75692c9949596833adc9', 'enrollee', 'Andre1', 'Andreev'),
( 'user2', '12dea96fec20593566ab75692c9949596833adc9', 'enrollee', 'Andrei2', 'Andreev');

insert into user (login, password, role, name, surname, faculty, average_mark, application_status) values 
( 'userA', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiA', 'Andreev', 1, 10, 'waiting'),
( 'userB', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiB', 'Andreev', 1, 20, 'waiting'),
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
( 'userZ', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiZ', 'Andreev', 1, 60, 'waiting');

insert into certificate (user, subject, mark) values
(5, 1, 50), (5, 1, 50), (5, 1, 50),
(6, 1, 50), (6, 1, 50), (6, 1, 50),
(7, 1, 50), (7, 1, 50), (7, 1, 50),
(8, 1, 50), (8, 1, 50), (8, 1, 50),
(9, 1, 50), (9, 1, 50), (9, 1, 50),
(10, 1, 50), (10, 1, 50), (10, 1, 50),
(11, 1, 50), (11, 1, 50), (11, 1, 50),
(12, 1, 50), (12, 1, 50), (12, 1, 50),
(13, 1, 50), (13, 1, 50), (13, 1, 50),
(14, 1, 50), (14, 1, 50), (14, 1, 50),
(15, 1, 50), (15, 1, 50), (15, 1, 50),
(16, 1, 50), (16, 1, 50), (16, 1, 50),
(17, 1, 50), (17, 1, 50), (17, 1, 50),
(18, 1, 50), (18, 1, 50), (18, 1, 50),
(19, 1, 50), (19, 1, 50), (19, 1, 50),
(20, 1, 50), (20, 1, 50), (20, 1, 50),
(21, 1, 50), (21, 1, 50), (21, 1, 50),
(22, 1, 50), (22, 1, 50), (22, 1, 50),
(23, 1, 50), (23, 1, 50), (23, 1, 50),
(24, 1, 50), (24, 1, 50), (24, 1, 50),
(25, 1, 50), (25, 1, 50), (25, 1, 50),
(26, 1, 50), (26, 1, 50), (26, 1, 50),
(27, 1, 50), (27, 1, 50), (27, 1, 50),
(28, 1, 50), (28, 1, 50), (28, 1, 50),
(29, 1, 50), (29, 1, 50), (29, 1, 50),
(30, 1, 50), (30, 1, 50), (30, 1, 50);

insert into faculty (name, recruitment) values
( 'mathematical', 20),
( 'linguistic', 20),
( 'physical', 20),
( 'economic', 20),
( 'biological', 20),
( 'geographical', 20);

insert into subject (name) values
('mathematics'),
('language'),
('physics'),
('foreign_language'),
('chemistry'),
('biology'),
('geography');

insert into faculty_subject (faculty, subject) values
(1 , 2), (1 , 1), (1 , 3),
(2 , 2), (2 , 4), (2 , 1),
(3 , 2), (3 , 1), (3 , 3),
(4 , 2), (4 , 1), (4 , 4),
(5 , 2), (5 , 5), (5 , 6),
(6 , 2), (6 , 6), (6 , 7);

use university;

select subject.id, subject.name from faculty_subject inner join subject on faculty_subject.subject = subject.id where faculty_subject.faculty = 6;

select * from user;
select * from certificate;