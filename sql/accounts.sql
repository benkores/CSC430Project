CREATE TABLE ACCOUNTS (
	id integer PRIMARY KEY,
	username varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	first_name varchar(255),
	last_name varchar(255),
	payment_type varchar(255),
	card_name varchar(255),
	card_number varchar(255),
	card_expiration varchar(255),
	card_security varchar(255),
	card_billing_address varchar(255),
	secondary_card_name varchar(255),
	secondary_card_number varchar(255),
	secondary_card_expiration varchar(255),
	secondary_card_security varchar(255),
	secondary_card_billing_address varchar(255)
);