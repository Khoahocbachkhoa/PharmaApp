CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE suppliers (
    SupplierID SERIAL PRIMARY KEY,
    Name VARCHAR(200) NOT NULL,
    Address VARCHAR(300),
    Phone VARCHAR(50)
);

CREATE TABLE customers (
    CustomerID SERIAL PRIMARY KEY,
    Name VARCHAR(200) NOT NULL,
    Phone VARCHAR(50),
    Address VARCHAR(300)
);

CREATE TABLE medicines (
    MedicineID SERIAL PRIMARY KEY,
    Name VARCHAR(200) NOT NULL,
    Category VARCHAR(100),
    Unit VARCHAR(50),
    Price DOUBLE PRECISION NOT NULL,
    Quantity INTEGER DEFAULT 0,
    SupplierID INTEGER REFERENCES suppliers(SupplierID),
    Description TEXT
);

CREATE TABLE invoices (
    InvoiceID SERIAL PRIMARY KEY,
    DateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CustomerID INTEGER REFERENCES customers(CustomerID),
    TotalAmount DOUBLE PRECISION DEFAULT 0.0
);

CREATE TABLE invoice_details (
    InvoiceDetailID SERIAL PRIMARY KEY,
    InvoiceID INTEGER REFERENCES invoices(InvoiceID),
    MedicineID INTEGER REFERENCES medicines(MedicineID),
    Quantity INTEGER NOT NULL,
    UnitPrice DOUBLE PRECISION NOT NULL,
    SubTotal DOUBLE PRECISION NOT NULL
);

CREATE TABLE import_receipts (
    ReceiptID SERIAL PRIMARY KEY,
    SupplierID INTEGER REFERENCES suppliers(SupplierID),
    DateImported TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    TotalCost DOUBLE PRECISION DEFAULT 0.0
);

CREATE TABLE import_details (
    ImportDetailID SERIAL PRIMARY KEY,
    ReceiptID INTEGER REFERENCES import_receipts(ReceiptID),
    MedicineID INTEGER REFERENCES medicines(MedicineID),
    Quantity INTEGER NOT NULL,
    ImportPrice DOUBLE PRECISION NOT NULL
);

insert into accounts(username, password)
values ('admin', '123456');