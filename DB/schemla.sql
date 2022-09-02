create database genesis;

-- ------------------------------------------------
-- Table clients
-- ------------------------------------------------
create table clients (
	id int not null primary key auto_increment,
	dpi varchar(150),
	name varchar(150),
	birthday date,
	address varchar(256),
	phone int,
	marital_status_id int not null,
	department_id int not null,
	municipality_id int not null,
	registration_date datetime default current_timestamp,
	state enum('Active', 'Inactive') default 'Active',
	foreign key (marital_status_id) references marital_status (id),
	foreign key (department_id) references department (id),
	foreign key (municipality_id) references municipality (id)
);

select * from clients c ;

-- ------------------------------------------------
-- Table marital_status
-- ------------------------------------------------
create table marital_status (
	id int not null auto_increment,
	name varchar(256),
	primary key (id)
);

-- ------------------------------------------------
-- Table department
-- ------------------------------------------------
create table department (
	id int not null auto_increment,
	name varchar(256),
	primary key (id)
);

-- ------------------------------------------------
-- Table municipality
-- ------------------------------------------------
create table municipality (
	id int not null auto_increment,
	name varchar(256),
	primary key (id)
);

-- ------------------------------------------------
-- Table municipality
-- ------------------------------------------------

create table loans(
	id int not null auto_increment ,
	code varchar(100) not null,
	client_id int not null,
	mont decimal(6, 2) not null,
	number_installments int not null,
	registration_date datetime default current_timestamp,
	primary key (id),
	foreign key (client_id) references clients (id)
);

