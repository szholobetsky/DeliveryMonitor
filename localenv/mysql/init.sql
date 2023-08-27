CREATE DATABASE deliverydb;
CREATE USER 'monitor'@'172.21.0.1' IDENTIFIED BY 'monitor';
GRANT ALL PRIVILEGES ON deliverydb.* TO 'monitor'@'172.21.0.1';

CREATE DATABASE taskdb;
CREATE USER 'operator'@'172.21.0.1' IDENTIFIED BY 'operator';
GRANT ALL PRIVILEGES ON taskdb.* TO 'operator'@'172.21.0.1';
