CREATE DATABASE javaAssignment;
USE javaAssignment;

CREATE TABLE employee
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	firstName VARCHAR(25),
	lastName VARCHAR(25),
	position VARCHAR(50),
	department VARCHAR(25),
	address VARCHAR(100),
	phone VARCHAR(12),
	sin CHAR(9)
);


CREATE TABLE manufacturer
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE product
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	manId INT NOT NULL,
	name VARCHAR(50),
	description VARCHAR(200),
	serialNumber VARCHAR(15),
	cost DECIMAL(5, 2),
	price DECIMAL(5, 2),
	availability BOOLEAN,
	FOREIGN KEY(manId) REFERENCES manufacturer(id)
);

CREATE TABLE invoice
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cost DECIMAL(10,2),
	created DATE
);

CREATE TABLE invoiceJunction
(
	employeeId INT NOT NULL,
    productId INT NOT NULL,
    invoiceId INT NOT NULL,
	FOREIGN KEY(employeeId) REFERENCES employee(id),
	FOREIGN KEY(productId) REFERENCES product(id),
	FOREIGN KEY(invoiceId) REFERENCES invoice(id)
);