# StocksApp Documentation
Java 17
## Overview
StocksApp is a Java-based application. It uses Spring Boot for dependency injection and Maven for project management. The application allows users to manage clients, bank accounts, and investment accounts.

## Project Structure
The project is organized into the following packages:
- `com.example.stocksapp`: Contains the main application class.
- `com.example.stocksapp.model`: Contains the model classes representing the entities in the application.
- `com.example.stocksapp.service`: Contains the service classes that handle the business logic.

## Dependencies
The project uses the following dependencies:
- Spring Boot
- Maven

# API Endpoints Documentation

## 1. **Get All Clients**
- **Method**: `GET`
- **Endpoint**: `/api/clients`
- **Description**: Retrieves a list of all clients with their bank and investment account details.
- **Request**: No parameters required.
- **Response**:
  ```json
  [
    {
      "name": "Marek Rezny",
      "email": "rezny.marek@gmail.com",
      "iban": "SK-0031697106015800",
      "bankAccountBalance": 10000.0,
      "investmentAccountBalance": 5000.0
    }
  ]
  ```

## 2. **Get Client By Email**
- **Method**: `GET`
- **Endpoint**: `/api/clients/{email}`
- **Description**: Retrieves the details of a client by their email.
- **Path Parameter**:
    - `email`: The email of the client (e.g., `rezny.marek@gmail.com`).
- **Response**:
  ```json
  {
    "name": "Marek Rezny",
    "email": "rezny.marek@gmail.com",
    "iban": "SK-0031697106015800",
    "bankAccountBalance": 10000.0,
    "investmentAccountBalance": 5000.0
  }
  ```

## 3. **Add a New Client**
- **Method**: `POST`
- **Endpoint**: `/api/clients`
- **Description**: Creates a new client and their associated bank and investment accounts.
- **Request Body**:
  ```json
  {
    "name": "Marek Rezny",
    "email": "rezny.marek@gmail.com",
    "bankAccountBalance": 10000.0,
    "investmentAccountBalance": 5000.0
  }
  ```
- **Response**:
  ```json
  {
    "name": "Marek Rezny",
    "email": "rezny.marek@gmail.com",
    "iban": "SK-0031697106015800",
    "bankAccountBalance": 10000.0,
    "investmentAccountBalance": 5000.0
  }
  ```

## 4. **Update Client Details**
- **Method**: `PUT`
- **Endpoint**: `/api/clients/{email}`
- **Description**: Updates the details of an existing client based on their email.
- **Path Parameter**:
    - `email`: The email of the client to be updated.
- **Request Body**:
  ```json
  {
    "name": "Updated Name",
    "bankAccountBalance": 12000.0,
    "investmentAccountBalance": 6000.0
  }
  ```
- **Response**:
  ```json
  {
    "name": "Updated Name",
    "email": "rezny.marek@gmail.com",
    "iban": "SK-0031697106015800",
    "bankAccountBalance": 12000.0,
    "investmentAccountBalance": 6000.0
  }
  ```

## 5. **Delete a Client**
- **Method**: `DELETE`
- **Endpoint**: `/api/clients/{email}`
- **Description**: Deletes an existing client based on their email.
- **Path Parameter**:
    - `email`: The email of the client to be deleted.
- **Response**:
  ```json
  {
    "message": "Client GONE"
  }
  ```

---

### `Index`
Located in `com.example.stocksapp.Index.java`

#### Main Method
- `public static void main(String[] args)`: The entry point of the application. It creates stock objects, bank accounts, and performs some operations.

### `TradingCompanyService`
Located in `com.example.stocksapp.service.TradingCompanyService.java`

#### Methods
- `Person addClient(String name, String email, double bankAccountBalance, double investmentAccountBalance)`: Adds a new client to the trading company.
- `List<Person> getAllClients()`: Returns a list of all clients.
- `Person getClientByEmail(String email)`: Returns a client by their email.
- `Person updateClientByEmail(String email, Person updatedClient)`: Updates a client's information by their email.
- `boolean deleteClientByEmail(String email)`: Deletes a client by their email.

## How to Run
1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn clean install` to build the project.
4. Run the application using your IDE or by executing `mvn spring-boot:run`.
