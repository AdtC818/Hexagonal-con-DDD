-- Schemas por bounded context
CREATE DATABASE IF NOT EXISTS orders_db;
CREATE DATABASE IF NOT EXISTS payments_db;
CREATE DATABASE IF NOT EXISTS shipping_db;
CREATE DATABASE IF NOT EXISTS inventory_db;

-- Usuario para orders
CREATE USER IF NOT EXISTS 'orders_user'@'%' IDENTIFIED BY 'uptc2026';
GRANT ALL PRIVILEGES ON orders_db.* TO 'orders_user'@'%';

-- Usuario para payments
CREATE USER IF NOT EXISTS 'payments_user'@'%' IDENTIFIED BY 'uptc2026';
GRANT ALL PRIVILEGES ON payments_db.* TO 'payments_user'@'%';

-- Usuario para shipping
CREATE USER IF NOT EXISTS 'shipping_user'@'%' IDENTIFIED BY 'uptc2026';
GRANT ALL PRIVILEGES ON shipping_db.* TO 'shipping_user'@'%';

-- Usuario para inventory
CREATE USER IF NOT EXISTS 'inventory_user'@'%' IDENTIFIED BY 'uptc2026';
GRANT ALL PRIVILEGES ON inventory_db.* TO 'inventory_user'@'%';

FLUSH PRIVILEGES;