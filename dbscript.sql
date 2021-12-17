create table user_details (
				user_id int generated always as identity,
				user_password varchar(20),
				user_first_name varchar(20),
				user_last_name varchar(20),
				user_type varchar(20),
				user_removed boolean,
				primary key(user_id)
);

insert into user_details(user_first_name, user_last_name, user_type, user_removed) values('Michelle', 'Harper', 'Manager', false);
insert into user_details(user_first_name, user_last_name, user_type, user_removed) values('John', 'Doe', 'Employee', false);
insert into user_details(user_first_name, user_last_name, user_type, user_removed) values('Jane', 'Doe', 'Customer', false);


create table item_details (
				item_id int generated always as identity,
				item_name varchar(100),
				item_condition varchar(20),
				item_price int,
				item_owned boolean,
				item_removed boolean,
				primary key(item_id)
);

CREATE TABLE offer_details (
				offer_id INT generated always as identity,
				item_id INT,
				user_id INT,
				offer_price INT,
				offer_accepted boolean,
				primary key(offer_id)
				CONSTRAINT fk_item_id foreign key(item_id) REFERENCES item_details(item_id)
				CONSTRAINT fk_user_id foreign key(user_id) REFERENCES user_details(user_id)

CREATE TABLE payment_details (
				payment_id INT generated always as identity,
				item_id INT,
				user_id INT,
				amount_due INT,
				payment_complete boolean,
				payment_date date,
				primary key(payment_id)
				foreign key(item_id) REFERENCES item_details(item_id)
				foreign key(user_id) REFERENCES user_details(user_id)
);