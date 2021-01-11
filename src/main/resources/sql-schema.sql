create database if not exists ims;
create table if not exists ims.customers(id int primary key auto_increment, first_name varchar(40), surname varchar(40));
create table if not exists ims.items(item_id int primary key auto_increment, item_name varchar(40),item_price decimal(6,2));
create table if not exists ims.orders(order_id int primary key auto_increment, customer_id int, item_id int, foreign key(customer_id) references customers(id), foreign key(item_id) references items(item_id));