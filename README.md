# 📖 BOOK STORE APP 📖
___
## 👋 Introduction 👋
Welcome to the Bookstore project! This is a project developed with Java Spring Boot for searching and buying books.
My project contains implemented relevant functionality, contains security, and is also great for e-commerce.
Learn more about this application in the following sections
___
## 🌐 Technology stack 🌐
**Language** Java

**Application Configuration** Spring Boot, Spring, Maven

**Accessing Data** Spring Data JPA, Hibernate, MySQL

**Web Development** Spring MVC, Servlets, JSP, Tomcat

**Security** Spring Security

**Testing and Documentation** JUnit, Mockito, Swagger, TestContainers

**Version Control** Git
___
## 💻 Functionality 💻
+ _`POST: /auth/registration` - endpoint for user registration_
+ _`POST: /auth/login` - endpoint for user login_


+ _`GET: /books` - endpoint for viewing all books_
+ _`GET: /books/{id}` - endpoint for searching a specific book_
+ _`POST: /books` - endpoint for administrators to adding a new book_
+ _`PUT: /books/{id}` - endpoint for administrators to updating a book_
+ _`DELETE: /books/{id}` - endpoint for administrators to deleting books_


+ _`GET: /categories` - endpoint for viewing all categories_
+ _`GET: /categories/{id}` - endpoint for searching a specific category_
+ _`GET: /categories/{id}/books` - endpoint for viewing books with specific category_
+ _`POST: /categories` - endpoint for administrators to create a new category_
+ _`PUT: /categories/{id}` - endpoint for administrators to update information about specific category_
+ _`DELETE: /categories/{id}` - endpoint for administrators to delete categories_

+ _`GET: /cart` - endpoint for users to view all items in their shopping cart_
+ _`POST: /cart` - endpoint for users to add items in their shopping cart_
+ _`PUT: /cart/cart-items/{itemId}` - endpoint for users to update quantity of the cart item_
+ _`DELETE: /cart/cart-items/{itemId}` - endpoint for users to delete items from their shopping cart_


+ _`GET: /orders` - endpoint for users to view orders history_
+ _`POST: /orders` - endpoint for users to place an orders_
+ _`PATCH: /orders/{id}` - endpoint  for administrators to update orders status_
+ _`GET: /orders/{orderId}/items` - endpoint  for users to view order items from specific order_
+ _`GET: /orders/{orderId}/items/{itemId}` - endpoint  for users to view a specific item from certain order_
___
## 🧰 Setup Instructions 🧰
1. Clone repository: clone from the console with the command: `git clone https://github.com/AgroFix/book-store.git`
2. Check database setup: Customize the database settings in the application.properties file.
3. Build and run project: `mvn spring-boot:run`
___
## 🐳 Running with Docker Compose 🐳
If you prefer to run the Bookstore application in a Docker container using Docker Compose, follow these steps:

1. Clone repository: Clone the repository from the console with the command: `git clone https://github.com/AgroFix/book-store.git`

2. Check Docker Compose file: Ensure that the `docker-compose.yml` file in the root of the project is configured appropriately. You can customize environment variables, ports, and other settings in this file.

3. Build and run the project: Execute the following commands in the project root directory:
   ```bash
   docker-compose build
   docker-compose up
___
## ⌛ History of creating the project (Challenges faced) ⌛
### _Docker and Liquibase_
After deploying my application using Docker, I found that my new liquibase scripts were no longer working, which prevented me from expanding my local database further.
To solve this problem, I asked my mentor for help, and together we found a solution.
I created a separate application-local.properties file where I set spring.docker.compose.enabled=false and ran my application locally from there, which helped me fix the problem.
### _JpaRepository_
While testing the controller for the order, I found out that I was getting a Lazy Initialization exception on one of the endpoints (every programmer has probably experienced this).
However, I was able to handle this exception by writing a native query on one of the OrderRepository methods.
___
## 💹 Possible improvements 💹
In the future I plan to add
ratings for books and the ability to rate them. And I also plan to add a system of discounts for books.
These two features will help to increase book sales, as it is important for a beginner to find an interesting book,
and for experienced readers, there will be more incentive to buy a book through discounts.
___
## 💟 Thanks 💟
Thank you for reading to the end, good luck!!!
