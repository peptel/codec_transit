SELECT * FROM public.n_task_messages where task_id in (386, 385);

update n_task_messages set message_date = '2019-10-21' where message_date < '2019-10-21' and task_id in (386, 385);

select * from users where email = 'g@b.com';//380444444444 2221;
select * from users where phone = 380770039948;
select * from bar_codes where user_id = 2221;
select * from partners;

select * from discr_certificates where partner_id = 10 and status = 0;

select * from discr_certificates where partner_id = 10 and bar_code = '0201603766380';
select * from discr_certificates where partner_id = 10 and bar_code = '0201760676317';

select * from partner_balance where user_id = 2221;
select * from bar_codes where user_id = 2221;
select * from partner_transactions_certificates where user_id = 2221;

delete from partner_transactions_certificates where id in (216, 217);


select p.id, p.name, p.logo, p.big_logo, p.logo_color, b.bar_code, b.reg_status, p.bar_code_type, pb.balance, 
p.dis_logo, p.dis_color, p.exchange_rate, p.map_logo, 
p.big_disc_logo, p.mobile_operator, b.pin, p.discrete, ptc.bar_code as disc_bar_code, ptc.amount as disc_amount 
from bar_codes b 
join partners p on b.partner_id = p.id 
join partner_balance pb on b.partner_id = pb.partner_id and b.user_id = pb.user_id 
join users u on b.user_id = u.id 
left join partner_transactions_certificates ptc on b.partner_id = ptc.partner_id and b.user_id = ptc.user_id and ptc.status = 0	
where u.phone = 380444444444 order by p.pos;

delete from partner_balance where id in (19927, 19929);

select * from discr_certificates where bar_code in ('0201603766380', '0201760676317');

