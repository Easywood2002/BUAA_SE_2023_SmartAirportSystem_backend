drop database if exists smartairport;
create database smartairport;
use smartairport;

#drop table if exists ;

create table touristtoken(
    id int,
    token varchar(255)
);

create table merchanttoken(
    id int,
    token varchar(255)
);

create table stafftoken(
    id int,
    token varchar(255)
);

create table airlinecompanytoken(
    id int,
    token varchar(255)
);

create table staff(
	staffid int primary key auto_increment,
	realname varchar(255),
	positionpost varchar(255),
	email varchar(255),
	passwords varchar(255),
	salt varchar(255)
);

create table repairrecord(
	recordid int primary key auto_increment,
	deviceinfo varchar(255),
	location varchar(255),
	approved varchar(255),
	devicename varchar(255),
	devicepicture varchar(255)
);

create table luggage(
	luggageid int primary key auto_increment,
	touristid int,
	flightid int,
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
	available varchar(255)
);

create table tourist(
	touristid int primary key auto_increment,
	email varchar(255),
	passwords varchar(255),
	salt varchar(255),
    vip varchar(255)
);

create table person(
    personid int primary key auto_increment,
    touristid int,
    realname varchar(255),
    idnumber varchar(255),
    email varchar(255)
);

create table purchaserecord(
	orderid int primary key auto_increment,
    personid int,
	ticketid int,
	purchasetime varchar(255),
	seatinfo varchar(255)
);

create table ticket(
	ticketid int primary key auto_increment,
	flightid int,
	tickettype varchar(255),
	price double,
	amount int
);

create table airlinecompany(
	companyid int primary key auto_increment,
	email varchar(255),
	name varchar(255),
	passwords varchar(255),
	salt varchar(255)
);

create table flight(
	flightid int primary key auto_increment,
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
    salt varchar(255),
	shopname varchar(255),
	email varchar(255)
);

create table commoditylist(
	commodityid int primary key auto_increment,
	name varchar(255),
	merchantid int,
	counts int,
	price double
);

drop trigger if exists delflight;
delimiter //
create trigger delflight before delete on flight for each row
    begin
        delete from ticket where flightid = old.flightid;
    end //
delimiter ;

drop trigger if exists delticket;
delimiter //
create trigger delticket before delete on ticket for each row
begin
    delete from purchaserecord where ticketid = old.ticketid;
end //
delimiter ;

drop trigger if exists delperson;
delimiter //
create trigger delperson before delete on person for each row
begin
    delete from purchaserecord where personid = old.personid;
end //
delimiter ;