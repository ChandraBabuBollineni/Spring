create table courses (
id int primary key auto_increment,
name varchar(50) not null, 
CONSTRAINT UK_name UNIQUE (name),
level_id int not null,
CONSTRAINT FK_level_id foreign key (level_id) references levels(id),
category_id int not null,
CONSTRAINT FK_category_id foreign key (category_id) references categories(id),
tags varchar(2000) not null,
slug varchar(100) not null,
is_level_over_ride boolean not null,
enrollment_point int not null 
constraint enrolmnt_pnts_chk CHECK (enrollment_point > 0) CHECK (enrollment_point < 10000) ,
completion_point int not null 
constraint completion_pnts_chk CHECK (completion_point > 0) CHECK (completion_point < 10000) ,
is_presignup boolean not null,
is_loggedin_via_slug boolean not null,
description varchar(100),
meta_keyword varchar(50) not null,
meta_description varchar(100),
icon MEDIUMBLOB not null,
icon_name varchar(150) not null,
created_by int not null,
CONSTRAINT FK_created_by foreign key (created_by) references course_users(id),
created_on timestamp default CURRENT_TIMESTAMP not null,
modified_on timestamp,
is_active boolean default 0,
version int not null default 1
);

create table course_users(id int primary key auto_increment,name varchar(200) not null,email varchar(200) not null,constraint UK_email unique(email), is_active boolean default 1);
drop table levels;
drop table categories;
drop table users;
drop table courses;
drop table course_reference_artifacts;
drop table course_refernce_urls;


create table levels (id int primary key auto_increment,name varchar(50),is_active boolean default 1);
create table categories (id int primary key auto_increment,name varchar(50),is_active boolean default 1);

select * from levels;
select * from categories;
select * from courses as c,course_users as cu where c.created_by=cu.id order by c.name desc LIMIT 2 OFFSET 0;
select * from course_reference_artifacts;
select * from course_reference_urls;
select * from course_users;
select * from courses where name='ghgyty';
SELECT LAST_INSERT_ID() as last;
INSERT INTO `chandra_db`.`course` (`name`, `level_id`, `category_id`, `tags`, `slug`, `level_over_ride`, `enrollment_point`, `completion_point`, `presignup`, `loggedin_via_slug`, `dashboard`, `description`, `meta_keyword`, `meta_description`, `reference_artifacts_id`, `refernce_url_id`) VALUES ('cc', '1', '1', 'sdfdsf', 'sdsfdsf', '1', '100', '100', '1', '1', '0', 'dffdgv', 'sdfsdf', 'ssdfg', '1', '1');

	SELECT * FROM course as c  right  JOIN reference_artifacts as refArt ON c.reference_artifacts_id = refArt.id RIGHT JOIN refernce_url as refUrl ON c.refernce_url_id = refUrl.id;

	SELECT * FROM course as c, reference_artifacts as refArt, refernce_url as refUrl where c.reference_artifacts_id = refArt.id and c.refernce_url_id = refUrl.id;
SELECT * FROM course as c, reference_artifacts as refArt, refernce_url as refUrl where c.reference_artifacts_id = refArt.id and c.refernce_url_id = refUrl.id and c.id=2;

create table course_reference_artifacts(id int primary key auto_increment,type varchar(200) not null,course_id int not null,CONSTRAINT FK_course_id_artifact foreign key (course_id) references courses(id),name varchar(200) not null,file MEDIUMBLOB not null,file_name varchar(150) not null,description varchar(100));
create table course_reference_urls(id int primary key auto_increment,type varchar(200) not null,course_id int not null, CONSTRAINT FK_course_id_url foreign key (course_id) references courses(id), name varchar(200) not null, url varchar(200) not null, is_tutorial boolean not null,description varchar(100));

insert into course (name,level_id,category_id,tags,slug,level_over_ride,enrollment_point,completion_point,presignup,loggedin_via_slug,dashboard,description,meta_keyword,meta_description,icon,reference_artifacts_id,refernce_url_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);


update reference_artifacts set name="fd",file=1010,description="cc" where id=1;

select * from course_reference_artifacts where course_id=2;

