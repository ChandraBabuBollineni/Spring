create table course (
id int primary key auto_increment,
name varchar(50) not null, 
CONSTRAINT uk_name UNIQUE (name),
level_id int not null,
CONSTRAINT fk_level_id foreign key (level_id) references level(id),
category_id int not null,
CONSTRAINT fk_category_id foreign key (category_id) references category(id),
tags varchar(100) not null,
slug varchar(100) not null,
level_over_ride boolean not null,
enrollment_point int not null 
constraint enrolmnt_pnts_chk CHECK (enrollment_point > 0) CHECK (enrollment_point < 10000) ,
completion_point int not null 
constraint completion_pnts_chk CHECK (completion_point > 0) CHECK (completion_point < 10000) ,
presignup boolean not null,
loggedin_via_slug boolean not null,
dashboard boolean not null,
description varchar(100) not null,
meta_keyword varchar(50) not null,
meta_description varchar(100) not null,
icon blob not null,
reference_artifacts_id int not null,
CONSTRAINT fk_reference_artifacts_id foreign key (reference_artifacts_id) references reference_artifacts(id),
refernce_url_id int not null,
CONSTRAINT fk_refernce_url foreign key (refernce_url_id) references refernce_url(id)
);

create table level (id int primary key auto_increment,name varchar(50));
create table category (id int primary key auto_increment,name varchar(50));


create table reference_artifacts(id int primary key auto_increment,type varchar(50) not null,name varchar(50) not null,file blob,description varchar(100) not null);
create table refernce_url(id int primary key auto_increment,type varchar(50) not null, name varchar(50) not null, url varchar(100) not null, tutorial boolean not null,description varchar(100) not null);


