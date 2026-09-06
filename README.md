# Expense Tracker Management System

## Overview

The Expense Tracker Management System is a Java-based application developed to manage personal income and expenses efficiently.

The application uses Java, Object-Oriented Programming (OOP), JDBC, MySQL, and SQL to store and manage transaction data.

## Technologies Used

- Java
- OOP (Object-Oriented Programming)
- JDBC
- MySQL
- SQL
- Eclipse IDE

## Features

- User Registration
- User Login
- Multi-user transaction management
- Add Income and Expense
- View Transactions
- Search Transactions by Category
- Update Transactions
- Delete Transactions
- Automatic Income Calculation
- Automatic Expense Calculation
- Balance Calculation

## Database

The application uses MySQL as the database.

### Tables

**users**
- user_id
- name
- email
- password

**transactions**
- transaction_id
- user_id
- type
- category
- amount
- description
- transaction_date

## Project Structure

```text
src
├── dao
│   ├── UserDAO.java
│   └── TransactionDAO.java
├── main
│   └── Main.java
├── model
│   ├── Transaction.java
│   └── user.java
└── util
    └── dbconnection.java
