CREATE DATABASE IF NOT EXISTS InsuranceDB;
USE InsuranceDB;

CREATE TABLE Policy (
    policyId INT PRIMARY KEY,
    policyName VARCHAR(100) NOT NULL,
    policyType VARCHAR(50) NOT NULL,
    coverageAmount DOUBLE NOT NULL,
    premium DOUBLE NOT NULL
);

CREATE TABLE User (
    userId INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(30) NOT NULL
);

CREATE TABLE Client (
    clientId INT PRIMARY KEY,
    clientName VARCHAR(100) NOT NULL,
    contactInfo VARCHAR(150),
    policyId INT,
    FOREIGN KEY (policyId) REFERENCES Policy(policyId)
);

CREATE TABLE Claim (
    claimId INT PRIMARY KEY,
    claimNumber VARCHAR(50) NOT NULL,
    dateFiled DATE NOT NULL,
    claimAmount DOUBLE NOT NULL,
    status VARCHAR(30) NOT NULL,
    policyId INT,
    clientId INT,
    FOREIGN KEY (policyId) REFERENCES Policy(policyId),
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

CREATE TABLE Payment (
    paymentId INT PRIMARY KEY,
    paymentDate DATE NOT NULL,
    paymentAmount DOUBLE NOT NULL,
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

INSERT INTO Policy VALUES 
(1, 'Health Protect', 'Health', 300000, 9000),
(2, 'Life Shield', 'Life', 500000, 12000);

INSERT INTO User VALUES 
(1001, 'admin1', 'admin@123', 'Admin'),
(1002, 'agent1', 'agent@123', 'Agent');

INSERT INTO Client VALUES 
(201, 'Janani', 'janani@gmail.com', 1),
(202, 'John', 'john@gmail.com', 2);

INSERT INTO Claim VALUES 
(301, 'CLM001', '2024-05-01', 25000, 'Pending', 1, 201),
(302, 'CLM002', '2024-06-15', 100000, 'Approved', 2, 202);

INSERT INTO Payment VALUES 
(401, '2025-06-27', 9000, 201),
(402, '2025-06-27', 12000, 202);

select*from policy;
select*from claim;
select*from payment;
select*from client;
select*from user;

desc policy;
