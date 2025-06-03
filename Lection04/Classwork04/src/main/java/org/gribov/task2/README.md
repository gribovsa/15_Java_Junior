
Прежде всего захожу в DBeaver и в консоли создаю в БД test таблицу magic
Делаю вот такой запрос:

~~~MySQl
create table `test`.`magic` (`id` int not null auto_increment,
`название` varchar(45) null,
`повреждение` int null,
`атака` int null,
`броня` int null,
primary key (`id`));
~~~~
