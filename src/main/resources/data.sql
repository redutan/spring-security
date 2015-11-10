insert into users (username, password, enabled) values ('admin', '1234', true);
insert into users (username, password, enabled) values ('user', '4567', true);

insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');
insert into authorities (username, authority) values ('user', 'ROLE_USER');

insert into account (username, password, role, nick) values('user', '1234', 'ROLE_USER', '유저');
insert into account (username, password, role, nick) values('admin', '2345', 'ROLE_ADMIN', '어드민');
