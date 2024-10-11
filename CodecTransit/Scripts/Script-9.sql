select * from n_task_messages where tp = '02' and to_user_id = 0 and task_id in (426, 396, 395, 403, 404, 436, 435, 272);

insert into n_task_messages(from_user_id, to_user_id, tp, message_date, message_text, image, button_caption, button_url, status, codec, task_id)
select from_user_id, to_user_id, '03', now(), message_text, image, button_caption, button_url, status, codec, task_id from n_task_messages where tp = '02' and to_user_id = 0 and task_id in (426, 396, 395, 403, 404, 436, 435, 272);

select * from n_task_messages where to_user_id = 380974133399;

select * from checking_registry where phone  = 380974133399;

select * from n_task_messages where tp = '03' and task_id = 272;

select * from admins where name = 'EDG';

select * from partners;
select count(*), amount from discr_certificates where partner_id = 11 and status = 0 group by amount;

select u.phone, b.* from bar_codes b join users u on u.id = b.user_id where partner_id = 4;
