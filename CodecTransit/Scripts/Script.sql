#new DB tables for tasks

CREATE TABLE n_tasks (
	id serial NOT NULL,
	task_type int4 NULL,
	task_schema int4 NULL,
	cost int4 NULL,
	auto_cost int4 NULL,
	correct_answer varchar(50) NULL,
	task_start timestamp NULL,
	task_finish_date timestamp NULL,
	status int4 NULL,
	CONSTRAINT tasks_pk PRIMARY KEY (id)
);

CREATE TABLE n_task_questions (
	id serial NOT NULL,
	task_id int4 NULL,
	task_message varchar(100) NULL,
	CONSTRAINT tq_pk PRIMARY KEY (id)
);

CREATE TABLE n_task_messages (
	id serial NOT NULL,
	from_user_id numeric(12) NULL,
	to_user_id numeric(12) NULL,
	tp varchar(50) NULL,
	message_date timestamp NULL,
	message_text varchar(1000) NULL,
	image varchar(150) NULL,
	button_caption varchar(100) NULL,
	button_url varchar(150) NULL,
	status int4 NULL,
	codec int4 NULL,
	task_id int4 NULL,
	CONSTRAINT tm_pk PRIMARY KEY (id)
);

CREATE TABLE n_task_messages_log (
	id serial NOT NULL,
	from_user_id numeric(12) NULL,
	to_user_id numeric(12) NULL,
	message_date timestamp NULL,
	message_text varchar(1000) NULL,
	image varchar(150) NULL,
	button_caption varchar(40) NULL,
	button_url varchar(150) NULL,
	codec int4 NULL,
	codec_id varchar(50) NULL,
	message_id varchar(50) NULL,
	task_id int4 NULL,
	CONSTRAINT tml_pk PRIMARY KEY (id)
);

CREATE TABLE n_task_messages_status (
	id serial NOT NULL,
	message_date timestamp NULL,
	codec int4 NULL,
	codec_id varchar(50) NULL,
	status int4 NULL,
	CONSTRAINT tms_pk PRIMARY KEY (id)
);


INSERT INTO n_tasks(task_type, task_schema, cost, auto_cost, correct_answer, task_start_date, task_finish_date, status) VALUES(?, ?, ?, ?, ?, NOW(), '', 0);

select * from n_tasks;
select * from n_task_questions;
select * from n_task_messages;
select * from n_task_messages_log;
select * from n_task_messages_status;

select * from users  where phone = 380503801077;

delete from users where id = 72;

select * from admins;
select * from admin_users;

select u.id, u.email, u.phone, u.balance, u.name, au.user_id, au.id from users u left join admin_users au on au.user_id = u.id and au.admin_id = ? and au.status > -1 where u.company_id = ? ;

UPDATE A
SET A.c1 = expresion
FROM B
WHERE A.c2 = B.c2;

update 
