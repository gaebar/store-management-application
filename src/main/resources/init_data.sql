# -- Drop tables if they exist
# DROP TABLE IF EXISTS store_management.store;
#
# -- Create store table
# CREATE TABLE store (
#        id BIGINT PRIMARY KEY AUTO_INCREMENT,
#        name VARCHAR(255) NOT NULL,
#        location VARCHAR(255),
#        type VARCHAR(50),
#        opening_date DATE
# );
#
# -- Drop tables if they exist
# DROP TABLE IF EXISTS store_management.role;
#
# -- Create role table
# CREATE TABLE role (
#       id BIGINT PRIMARY KEY AUTO_INCREMENT,
#       name VARCHAR(50) NOT NULL
# );
#
# -- Drop tables if they exist
# DROP TABLE IF EXISTS store_management.user;
#
# -- Create user table
# CREATE TABLE user (
#       id BIGINT PRIMARY KEY AUTO_INCREMENT,
#       username VARCHAR(50) UNIQUE NOT NULL,
#       password VARCHAR(255) NOT NULL,
#       email VARCHAR(255) UNIQUE,
#       role_id BIGINT,
#       FOREIGN KEY (role_id) REFERENCES role(id)
# );
#
#
# -- Drop tables if they exist
# DROP TABLE IF EXISTS store_management.item;
#
# -- Create item table
# CREATE TABLE item (
#       id BIGINT PRIMARY KEY AUTO_INCREMENT,
#       store_id BIGINT,
#       name VARCHAR(255) NOT NULL,
#       description TEXT,
#       category VARCHAR(50),
#       price DOUBLE,
#       initial_quantity INT,
#       FOREIGN KEY (store_id) REFERENCES store(id)
# );
#
# -- Drop tables if they exist
# DROP TABLE IF EXISTS store_management.purchaseOrder;
#
# -- Create purchase order table
# CREATE TABLE purchaseOrder (
#    id BIGINT PRIMARY KEY AUTO_INCREMENT,
#    user_id BIGINT,
#    store_id BIGINT,
#    total_price DOUBLE,
#    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
#    FOREIGN KEY (user_id) REFERENCES user(id),
#    FOREIGN KEY (store_id) REFERENCES store(id)
# );
