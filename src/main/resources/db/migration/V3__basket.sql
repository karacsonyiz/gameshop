CREATE TABLE basket(
id BIGINT auto_increment,
user_id INT,
product_id VARCHAR(100),
CONSTRAINT pk_basket PRIMARY KEY(id),
FOREIGN KEY (product_id) REFERENCES products(product_id),
FOREIGN KEY (user_id) REFERENCES users(id)
);