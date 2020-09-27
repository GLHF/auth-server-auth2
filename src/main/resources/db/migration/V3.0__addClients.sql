insert into ps_client (client_id, secret, scopes, authorized_grand_types, authorities, auto_approve_scopes)
values ('payment-system', '$2a$10$8RJgL8jn5PEfUOF2zPJ5JOg3KUqutn.mEz9ovji3DJcSstaKyH816', 'openid', 'authorization_code,refresh_token,password,code,implicit', 'ROLE_USER,ROLE_ADMIN', '.*');
-- secret - payment-secret