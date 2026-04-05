
INSERT INTO categories (id, name) VALUES
(1, 'Electronics'),
(2, 'Books'),
(3, 'Clothing'),
(4, 'Home Appliances'),
(5, 'Sports & Outdoors'),
(6, 'Health & Beauty'),
(7, 'Toys & Games'),
(8, 'Automotive'),
(9, 'Grocery');


INSERT INTO products(id, name, barcode, sku, description, price, category_id) VALUES
(1, 'Smartphone', '2315367254623', 'SP-001', 'Latest model smartphone with advanced features.', 699.99, 1),
(2, 'Laptop', '2315453525463', 'LT-001', 'High-performance laptop for gaming and work.', 1299.99, 1),
(3, 'Novel Book', '3341433114543', 'BK-001', 'Bestselling novel of the year.', 19.99, 2),
(4, 'T-Shirt', '7234556363421', 'TS-001', 'Comfortable cotton t-shirt.', 15.99, 3),
(5, 'Microwave Oven', '6363463222411', 'M0-001', 'Compact microwave oven for quick meals.', 89.99, 4),
(6, 'Running Shoes', '3523525222214', 'RS-001', 'Lightweight running shoes for athletes.', 59.99, 5),
(7, 'Face Cream', '5445345323333', 'FC-001', 'Moisturizing face cream for daily use.', 29.99, 6),
(8, 'Action Figure', '2352124141455', 'AF-001', 'Collectible action figure from popular series.', 24.99, 7),
(9, 'Car Battery', '2342341357433', 'CB-001', 'High-performance car battery.', 119.99, 8),
(10, 'Organic appless(1kg)', '1474223344444', 'GA-001', 'Fresh organic apples from local farms.', 3.99, 9);
