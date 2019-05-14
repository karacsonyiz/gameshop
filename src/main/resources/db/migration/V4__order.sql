CREATE TABLE orders (
id BIGINT AUTO_INCREMENT,
user_id INT,
date DATETIME NOT NULL,
status varchar(50) NOT NULL,
CONSTRAINT pk_order PRIMARY KEY(id),
FOREIGN KEY(user_id) REFERENCES users(id)
);