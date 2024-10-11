select * from regular_payments where phone = '380679781085';

select * from users where id = 27341;

select * from user_session where phone = 380770036063;

select * from partner_transactions where regular_id = 442;


select * from partner_transactions_log l left join partner_transactions_status s on s.codec_id = l.codec_id where user_id = 27341 order by l.id desc;

select * from sf_transactions where partner_transaction_id = 908897;

select * from sf_transactions where partner_transaction_id = 926151;

select * from sf_bug where contact_id = '0035800001Lj4PwAAJ';