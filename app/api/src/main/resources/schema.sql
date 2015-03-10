drop table if exists users;

create table users (
	id varchar(100) primary key,
	email varchar(100),
	name varchar(100),
	password_hash varchar(100)
);
