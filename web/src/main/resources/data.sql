------USERS------
insert into users (id, email, active, first_name, last_name, authentication_string)
values (1, 'admin@bluementors.com', 'true', 'Dur', 'Panerus', '$2a$10$J5sNPjCqx2767Xql5njoSOG3I0E/4eXwSZ/LYEr0WjwULqRqb30Bq');



-----ADMINS------
insert into admins (id, user_id) values(1,1);


----SKILS--------
insert into skills(id, name, description, active) values(1, 'c++','A mush have skill for any oop enthusiast', true);
insert into skills(id, name, description, active) values(2, 'java.croe','Industry leading back end programing core', false);
insert into skills(id, name, description, active) values(3, 'spring mvc','Spring, String Core, Web Services, Jsp1.4, JSTL2.0', true);