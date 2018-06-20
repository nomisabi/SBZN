set foreign_key_checks = 0;

insert into authority (name) values ('ROLE_ADMIN');
insert into authority (name) values ('ROLE_USER');


-- password is 'admin' (bcrypt encoded) 
insert into user (username, password, email) values ('admin', '$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK', 'admin@admin.com');
-- password is 'user' (bcrypt encoded)
insert into user (username, password, email) values ('user', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq', 'user@user.com');


insert into user_authority (user_id, authority_id) values (1, 1); -- admin has ROLE_ADMIN
insert into user_authority (user_id, authority_id) values (2, 2); -- user has ROLE_USER

