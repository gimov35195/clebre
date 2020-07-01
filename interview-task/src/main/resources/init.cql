CREATE KEYSPACE clebre WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

use clebre;

CREATE TABLE Note (id bigint, content varchar, PRIMARY KEY ( id ));