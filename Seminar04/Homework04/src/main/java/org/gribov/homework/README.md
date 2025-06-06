
Прежде всего захожу в DBeaver и в консоли создаю в БД school_db 
и таблицу courses с полями (id, название, продолжительность).

~~~MySQl
create database `school_db`;

create table `school_db`.`courses` (`id` int not null auto_increment,
`название` varchar(45) null,
`продолжительность` int null,
primary key (`id`));
~~~~
