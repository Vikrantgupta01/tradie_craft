CREATE TABLE customer
(
  id   BIGINT      NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE contractor
(
  id   BIGINT      NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE project (
  id   BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  description VARCHAR(500) NOT NULL,
  expected_time INT NOT NULL,
  customer_id BIGINT NOT NULL ,
  bid_expiry_date TIMESTAMP NOT NULL ,
  winner_bid_id BIGINT ,
  status ENUM('NEW', 'CONFIRM', 'PENDING') default 'NEW',
  PRIMARY KEY (id),
foreign key(customer_id)references customer(id)
);


CREATE TABLE project_bid (
  id   BIGINT NOT NULL AUTO_INCREMENT,
  bid_amount decimal NOT NULL,
  bid_type   ENUM('FIXED', 'HOURLY'),
  project_id bigint  NOT NULL,
  contractor_id bigint  NOT NULL,
  PRIMARY KEY (id),
foreign key(contractor_id)references contractor(id),
foreign key(project_id)references project(id),
unique key(project_id,contractor_id)
);


INSERT INTO customer(name) VALUES ('Radisson hotel');

INSERT INTO contractor(name) VALUES ('Super cleaner service');
INSERT INTO contractor(name) VALUES ('Sydney cleaners');
INSERT INTO contractor(name) VALUES ('Awesome cleaners');


INSERT INTO project(title, description,expected_time ,customer_id, bid_expiry_date)
VALUES ('Carpet cleaning for 24 rooms', 'Need service to clean 24 rooms carpet', 4,1,
PARSEDATETIME('14 Jul 2021, 19:00:00 PM', 'dd MMM yyyy, hh:mm:ss a', 'en'));

INSERT INTO project(title, description,expected_time ,customer_id, bid_expiry_date)
VALUES ('Garden cleaning', 'lawn mower service ', 8,1,
PARSEDATETIME('14 Jul 2021, 19:00:00 PM', 'dd MMM yyyy, hh:mm:ss a', 'en'));


INSERT INTO project(title, description,expected_time ,customer_id, bid_expiry_date)
VALUES ('Lease cleaning', 'Lease cleaning service ', 8,1,
PARSEDATETIME('14 Jul 2021, 19:00:00 PM', 'dd MMM yyyy, hh:mm:ss a', 'en'));

INSERT INTO project(title, description,expected_time ,customer_id, bid_expiry_date)
VALUES ('BMW servicing', 'BMW servicing ', 8,1,
PARSEDATETIME('14 Jul 2021, 19:00:00 PM', 'dd MMM yyyy, hh:mm:ss a', 'en'));


INSERT INTO project_bid(bid_amount, project_id,bid_type, contractor_id)
VALUES (99.00, 1, 'FIXED', 1);
INSERT INTO project_bid(bid_amount, project_id,bid_type, contractor_id)
VALUES (98.75, 1, 'FIXED', 2);
INSERT INTO project_bid(bid_amount, project_id,bid_type, contractor_id)
VALUES (24.50, 1, 'HOURLY', 3);


INSERT INTO project_bid(bid_amount, project_id,bid_type, contractor_id)
VALUES (99.00, 3, 'FIXED', 1);
INSERT INTO project_bid(bid_amount, project_id,bid_type, contractor_id)
VALUES (98.75, 3, 'FIXED', 2);
INSERT INTO project_bid(bid_amount, project_id,bid_type, contractor_id)
VALUES (100.50, 3, 'FIXED', 3);


INSERT INTO project_bid(bid_amount, project_id,bid_type, contractor_id)
VALUES (1.00, 4, 'FIXED', 1);