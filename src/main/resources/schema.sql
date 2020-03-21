drop table if exists shortener;
create table shortener(
    id int primary key,
    data varchar(255)
);

insert into shortener (id, `data`) values (1, '123456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ');

create table if not exists urls (
    id integer not null auto_increment,
    long_url varchar(1000) not null,
    short_url varchar(6) not null,
    creation_time varchar(255) not null,

    primary key (id)
);

insert into urls (`long_url`, `short_url`, `creation_time`)
        values ('https://www.youtube.com/', '222222', '2020-03-21T21:43:35.806725Z');

