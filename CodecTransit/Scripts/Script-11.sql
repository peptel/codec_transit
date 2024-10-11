select b.bar_code, b.user_id, b.id as bar_code_id, u.phone, u.* from bar_codes b join users u on u.id = b.user_id 
where partner_id = 4 and bar_code <> '' order by b.id desc;

select s.verification_phone, u.phone, b.bar_code, u.* from users u 
left join user_session s on s.contact_id = u.contact_id 
left join bar_codes b on b.user_id = u.id 
where b.partner_id = 4 and bar_code <> '' order by id desc;

select s.verification_phone, cast(u.phone as varchar(12)) as PHONE_VALUE, substring(cast(u.phone as varchar(12)),1,5), b.bar_code, u.* from users u 
left join user_session s on s.contact_id = u.contact_id 
left join bar_codes b on b.user_id = u.id 
where b.partner_id = 4 and bar_code <> '' and substring(cast(u.phone as varchar(12)),1,5) = '38077' order by s.verification_phone desc;

select s.verification_phone, cast(u.phone as varchar(12)) as PHONE_VALUE, substring(cast(u.phone as varchar(12)),1,5), b.bar_code, u.* from users u 
left join user_session s on s.contact_id = u.contact_id 
left join bar_codes b on b.user_id = u.id 
where b.partner_id = 4 and bar_code <> '' and substring(cast(u.phone as varchar(12)),1,5) = '38077' 
and u.phone = 380770032654
order by s.verification_phone desc;

select count(*) from users u 
left join user_session s on s.contact_id = u.contact_id 
left join bar_codes b on b.user_id = u.id 
where b.partner_id = 4 and bar_code <> '' and substring(cast(u.phone as varchar(12)),1,5) = '38077';

select count(*) from users u 
left join user_session s on s.contact_id = u.contact_id 
left join bar_codes b on b.user_id = u.id 
where b.partner_id = 4 and bar_code <> '';

select * from fraud_white_list where phone is not null;
select * from fraud_white_list_payment;

verification:      phone:                  bar_code:    user_id:   email:                       balance:
380999027555	380770032654	38077	1000428822436	23933	   98500460.pos.tp@gmail.com	437900

За вказаним номером картки 1000428822436 зареєстровний номер +380770032654.

на номери: 380999027555 зареєстровані:
1000062796094
1000062796100

Телефон 380770032654
Картка 1000428822436

select * from users where phone = 380679707277;
select * from bar_codes where user_id = 26172;
