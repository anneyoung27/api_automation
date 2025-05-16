# API Automation with REST Assured

## What's in this repository?
The project involves building and testing APIs for three moduels: Products, Cart, and User.
The APIs support CRUD operations, filtering, sorting, and specific use cases like login, user-specific data, and data-based queries.

## What's the purpose of this project?
The purpose of this project is to implement what I have learned in api automation testing using Rest Assured with Java programming language.

## Key Features
- TestNG for test execution
- Maven for dependency management
- Parallel testing
- API Chaining
- Extent Report and Allure Report for test reporting
- Helper class and utility

## Technologies
- Java 21
- Rest Assured
- Hamcrest
- TestNG Framework
- ExtentReport
- AllureReport
- Jackson Databind
- JSON & GSON

## Project Structure
```
allure-results/                         # Stores log files and test execution results (Allure)
│
reports/                                # Stores generated test reports (Extent Report)
│
src/
├── main/
│   ├── java/
│   │    ├── base/                      # Setup class and validation method
│   │    ├── helper/                    # Helper classes for common actions and utilities
│   │    ├── payloads/                  # Serialization class (converting a Java object into request body)
│   │    ├── pojo/                      # Data model classes (Plain Old Java Objects)
│   │    ├── rough/                     # Experimental or trial code
│   │    ├── routes/                    # API route and endpoint definitions
│   │    └── utils/                     # General utility classes
│   │    
│   └── resources/
│   │    ├── config/                    # Configuration files (e.g., properties files)
│   │    ├── ProductData.csv/           # Product test data in CSV format
│   │    └── ProductSchema.json/        # JSON schema for product data validation
├── test/
│   ├── documents/                      # Reference documents for test scenarios
│   └── java/
│       ├── ddt/                        # Data driven testing
│       └── testcases/                  # Test cases
├── DataDrivenTestRunner.xml/           # XML runner for data-driven tests
├── TestCaseRunner.xml/                 # XML runner for general test execution
└── pom.xml                             # Maven configuration file with dependencies

```
### 1. Project URL
```https://fakestoreapi.com```

### 2. Modules and Functionalities
#### 2.1 Products Module
- API Endpoints:
    - GET /products: Retrieve all products
    - GET /products/{id}: Retrieve a product by ID
    - GET /products?limit=x: Retrieve a limited number of products
    - GET /products?sort=asc|desc: Retrieve all product categories
    - GET /products/category/{category}: Retrieve products by category
    - POST /products: Add a new product
    - PUT /products/{id}: Update an existing product
    - PATCH /products/{id}: Partially update a product
    - DELETE /products/{id}: Delete a product
- Request Body:<br />
![image](https://github.com/user-attachments/assets/2099dcad-d366-45af-ace0-f3288efd1b9d)
- Functional Requirements:<br />
    - Support CRUD operations
    - Enable filtering by category, sorting, and limiting result
#### 2.2 Cart Module
- API Endpoints:
    - GET /carts: Retrieve all cart items
    - GET /carts/{id}: Retrieve a cart by ID
    - GET /carts?limit=x: Retrieve a limited number of carts
    - GET /carts?sort=asc|desc: Retrieve carts in a specific order
    - GET /carts?startDate=YYYY-MM-dd&endDate=YYYY-MM-dd: Retrieve carts in a date range
    - GET /carts/users/{userID}: Retrieve carts by user id
    - POST /carts: Add a new cart
    - PUT /carts/{id}: Update a cart
    - PATCH /carts/{id}: Partially update a cart
    - DELETE /carts/{id}: Delete a cart
- Request Body:<br />
![image](https://github.com/user-attachments/assets/24631966-e413-4c9f-a122-cdcb39e6c867)
- Functional Requirements:<br />
    - Support CRUD operations
    - Filter carts by date range
    - Enable sorting and limiting of results
#### 2.3 User Module
- API Endpoints:
    - POST /auth/login: Log in to generate a token
    - GET /users/{id}: Retrieve user details by ID
    - GET /users: Retrieve all users
    - POST /users: Add a new user
    - PUT /users/{id}: Update a user
    - PATCH /users/{id}: Partially update a user
    - DELETE /users/{id}: Delete a user
- Request Body: <br />
![image](https://github.com/user-attachments/assets/31b4de33-3cd2-4ae7-bf38-6f7750cf3578)
- Functional Requirements:<br />
    - Support CRUD operations
    - Authenticate users and return token

### 3. Project Pipeline
![Project Pipeline](https://github.com/user-attachments/assets/fea9fa4c-afb1-4947-b309-bfd5592960a2)


## Installation
1. Clone this repository:<br />
   `git clone https://github.com/anneyoung27/cucumber_bdd.git`
2. Navigate to the project directory:<br />
3. Update configuration<br />
   Modify `resources/config.properties` as needed.
4. Install dependencies using Maven:<br />
   `mvn clean install`


