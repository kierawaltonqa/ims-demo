create database if not exists ims;
create table if not exists ims.customers(customerID int unique primary key auto_increment, first_name varchar(40), surname varchar(40));
create table if not exists ims.items(itemID int unique primary key auto_increment, itemName varchar(40), itemPrice varchar(40));
create table if not exists ims.orders(orderID int unique primary key auto_increment, customerID int, totalPrice varchar(10), foreign key(customerID) references ims.customers(customerID));
create table if not exists ims.orderline(orderlineID int unique primary key auto_increment, itemID int, orderID int, quantity int, foreign key(itemID) references ims.items(itemID), foreign key(orderID) references ims.orders(orderID));
