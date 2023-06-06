drop database if exists smartairport;
create database smartairport;
use smartairport;

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
	positionpost int,
	email varchar(255),
	passwords varchar(255),
	salt varchar(255),
	idnumber varchar(255)
);

create table repairrecord(
	recordid int primary key auto_increment,
	deviceinfo varchar(255),
	location varchar(255),
	approved int,
	devicename varchar(255),
	devicepicture varchar(255)
);

create table luggage(
	luggageid int primary key auto_increment,
	personid int,
	ticketid int,
	state varchar(255),
	location varchar(255)
);

create table parkingorder(
	orderid int primary key auto_increment,
	touristid int,
	starttime varchar(255),
	endtime varchar(255),
	parkingspaceid int
);

create table parkingspace(
	parkingspaceid int primary key auto_increment,
	location varchar(255),
	price double
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

create table merchantrequest(
    requestid int primary key auto_increment,
    realname varchar(255),
    passwords varchar(255),
    salt varchar(255),
    shopname varchar(255),
    email varchar(255),
    idnumber varchar(255)
);

create table merchant(
	merchantid int primary key auto_increment,
	realname varchar(255),
	passwords varchar(255),
    salt varchar(255),
	shopname varchar(255),
	email varchar(255),
    idnumber varchar(255)
);

create table commoditylist(
	commodityid int primary key auto_increment,
	name varchar(255),
	merchantid int,
	counts int,
	price double
);

create table commodityorder(
    orderid int primary key auto_increment,
    counts int,
    touristid int,
    commodityid int,
    terminal int,
    departuregate varchar(255),
    arrivetime varchar(255),
    email varchar(255)
);

create table information(
    informationid int primary key auto_increment,
    type varchar(255),
    touristid int,
    staffid int,
    sendtime varchar(255),
    content varchar(255)
);

create table notifyaudience(
    audienceid int primary key auto_increment,
    touristid int,
    ticketid int
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