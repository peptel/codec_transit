select * from n_task_messages_log ntml left join n_task_messages_status s on s.codec_id = ntml.codec_id 
where to_user_id = 380668117771 and task_id = 272 and s.id is null;

select * from n_task_messages_log ntml left join n_task_messages_status s on s.codec_id = ntml.codec_id 
where to_user_id = 380668117771 and s.id is null;

select * from admins;

select * from users where phone = 380778117771;
select * from users where phone = 380668117771;

update users set balance = 27600 where id = 3179;

select * from bar_codes bc where user_id = 3179; #1008929524330;
select * from bar_codes bc where user_id = 206;

update bar_codes set bar_code = '1008929524330' where partner_id in (4, 5, 6, 7, 9) and user_id = 3179;

select * from users where email like '%smikhasik%';

select * from n_task_messages ntm where task_id = 272 and to_user_id = 380668117771 order by id desc;

update n_task_messages set status = 0 where id = 27078;

delete from n_task_messages_log ntml where id in (
select ntml.id from n_task_messages_log ntml left join n_task_messages_status s on s.codec_id = ntml.codec_id where to_user_id = 380668117771 and task_id = 272 and s.id is null
);

select * from n_task_messages ntm