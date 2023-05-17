drop database if exists smartairport;
create database smartairport;
use smartairport;

#drop table if exists ;

create table staff(
	staffid int primary key auto_increment,
	realname varchar(255),
	positionpost varchar(255),
	nickname varchar(255),
	passwords varchar(255)
);

create table repairrecord(
	recordid int primary key auto_increment,
	deviceinfo varchar(255),
	location varchar(255),
	approved bool,
	devicename varchar(255),
	devicepicture varchar(255),
	deviceid varchar(255)
);

create table luggage(
	luggageid varchar(255) primary key,
	touristid int,
	flightid varchar(255),
	state varchar(255),
	location varchar(255)
);

create table parkingorder(
	orderid int primary key auto_increment,
	touristid int,
	parktime double,
	price double,
	location varchar(255)
);

create table parkingspace(
	parkingpostid int primary key auto_increment,
	location varchar(255),
	price double,
	available bool
);

create table tourist(
	touristid int primary key auto_increment,
	nickname varchar(255),
	vip bool,
	realname varchar(255),
	passwords varchar(255)
);

create table purchaserecord(
	orderid int primary key auto_increment,
	touristid int,
	ticketid varchar(255),
	purchasetime varchar(255),
	seatinfo varchar(255)
);

create table ticket(
	ticketid varchar(255) primary key,
	flightid varchar(255),
	tickettype varchar(255),
	price double,
	amount int
);

create table airlinecompany(
	companyid int primary key auto_increment,
	name varchar(255),
	passwords varchar(255)
);

create table flight(
	flightid varchar(255) primary key,
	name varchar(255),
	companyid int,
	takeofflocation varchar(255),
	landinglocation varchar(255),
	departuretime varchar(255),
	landingtime varchar(255),
	departuregate varchar(255),
	terminal int
);

create table merchant(
	merchantid int primary key auto_increment,
	realname varchar(255),
	passwords varchar(255),
	nickname varchar(255)
);

create table commoditylist(
	commodityid int primary key auto_increment,
	name varchar(255),
	merchantid int,
	counts int,
	price double,
	destination varchar(255)
);