DROP TABLE IF EXISTS persons;
CREATE TABLE persons
(
    id           int primary key auto_increment,
    firstName    varchar(65) not null,
    lastName     varchar(75) not null,
    nationalCode varchar(10) not null,
    age          int         not null,
    email        varchar(50) not null,
    mobile       varchar(15) not null,
    constraint nationalCode unique (nationalCode)
);