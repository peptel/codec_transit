
select * from n_task_messages_log l left join n_task_messages_status s on s.codec_id = l.codec_id order by l.id desc;

select * from n_task_messages_log l order by id desc; 
select * from n_task_messages_status order by id desc;

select t.*, m.* from n_tasks t join n_task_messages m on m.task_id = t.id order by t.id;

select * from n_task_messages order by id desc;
select * from n_tasks order by id desc;

select t.*, m.* from n_tasks t join n_task_messages m on m.task_id = t.id 
where t.id >=57 order by t.id desc;

select * from n_task_messages_log l left join n_task_messages_status s on s.codec_id = l.codec_id 
where l.id > 118
order by l.id desc;