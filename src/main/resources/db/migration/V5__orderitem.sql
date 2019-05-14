CREATE TABLE orderitem (
id BIGINT AUTO_INCREMENT,
order_id BIGINT,
product_id VARCHAR(100),
product_price BIGINT NOT NULL,
CONSTRAINT pk_orderitem PRIMARY KEY(id),
FOREIGN KEY(order_id) REFERENCES orders(id),
FOREIGN KEY(product_id) REFERENCES products(product_id)
);