select id from users where company_id = 4;

select * from bar_codes where partner_id  = 5 and user_id in (select id from users where company_id = 4);

select * from partner_balance where partner_id  = 5 and user_id in (select id from users where company_id = 4);

delete from bar_codes where partner_id = 8 and user_id in (select id from users where company_id = 4);
delete from partner_balance where partner_id  = 8 and user_id in (select id from users where company_id = 4);

select * from users where id = 90;
select * from partner_transactions_certificates where partner_id = 8 and user_id in (select id from users where company_id = 4) and status = 0;

delete from partner_transactions_certificates where id in (62, 63);

select * from discr_certificates where bar_code in ('9101299160695', '9103248948019');

select * from admins where login = 'epicentr_root';

