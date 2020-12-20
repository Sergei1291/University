insert into user (login, password, role, name, surname) values 
( 'user', 'a2b7caddbc353bd7d7ace2067b8c4e34db2097a3', 'enrollee', 'Andrei', 'Andreev'),
( 'userA', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiA', 'Andreev'),
( 'userB', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiB', 'Andreev'),
( 'userC', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiC', 'Andreev'),
( 'userD', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiD', 'Andreev'),
( 'userE', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiE', 'Andreev'),
( 'userF', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiF', 'Andreev'),
( 'userG', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiG', 'Andreev'),
( 'userH', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiH', 'Andreev'),
( 'userI', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiI', 'Andreev'),
( 'userJ', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiJ', 'Andreev'),
( 'userK', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiK', 'Andreev'),
( 'userL', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiL', 'Andreev'),
( 'userM', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiM', 'Andreev'),
( 'userN', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiN', 'Andreev'),
( 'userO', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiO', 'Andreev'),
( 'userP', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiP', 'Andreev'),
( 'userQ', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiQ', 'Andreev'),
( 'userR', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiR', 'Andreev'),
( 'userS', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiS', 'Andreev'),
( 'userT', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiT', 'Andreev'),
( 'userU', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiU', 'Andreev'),
( 'userV', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiV', 'Andreev'),
( 'userW', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiW', 'Andreev'),
( 'userX', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiX', 'Andreev'),
( 'userY', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiY', 'Andreev'),
( 'userZ', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'enrollee', 'AndreiZ', 'Andreev'),
( 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'committee', 'Ivan', 'Adminov');

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

insert into faculty_requirement (faculty, subject) values
(1 , 2), (1 , 1), (1 , 3),
(2 , 2), (2 , 4), (2 , 1),
(3 , 2), (3 , 1), (3 , 3),
(4 , 2), (4 , 1), (4 , 4),
(5 , 2), (5 , 5), (5 , 6),
(6 , 2), (6 , 6), (6 , 7);

use university;

select subject.id, subject.name from faculty_requirement inner join subject on faculty_requirement.subject = subject.id where faculty_requirement.faculty = 6;

update user set faculty=1, average_mark=50, application_status='registered' where user.id = 2;
update user set faculty=null, average_mark=null, application_status=null where id = 2;
select * from user;

select*from certificate;
