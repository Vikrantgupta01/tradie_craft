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
  PRIMARY KEY (id),
foreign key(customer_id)references customer(id)
);


CREATE TABLE project_bid (
  id   BIGINT NOT NULL AUTO_INCREMENT,
  bid_amount int NOT NULL,
  bid_type   ENUM('FIXED', 'HOURLY'),
  project_id bigint  NOT NULL,
  contractor_id bigint  NOT NULL,
  PRIMARY KEY (id),
foreign key(contractor_id)references contractor(id),
foreign key(project_id)references project(id)
);


INSERT INTO customer(name) VALUES ('India');
INSERT INTO contractor(name) VALUES ('contractor');

INSERT INTO project(title, description,expected_time ,customer_id, bid_expiry_date)
VALUES ('test -11', 'test -11', 4,1,
PARSEDATETIME('08 Jul 2021, 17:59:58 AM', 'dd MMM yyyy, hh:mm:ss a', 'en'));

INSERT INTO project(title, description, expected_time, customer_id, bid_expiry_date)
VALUES ('test -112323', 'test -133331', 5, 1,
PARSEDATETIME('08 Jul 2021, 17:59:58 AM', 'dd MMM yyyy, hh:mm:ss a', 'en'));

INSERT INTO project_bid(bid_amount, project_id,bid_type, contractor_id)
VALUES (100, 1, 'FIXED', 1);

INSERT INTO project_bid(bid_amount, project_id,bid_type, contractor_id)
VALUES (150, 1, 'FIXED', 1);
INSERT INTO project_bid(bid_amount, project_id,bid_type, contractor_id)
VALUES (26, 1, 'HOURLY', 1);