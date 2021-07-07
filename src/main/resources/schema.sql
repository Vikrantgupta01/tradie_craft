CREATE TABLE customer (
  id   BIGINT      NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE contractor (
  id   BIGINT      NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE project (
  id   BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  description VARCHAR(500) NOT NULL,
  start_date TIMESTAMP NOT NULL ,
  end_date TIMESTAMP NOT NULL ,
  customer_id BIGINT NOT NULL ,
  bid_expiry_date TIMESTAMP NOT NULL ,
  winner_bid_id BIGINT ,
  PRIMARY KEY (id),
  foreign key (customer_id) references customer(id)
);

INSERT INTO customer (name) VALUES ('India');
INSERT INTO project (title, description,start_date, end_date , customer_id, bid_expiry_date)
VALUES ('test -11', 'test -11', now(), now(), 1, now());
-- customer
-- contractor

-- project ( winnerBIdId, customer id)
-- project _ bid ( bid and contacrtor id)
