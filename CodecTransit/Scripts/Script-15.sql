select * from n_task_messages_log l left join n_task_messages_status s on s.codec_id = l.codec_id where tp = 'KR' and task_id = 395 and s.id is null;

select * from n_task_messages_log l left join n_task_messages_status s on s.codec_id = l.codec_id where to_user_id = 380977081583 and task_id = 396 and s.id is null;

select * from users u left join bar_codes b on b.user_id = u.id where u.phone = 380503621874;
select * from users u left join partner_balance p on u.id = p.user_id where u.phone = 380503621874;

select * from partner_transactions_certificates where user_id = 1909;

select * from admins where name = 'Epson';
select * from admins where login like '%mti%';

select to_user_id, count(*) as total from n_task_messages_log l left join n_task_messages_status s on s.codec_id = l.codec_id where s.id is not null group by to_user_id order by total desc;

select a.id, a."name" ,ra.id from admins a left join retails_admins ra on ra.admin_id = a.id where creator_id = 8 and ra.retail_id is null;

select * from retails_admins where admin_id in (46, 88, 187);

insert into retails_admins(retail_id, admin_id) select 16, a.id from admins a left join retails_admins ra on ra.admin_id = a.id where creator_id = 8 and ra.retail_id is null;

update retails_admins set retail_id = 16 where id = 11;

select * from admin_payments_log where admin_id in (select id from admins where creator_id = 8);

select * from users u where phone = 380888888888;

select * from admins;

select * from retails_admins ra;


select * from retails;
insert into retails(name) values('MTI_LOYALITY');

select distinct m.task_id, m.message_text from n_task_messages m join n_tasks t on t.id = m.task_id where t.retail_company_id = 2 order by m.task_id;

select * from n_tasks where retail_company_id = 2;

select * from n_tasks where id = 272;




select * from users u left join bar_codes b on b.user_id = u.id where phone = 380555555551;

select * from admin_payments_log l left join partner_transactions pt on pt.id = l.pt_id where l.phone = '380509739766';

select * from retails_admins where admin_id = 41;

select t.trans_date, al.phone, p.name  as partner, t.phone as landline, l.task_id, t.id as trans_id, t.amount as trans_amount, al.amount as task_amount, 
r.name as retail_name 
from admin_payments_log al left join partner_transactions t on t.id = al.pt_id 
left join n_task_messages_log l on ''||l.codec_id = al.codec_message_id 
left join partners p on p.id = t.partner_id 
left join n_tasks nt on nt.id =l.task_id 
left join retails r on r.id = nt.retail_company_id where al.codec_message_id <> '' and al.pt_id is not null 
and al.status = 1 and  nt.retail_company_id in  
(select retail_id from retails_admins where admin_id = 41) and t.trans_date > '2019-10-01' and t.trans_date < '2019-11-01' order by t.id desc;

select * from users u where phone = 380555555551;

select * from admin_payments_log where phone = 380509739766;

select t.trans_date, al.phone, p.name  as partner, t.phone as landline, l.task_id, t.id as trans_id, t.amount as trans_amount, al.amount as task_amount, 
case when r.name is null then r2."name" else r.name end as retail_name from admin_payments_log al 
left join partner_transactions t on t.id = al.pt_id  left join n_task_messages_log l on ''||l.codec_id = al.codec_message_id left 
join partners p on p.id = t.partner_id left join n_tasks nt on nt.id =l.task_id left join retails r on r.id = 
nt.retail_company_id 
left join retails_admins ra on ra.admin_id = al.admin_id
left join retails r2 on r2.id = ra.retail_id 
where al.pt_id is not null and al.status = 1 and  (nt.retail_company_id in  
(select retail_id from retails_admins where admin_id = 41) or ra.retail_id in (select retail_id from retails_admins where admin_id = 41)) and t.trans_date > '2019-10-01' and t.trans_date < '2019-11-01' order by t.id desc


select tm.task_id, l.id as message_id, l.codec_id, l.from_user_id, l.to_user_id, l.message_date, l.message_text, l.image, l.button_caption, l.button_url, 
s.answer, s.answer2, s.status as user_task_status, t.task_start_date, t.task_finish_date, t.task_type, 
t.cost, t.cost_type, t.auto_cost,  t.status as task_status, q.task_message as question, u.gcm_id, us.photo, t.expected, t.repeat 
from n_task_messages tm 
left join n_task_messages_log l on l.task_id = tm.task_id and l.tp = tm.tp 
left join n_task_messages_status s on s.codec_id = l.codec_id 
left join n_tasks t on t.id = tm.task_id 
left join n_task_questions q on q.task_id = tm.task_id 
left join messenger_user u on u.user_id = tm.to_user_id  
left join users us on us.phone = l.to_user_id 
where tm.to_user_id = 380977081583 and (tm.message_date > '2019-10-12' and tm.message_date < '2019-11-12') 
and ((l.to_user_id <> 380977081583 and s.id is not null and tm.tp <> '') or l.to_user_id = 380977081583) 
and t.status <> 5 and tm.task_id = 272 order by tm.task_id, l.id limit 5000;

select * from n_task_messages ntm where to_user_id = 380977081583 and task_id = 272;

update n_task_messages set message_date = '2019-11-10' where id = 9779;

update n_task_messages set message_date = '2019-11-10' where id in (
select  to_number(ntml.message_id, '999999') from n_task_messages_log ntml 
left join n_task_messages_status s on s.codec_id = ntml.codec_id where s.id is null
) and message_date < '2019-11-01' and task_id = 272;

select * from admins a where login = 'epson_root';

select * from users u where id = 1628;

select * from n_task_messages ntm where id in (
select  to_number(ntml.message_id, '999999') from n_task_messages_log ntml 
left join n_task_messages_status s on s.codec_id = ntml.codec_id where s.id is null
) and message_date < '2019-11-01' and task_id = 272;

select to_number(ntml.message_id, '999999') from n_task_messages_log ntml left join n_task_messages_status s on s.codec_id = ntml.codec_id where s.id is null;

select * from n_task_messages_log ntml left join n_task_messages_status s on ntml.codec_id = s.codec_id where to_user_id = 380975654914;
