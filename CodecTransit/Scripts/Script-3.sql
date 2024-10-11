select d.*, p."name" from discr_certificates d, partners p where p.id = d.partner_id and partner_id = 11 and status = 0;

select d.*, p."name" from discr_certificates d, partners p where p.id = d.partner_id and partner_id = 11 and status = 0 and amount = 500;

select d.*, p."name" from discr_certificates d, partners p where p.id = d.partner_id and amount = 500;

select d.*, p."name" from discr_certificates d, partners p where p.id = d.partner_id and bar_code in ('0200152956068', '0200152958826');


insert into discr_certificates(bar_code, amount, partner_id, status, creation_date) values
('0200152290742', 200, 11, 0, now()),
('0200152290759', 200, 11, 0, now()),
('0200152290766', 200, 11, 0, now()),
('0200152956068', 500, 11, 0, now()),
('0200152958826', 200, 11, 0, now());

select c.*, u.* from partner_transactions_certificates c, users u, user_session s where discr_cert_id in (3583, 3582) and u.id = c.user_id and to_number(s.user_id, '999999') = u.phone;
