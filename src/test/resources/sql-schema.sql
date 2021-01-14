create database if not exists ims;
create table if not exists ims.customers(id int primary key auto_increment, first_name varchar(40), surname varchar(40));
create table if not exists ims.items(itemID int primary key auto_increment, itemName varchar(40), itemPrice varchar(40));
create table if not exists ims.orders(orderID int primary key auto_increment, customerID int, orderDate varchar(20) not null, foreign key(customerID) references ims.customers(id));
create table if not exists ims.orderline(orderlineID int primary key auto_increment, itemID int, orderID int, foreign key(itemID) references ims.items(itemID), foreign key(orderID) references ims.orders(orderID));
