-- Insert initial data for Category
INSERT INTO categories (name) VALUES ('Category 1');
INSERT INTO categories (name) VALUES ('Category 2');
INSERT INTO categories (name) VALUES ('Category 3');
-- ... insert more category records as needed, up to 20

-- Insert initial data for Product
INSERT INTO products (id,name, description, category_id, price) VALUES ('6221155056948','lipton', 'Description 1', 1,10.99);
INSERT INTO products (id,name, description, category_id, price) VALUES ('72527273071','Product 2', 'Description 2', 2,19.99);
INSERT INTO products (id,name, description, category_id, price) VALUES ('72527273072','Product 3', 'Description 3', 3,9.99);
-- ... insert more product records as needed, up to 20
