drop database ims_test;
create database if not exists ims_test;
drop table if exists ims_test.customers;
drop table if exists ims_test.items;
create table if not exists ims_test.customers(id int unique not null primary key auto_increment, first_name varchar(40), surname varchar(40));
create table if not exists ims_test.items(itemID int unique primary key auto_increment, itemName varchar(40), itemPrice varchar(40));
create table if not exists ims_test.orders(orderID int unique primary key auto_increment, customerID int, totalPrice varchar(10), foreign key(customerID) references ims_test.customers(id));
create table if not exists ims_test.orderline(orderlineID int unique primary key auto_increment, itemID int, orderID int, quantity int, foreign key(itemID) references ims_test.items(itemID), foreign key(orderID) references ims_test.orders(orderID));
