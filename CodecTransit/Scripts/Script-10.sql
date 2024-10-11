select * from sf_bug where contact_id = '0034H000020lxaR';

select * from user_session where email like 'bumer%';
select * from user_session where email like '%';
select * from users where phone = 380939832069;

select * from partner_transactions_verification where user_id = 26373 order by id desc;

select * from partner_transactions_log l left join partner_transactions_status s on s.codec_id = l.codec_id where user_id = 26373 and trans_date > '2019-10-01';

select * from sf_transactions where partner_transaction_id = 916743;

select * 

