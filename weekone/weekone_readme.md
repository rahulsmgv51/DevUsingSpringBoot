### step-by-step guide to create a basic Spring Boot CRUD API with a User entity and H2 database.
1. Configuration:
    - Project: Maven 
    - Language: Java 
    - Spring Boot version: 3.x 
    - Dependencies:
      - Spring Web 
      - Spring Data JPA 
      - H2 Database 
      - Lombok (optional, but helps reduce boilerplate)

2. Project Structure
   *  service         --> Business logic
   * repository      --> Data access layer
   * model           --> Entity classes
   * controller      --> REST Controllers
   * dto             --> Request/Response objects
   * exception       --> Custom exceptions
   * config          --> Configuration classes

### Request Payload

curl --location 'http://localhost:8080/weekone/userapi' \
--header 'Content-Type: application/json' \
--data '{
"name": "Anjali Sharma",
"age": 24,
"address": "56 Park Street, Delhi",
"role": "Tester",
"contact": "+91-9123456780"
}
'

{
"name": "Rahul Kumar",
"age": 28,
"address": "123 MG Road, Mumbai",
"role": "Developer",
"contact": "+91-9876543210"
}

{
"name": "Anjali Sharma",
"age": 24,
"address": "56 Park Street, Delhi",
"role": "Tester",
"contact": "+91-9123456780"
}

{
"name": "Mohit Verma",
"age": 32,
"address": "789 Lake View, Bengaluru",
"role": "Project Manager",
"contact": "+91-9988776655"
}

{
"name": "Sneha Patil",
"age": 26,
"address": "45 Rose Garden, Pune",
"role": "UI/UX Designer",
"contact": "+91-9001234567"
}

