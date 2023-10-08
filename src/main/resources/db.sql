create database employee_manager_jdbc;

use employee_manager_jdbc;

create table user
(
    id       int primary key auto_increment,
    username varchar(50) not null unique,
    password varchar(50) not null
);

create table role
(
    id   int primary key auto_increment,
    name varchar(50) not null
);

create table user_role
(
    id      int primary key auto_increment,
    user_id int not null,
    role_id int not null,
    foreign key (user_id) references user (id),
    foreign key (role_id) references role (id)
);

create table department
(
    id   int primary key auto_increment,
    name varchar(50) not null
);

create table employee
(
    id            int primary key auto_increment,
    name          varchar(50) not null,
    birth_date    date,
    gender        boolean,
    salary        double,
    phone_number  varchar(15) not null unique,
    department_id int,
    foreign key (department_id) references department (id)
);
insert into user (username, password) value ('quangnn', 'quangnn');
insert into user (username, password) value ('user', '$2a$12$XRtUlNIzLXTItQxTPUbe.exjHDrFpD54lLJ2zngIQ8bQcnGY9yWj.');
insert into user (username, password) value ('admin', '$2a$12$JocA3fX0gwlNA6c6QsDP/ej.kA59uhw6pvdOVKjN0qPMUYx6CSX4C');

insert into role(id, name) value (1, 'user');
insert into role(id, name) value (2, 'admin');

insert into user_role(user_id, role_id) value (1, 1);
insert into user_role(user_id, role_id) value (1, 2);
insert into user_role(user_id, role_id) value (2, 1);
insert into user_role(user_id, role_id) value (3, 2);

insert into department (name) value ('Quản lý');
insert into department (name) value ('kế toán');
insert into department (name) value ('Sale-Marketing');
insert into department (name) value ('Sản xuất');
insert into department (name) value ('Giám đốc');


insert into employee(name, birth_date, gender, salary, phone_number, department_id) value ('Lê văn Sỹ', '2003-01-25', true, 15000000, '0942896469', 1);
insert into employee(name, birth_date, gender, salary, phone_number, department_id) value ('Nguyễn Phương Hằng', '1971-01-26', false, 500000000, '099999999', 5);
insert into employee(name, birth_date, gender, salary, phone_number, department_id) value ('Lê Thị Thanh Hoài', '2003-07-25', false, 8000000, '0942896470', 3);
insert into employee(name, birth_date, gender, salary, phone_number, department_id) value ('Nguyễn Nhật Tân', '2003-04-30', true, 7000000, '0942896471', 4);
insert into employee(name, birth_date, gender, salary, phone_number, department_id) value ('Từ Anh Đài', '2003-01-01', true, 9000000, '0942896472', 2);
insert into employee(name, birth_date, gender, salary, phone_number, department_id) value ('Trần Phi Long', '2003-01-01', true, 10000000, '0942896473', 1);
insert into employee(name, birth_date, gender, salary, phone_number, department_id) value ('Nguyễn Tình', '1987-01-25', false, 15000000, '0942896474', 3);

select id, username, password from user where  username = ? and password = ?;

select r.name as role
from user u inner join user_role ur on u.id = ur.user_id
            inner join role r on ur.role_id = r.id
where u.username = ?;


