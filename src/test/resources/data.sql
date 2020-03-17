create table if not exists show (
    id integer not null auto_increment,
    first varchar(255) not null,
    second varchar(255) not null,

    primary key (id)
);

insert into show (`first`, `second`) values ('first value', 'second value');

insert into urls (`long_url`, `short_url`) values ('https://www.google.com/', '111111');