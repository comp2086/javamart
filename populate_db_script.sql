use javamart;
set SQL_SAFE_UPDATES = 0;

-- Employees
insert into employee(firstName, lastName, position, department, address, phone, sin, commissionRate, sales)
values
	('Alex', 'Andriishyn', 'Software Developer', 'IT', '140 Penetang St.', '123456', 'abcdefghg', 0.2, 0),
	('Anthony', 'Scinocco', 'Software Developer', 'IT', 'Some street', '123456', 'abcdefghg', 0.2, 0),
	('Dan', 'Masci', 'Software Developer', 'IT', 'Some street-2', '123456', 'abcdefghg', 0.2, 0),
	('David', 'Yu', 'Software Developer', 'IT', 'Some street-3', '123456', 'abcdefghg', 0.2, 0),
	('Michael', 'Joyce', 'Software Developer', 'IT', 'Some street-4', '123456', 'abcdefghg', 0.2, 0);

-- Manufacturers
insert into manufacturer(name) 
VALUES
	("Zehrs"),
	("Wallmart"),
	("Loblaws");

-- Products
insert into product(manId, name, description, serialNumber, cost, price, availability)
values
	(1, "Banana", "desc", "abcde", 11.2, 15.0, 1),
	(1, "Orange", "desc", "abcde", 11.2, 15.0, 1),
	(1, "Apple", "desc", "abcde", 11.2, 15.0, 1),
	(2, "Fish", "desc", "abcde", 11.2, 15.0, 1),
	(3, "Rice", "desc", "abcde", 11.2, 15.0, 1);

-- Invoices
insert into invoice(cost)
values
	(100);

-- Invoice Junction
insert into invoiceJunction(employeeId, productId, invoiceId)
values
	(3, 7, 1),
	(2, 6, 1);

select * from employee;
select * from manufacturer;
select * from product;
select * from invoice;
select * from invoiceJunction;