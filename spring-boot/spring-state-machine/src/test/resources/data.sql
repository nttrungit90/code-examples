DROP TABLE IF EXISTS runsheet;

CREATE TABLE runsheet(
  id int not null AUTO_INCREMENT,
  status varchar(100) not null,
  courier_id int not null,
  PRIMARY KEY ( id )
);

INSERT INTO runsheet (id, status, courier_id) VALUES (1,'DISPATCHED',1);
INSERT INTO runsheet (id, status, courier_id) VALUES (2,'CHECKED_OUT',2);
INSERT INTO runsheet (id, status, courier_id) VALUES (3,'CHECKED_IN',3);
INSERT INTO runsheet (id, status, courier_id) VALUES (4,'DONE',4);
INSERT INTO runsheet (id, status, courier_id) VALUES (5,'CANCELED',5);
INSERT INTO runsheet (id, status, courier_id) VALUES (6,'DISPATCHED',6);