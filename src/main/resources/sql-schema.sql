create database if not exists ims;
drop table if exists ims.customers
create table if not exists ims.customers(id int unique not null primary key auto_increment, first_name varchar(40), surname varchar(40));
create table if not exists ims.items(itemID int unique not null primary key auto_increment, itemName varchar(40), itemPrice varchar(40));
create table if not exists ims.orders(orderID int unique not null primary key auto_increment, customerID int, orderDate varchar(20) not null, foreign key(customerID) references ims.customers(id));
create table if not exists ims.orderline(orderlineID int unique not null primary key auto_increment, itemID int, orderID int, foreign key(itemID) references ims.items(itemID), foreign key(orderID) references ims.orders(orderID));
