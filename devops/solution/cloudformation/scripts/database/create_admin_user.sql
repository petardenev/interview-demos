create user admin1;
GRANT postgres to admin1;
GRANT rds_iam TO admin1;
alter user admin1 with CREATEDB CREATEROLE;
CREATE DATABASE admin1;
alter database admin1 OWNER to admin1;
