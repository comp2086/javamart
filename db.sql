CREATE DATABASE javamart;
USE javamart;

DROP TABLE IF EXISTS invoicesJunction;
DROP TABLE IF EXISTS invoices;
DROP TABLE IF EXISTS manufacturers;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS employees;

CREATE TABLE employees
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	firstName VARCHAR(25),
	lastName VARCHAR(25),
	position VARCHAR(50),
	department VARCHAR(25),
	address VARCHAR(100),
	phone VARCHAR(12),
	sin CHAR(9),
    commissionRate DECIMAL(5,5)
);


CREATE TABLE manufacturers
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE products
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	manId INT NOT NULL,
	name VARCHAR(50),
	description VARCHAR(200),
	serialNumber VARCHAR(15),
	cost DECIMAL(5, 2),
	price DECIMAL(5, 2),
	availability BOOLEAN,
	FOREIGN KEY(manId) REFERENCES manufacturers(id)
);

CREATE TABLE invoices
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cost DECIMAL(10,2),
	created TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE invoicesJunction
(
	employeeId INT NOT NULL,
    productId INT NOT NULL,
    invoiceId INT NOT NULL,
	FOREIGN KEY(employeeId) REFERENCES employees(id),
	FOREIGN KEY(productId) REFERENCES products(id),
	FOREIGN KEY(invoiceId) REFERENCES invoices(id)
);

-- Possbile changes if we decide not to hardcode values
/**
CREATE TABLE positions
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    position VARCHAR(50)
);

CREATE TABLE departments
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    department VARCHAR(50)
);
*/
