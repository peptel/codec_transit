select * from n_tasks order by id desc;
select * from n_task_messages order by id desc;

select * from n_task_messages_log l left join n_task_messages_status s on s.codec_id = l.codec_id where  task_id = 69 order by l.id desc;
select * from n_task_messages_log l left join n_task_messages_status s on s.codec_id = l.codec_id where s.id is null and l.codec_id <> 2790036 and task_id = 72 order by l.id desc;
select * from n_task_messages_log l left join n_task_messages_status s on s.codec_id = l.codec_id where s.id is null and  l.task_id = 69 order by l.id desc;
select * from n_task_messages_log l left join n_task_messages_status s on s.codec_id = l.codec_id where s.id is null and l.codec_id <> 2660096 and l.task_id = 69 order by l.id desc;

select l.task_id, count(*) as total from n_task_messages_log l left join n_task_messages_status s on s.codec_id = l.codec_id where s.id is null group by l.task_id order by total desc;


select * from n_task_messages_status where codec_id = 2720003;

INSERT INTO n_task_messages_status(status_date, codec, codec_id, status, answer) 
(SELECT NOW(), l.codec, l.codec_id, 8, 'OK value' from n_task_messages_log l where l.task_id = 69 and codec_id <> 2660096) 
ON CONFLICT ON CONSTRAINT messages_status_codec_id DO nothing;

INSERT INTO n_task_messages_status(status_date, codec, codec_id, status, answer) 
VALUES(NOW(), 0, 2670061, 3, 'OK. Value') ON CONFLICT ON CONSTRAINT messages_status_codec_id DO nothing;

update n_task_messages_status set status = 1 where status > 7;
select codec_id, count(*) as countv from n_task_messages_status group by codec_id order by countv;
select * from n_task_messages_status where codec_id = 2670061;

delete from n_task_messages_status where codec_id in (0, 2670061);

select distinct to_user_id from n_task_messages;