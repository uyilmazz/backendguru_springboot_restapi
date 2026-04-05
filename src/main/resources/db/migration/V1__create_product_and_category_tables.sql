-- CATEGORY için sequence
CREATE SEQUENCE categories_id_seq START WITH 1 INCREMENT BY 1;
-- PRODUCT için sequence
CREATE SEQUENCE products_id_seq START WITH 1 INCREMENT BY 1;

-- Create the product table
CREATE TABLE IF NOT EXISTS products(
	id BIGINT PRIMARY KEY DEFAULT nextval('products_id_seq'),
	name VARCHAR,
	barcode VARCHAR(255),
	sku VARCHAR(255),
	description TEXT,
	price DECIMAL(10, 2) NOT NULL,
	category_id BIGINT NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Create the category table
CREATE TABLE IF NOT EXISTS categories(
	id BIGINT PRIMARY KEY DEFAULT nextval('categories_id_seq'),
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create a foreign key constraitn between products and categories
ALTER TABLE products
ADD CONSTRAINT fk_category
FOREIGN KEY (category_id)
REFERENCES categories(id)