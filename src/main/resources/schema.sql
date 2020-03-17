drop table if exists shortener;
create table shortener(
    id int primary key,
    data varchar(255)
);

insert into shortener (id, `data`) values (1, '123456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ');

create table if not exists urls (
    id integer not null auto_increment,
    long_url varchar(255) not null,
    short_url varchar(6) not null,

    primary key (id)
);

insert into urls (`long_url`, `short_url`) values ('https://www.youtube.com/', '222222');

