

------USERS------
insert into users (id, email, active, first_name, last_name, authentication_string)
values (1, 'admin@bluementors.com', 'true', 'Dur', 'Panerus', '$2a$10$J5sNPjCqx2767Xql5njoSOG3I0E/4eXwSZ/LYEr0WjwULqRqb30Bq');

insert into users (id, email, active, first_name, last_name, authentication_string)
values (2, 'mem@bluementors.com', 'true', 'Mem', 'Geo', '$2a$10$J5sNPjCqx2767Xql5njoSOG3I0E/4eXwSZ/LYEr0WjwULqRqb30Bq');

insert into users (id, email, active, first_name, last_name, authentication_string)
values (3, 'pep@bluementors.com', 'true', 'Pep', 'John', '$2a$10$J5sNPjCqx2767Xql5njoSOG3I0E/4eXwSZ/LYEr0WjwULqRqb30Bq');

insert into users (id, email, active, first_name, last_name, authentication_string)
values (4, 'gg@bluementors.com', 'true', 'Geo', 'Giovani', '$2a$10$J5sNPjCqx2767Xql5njoSOG3I0E/4eXwSZ/LYEr0WjwULqRqb30Bq');



-----ADMINS------
insert into admins (id, user_id) values(1,1);
update users set admin_id = 1 where id =1;

----MENTORS -----
insert into mentors (id, user_id, years_of_experience, active) values (1, 2, 1, true);
update users set mentor_id=1 where id=2;
update users set mentor_id=1 where id=1;

----SKILS--------
insert into skills(id, name, description, active) values(1, 'c++','A mush have skill for any oop enthusiast. Basic abilities will enable your journey into the odyssey of programing.', true);
insert into skills(id, name, description, active) values(2, 'java.core','Industry leading back end programing core.', false);
insert into skills(id, name, description, active) values(3, 'spring mvc','Spring, String Core, Web Services, Jsp1.4, JSTL2.0', true);
insert into skills(id, name, description, active) values(4, 'cloud','Clouding is the next level of fogging. Learn now the skills of the future.', true);
insert into skills(id, name, description, active) values(5, 'nodeJS','Join the histerry. Learn to to the same thing in multiple ways.', true);