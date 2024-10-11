select * from users where email like 'asdf%';

select * from users where phone = 380503621874;

select * from bar_codes where user_id = 1909;
select * from partners;

select * from discr_certificates where bar_code = '0201044120130';

select * from partner_transactions_certificates where user_id = 1909;

select * from partner_balance where user_id = 1909;

select * from n_task_messages where task_id = 385 and to_user_id = '380955900901';
update n_task_messages set status = 0 where id = 11925;

select * from users where phone = 380669102009;
select * from partner_transactions_log where user_id = 149 order by id desc;

