CREATE DATABASE javamart;
use javamart;
set SQL_SAFE_UPDATES = 0;

-- Employees
insert into employees(firstName, lastName, position, department, address, phone, sin, commissionRate)
values
	('Alex', 'Andriishyn', 'Software Developer', 'IT', '140 Penetang St.', '123456789', 'abcdefghg', 0.2),
	('Anthony', 'Scinocco', 'Software Developer', 'IT', 'Some street', '123456789', 'abcdefghg', 0.2),
	('Dan', 'Masci', 'Software Developer', 'IT', 'Some street-2', '123456789', 'abcdefghg', 0.2),
	('David', 'Yu', 'Software Developer', 'IT', 'Some street-3', '123456789', 'abcdefghg', 0.2),
	('Michael', 'Joyce', 'Software Developer', 'IT', 'Some street-4', '123456789', 'abcdefghg', 0.2);

-- Manufacturers
insert into manufacturers(name) 
VALUES
	("Zehrs"),
	("Walmart"),
	("Loblaws");

-- Products
insert into products(manId, name, description, serialNumber, cost, price, availability)
values
	(1, "Banana", "desc", "abcde", 11.2, 15.0, 1),
	(1, "Orange", "desc", "abcde", 11.2, 15.0, 1),
	(1, "Apple", "desc", "abcde", 11.2, 15.0, 1),
	(2, "Fish", "desc", "abcde", 11.2, 15.0, 1),
	(3, "Rice", "desc", "abcde", 11.2, 15.0, 1);

-- Invoices
insert into invoices(cost)
values
	(100);

-- Invoice Junction
insert into invoicesJunction(employeeId, productId, invoiceId)
values
	(3, 4, 1),
	(2, 2, 1);

select * from employees;
select * from manufacturers;
select * from products;
select * from invoices;
select * from invoicesJunction;