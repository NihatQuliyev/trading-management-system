# Trading Management System
# Introduction
- Welcome to the Trade Control System (TMS) README. This document provides an overview of the TMS application, its features, installation instructions, and usage guidelines. This project is a Spring Boot application designed to manage product, category, and supplier-related operations in a PostgreSQL database.

  The TMS application offers a comprehensive set of features to facilitate effective management of various aspects of your business.


- Product Management: This tool provides users with the ability to exercise complete authority over their product inventory. It simplifies the processes of product listing, updating, and removal, offering invaluable assistance to businesses aiming to streamline their inventory management.

- Category Handling: Through this feature, users can easily create, modify, and eliminate product categories. Furthermore, products can be linked with one or multiple categories, streamlining the process of filtering and searching for specific items.

- Supplier Management: This utility allows businesses to effectively oversee their associations with suppliers. Users can add, revise, or remove supplier details as necessary, and they can conveniently connect products with their relevant suppliers, ensuring swift access to supplier information when required.

- Data Integrity and Verification: To enhance data precision and reliability, this system incorporates customized validation regulations.


# Technology
- IDE - Intellij Idea
- JDK - 17
- Spring Web - 2.7.14
- Spring Data Jpa
- Poatgresql
- Mapstruct
- Lombok
- Validation
- Security
- JWT
- Docker

# Structure
![elchin](https://github.com/NihatQuliyev/trading-management-system-with-jwt/assets/116736363/1088e708-fd3d-459c-b5c2-62c37385c55a)



# Endpoint

**User**
- http://localhost:8080/trading/user/registration [POST]
- http://localhost:8080/trading/user/login [POST]
- http://localhost:8080/trading/user/refresh-token [GET]

 **Category**
- http://localhost:8080/trading/category/registration [POST]
- http://localhost:8080/trading/category/all-category [GET]

 **Supplier**
- http://localhost:8080/trading/supplier/registration [POST]
- http://localhost:8080/trading/supplier/all-supplier [GET]
  
 **Supplier**
- http://localhost:8080/trading/product/registration  [POST]
- http://localhost:8080/trading/product/all-product   [GET]
- http://localhost:8080/trading/product/{id}          [GET]
- http://localhost:8080/trading/product/{id}          [PUT]
- http://localhost:8080/trading/product/{id}          [DELETE]

<br>
<br>

**Docker**
<br>
"After completing the project, we can create Docker images of the Postgres and service using the Dockerfile and docker-compose.yml configurations. Then, we deploy them as Docker containers. Finally, we keep the server running within the container."

<br>
<br>

![Screenshot (179)](https://github.com/NihatQuliyev/trading-management-system-with-jwt/assets/116736363/c71029eb-a518-416d-8e0f-89d44a214b6c)

<br>
<br>

**Security and Jwt**
<br>
<br>
The project is secured using JWT (JSON Web Token) technology. Initially, the user signs up, and during this process, the user is provided with an access token and a refresh token. When the user validates their registration with the email address they provided during the sign-up, the user's account is activated. Until the access token's expiration time (token duration), the user can access API endpoints such as product, supplier, and category. When the access token expires, the user can obtain a new access token by using the login endpoint to refresh their access token.

<br>
<br>

  ![jwt](https://github.com/NihatQuliyev/spring-security-jwt/assets/116736363/7becbadf-a3a5-4c1b-9b7a-65f375020cde)

<br>
**User registration**
<br>

![Screenshot (169)](https://github.com/NihatQuliyev/trading-management-system-with-jwt/assets/116736363/8c046cf5-57f1-4d9d-a4f2-3e29a0b601c5)

<br>
<br>

**User refresh-token**
<br>

![Screenshot (177)](https://github.com/NihatQuliyev/trading-management-system-with-jwt/assets/116736363/be4e2769-31f7-4aa2-a35e-1d6b73e503fb)

<br>
<br>

**User login**
<br>

![Screenshot (170)](https://github.com/NihatQuliyev/trading-management-system-with-jwt/assets/116736363/0015c352-3a60-4b6c-a998-f183a8f48d88)


<br>
<br>

**Category registration**
<br>

![Screenshot (171)](https://github.com/NihatQuliyev/trading-management-system-with-jwt/assets/116736363/06c2e1f7-02bb-4f0f-ac19-15b17abaa4a6)

<br>
<br>

**Product registration**
<br>

![Screenshot (173)](https://github.com/NihatQuliyev/trading-management-system-with-jwt/assets/116736363/f9afc53a-71a9-471f-83c9-a6de6aea3359)

<br>
<br>

**Product Show**
<br>

![Screenshot (174)](https://github.com/NihatQuliyev/trading-management-system-with-jwt/assets/116736363/86299fca-29c3-4357-9e18-e030556b0550)

<br>
<br>
**Product Update**
<br>
When updating the product, you can modify the desired fields while leaving the others unchanged. For example, in this case, the "price" has been modified.
<br>
<br>

![Screenshot (175)](https://github.com/NihatQuliyev/trading-management-system-with-jwt/assets/116736363/11a5d606-3c63-4a1d-98ba-882d58a9d245)


