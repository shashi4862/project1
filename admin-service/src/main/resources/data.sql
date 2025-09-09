-- Initial data for the 'admin' table
INSERT INTO admin (username, password, email) VALUES
    ('admin', '$2a$10$T87sQz8E/y5FqBfA/vQ5f.B.qP5eT/zV.jF/E.i.R7.y', 'admin@pizzeria.com');

-- Initial data for the 'menu_item' table
INSERT INTO menu_item (name, description, price, category, image_url) VALUES
    ('Margherita Pizza', 'Classic pizza with fresh mozzarella, basil, and tomatoes.', 12.50, 'Pizzas', 'https://example.com/images/margherita.jpg'),
    ('Pepsi', 'Refreshing soda.', 2.00, 'Drinks', 'https://example.com/images/pepsi.jpg'),
    ('Garlic Bread', 'Oven-baked bread with garlic and parsley.', 4.50, 'Appetizers', 'https://example.com/images/garlic_bread.jpg');

