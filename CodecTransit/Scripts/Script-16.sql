select * from users where phone = 380741258963;
select * from users where email = 'd@gmail.com';


select * from n_tasks nt order by id desc limit 10;
select * from n_task_messages ntm where to_user_id = 380666666666;

select * from n_task_messages ntm where task_id > 448;