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

# Structure
![drow](https://github.com/NihatQuliyev/trading-management-system/assets/116736363/ba70107d-bc77-4a89-ad2b-dc06c94d39d3)



# Endpoint
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
#
- Swagger endpoint show
![endpoint](https://github.com/NihatQuliyev/trading-management-system/assets/116736363/e853d49c-34b7-4516-bf78-8f8822456e7a)


# Jar Building
 - jar name: trading-management-service-0.0.1-SNAPSHOT.jar
![building](https://github.com/NihatQuliyev/trading-management-system/assets/116736363/37db3514-a97b-490f-8ba7-475e9a09c68b)


