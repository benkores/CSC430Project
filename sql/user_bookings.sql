CREATE TABLE USER_BOOKINGS (
	account_id int REFERENCES Accounts(ID),
	id int,
	flight_id int,
	flight_seats_id int,
	first_name varchar(255),
	last_name varchar(255),
	person_type varchar(255)
);