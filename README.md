# Money Transfer Application

A simple Spring Boot application for money transfers between user accounts.

## Features

1. Create user accounts with initial balance
2. Transfer money between accounts
3. View transaction history

## Running the Application

1. Make sure you have Java 17+ installed
2. Run the application using Maven:
   ```
   mvn spring-boot:run
   ```
3. The application will start on `http://localhost:8080`
4. Access H2 Console at `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:moneydb`
   - Username: `sa`
   - Password: (empty)

## API Endpoints

1. Create User Account
   ```
   POST /api/users
   {
     "name": "John Doe",
     "email": "john@example.com",
     "initialBalance": 1000.00
   }
   ```

2. Transfer Money
   ```
   POST /api/transactions/transfer
   {
     "fromAccountId": 1,
     "toAccountId": 2,
     "amount": 100.00
   }
   ```

3. Get Transaction History
   ```
   GET /api/transactions/{userId}
   ```

## Error Handling

- The application includes basic error handling for:
  - Insufficient funds
  - Invalid user accounts
  - Duplicate email addresses
  - Invalid request data
