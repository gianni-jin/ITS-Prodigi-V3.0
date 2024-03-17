# Project Overview

## Introduction
ITS Prodigi V3.0 is a back-end school management application that builds upon my two previous, much simpler Java projects, started over five months ago. 

The application enables school administrators to add new students, teachers, courses, classes, and programs to the database, modify existing entries, delete them, and establish relationships between them. Additionally, users can perform search operations using certain parameters to filter through the database. 


## Technologies Used
The back-end is built with Spring Boot and Hibernate/JPA. I've also used SQL/JBQL to write some custom/non-CRUD queries. The database of choice for this project is MySQL. 

As for now, it doen not have a front-end part. I wanted to use Angular at the beginning, but due to time constaints, I wasn't able to study the framework well enough up to the point of starting to use it. 

For API testing, I've used Postman. For API documentation, I've used Swagger UI/Open API.

## The Origin of the Idea

After gaining a foundational understanding of Spring and Hibernate, I wanted apply this knowledge and deepen the knowledge. The idea of designing my program to meet the needs of a familiar environment appealed to me. And since I had already built two Java projects on school management, I wanted to take a step further and build the same idea but with spring/hibernate as back-end.

## Future Plans

The project is currently in its initial stages. I plan to incorporate additional functionalities such as authentication and authorization, logging, among others. I also intend to add more related entities to increase the complexity of the program. Furthermore, I want to add the front-end part using as Angular or React.


## API Documentation

Upon launching the program, we can go to http://localhost:8080/swagger-ui/index.html#/ to access to the list of rest endpoints of the program:

![image](https://github.com/gianni-jin/ITS-Prodigi-V3.0/assets/129873947/1052e9a6-8640-4332-b018-a0573ee35721)

![image](https://github.com/gianni-jin/ITS-Prodigi-V3.0/assets/129873947/06b81991-02ff-4f03-8320-a60f12dc3594)

![image](https://github.com/gianni-jin/ITS-Prodigi-V3.0/assets/129873947/738c6a0f-47c0-4c76-9b8a-980e13b6ea65)

![image](https://github.com/gianni-jin/ITS-Prodigi-V3.0/assets/129873947/21375d4a-d7d3-419e-a37a-9e7a042e6d24)

![image](https://github.com/gianni-jin/ITS-Prodigi-V3.0/assets/129873947/6d63c570-6c53-49a5-9bd8-50ccb07009cc)




## Database Schema
![image](https://github.com/gianni-jin/ITS-Prodigi-V3.0/assets/129873947/cf3ebb6f-0326-403b-b829-a8682d8ca2d6)

# Code Structure
The program follows the MVC model structure, emphasizing the separation of concerns and clear delineation of responsibilities. 

However, as for now, it's a back-end only program, meaning that the view part needs to be implemented yet.

Here's a brief overview of the project structure:

## Controller Package
This package contains the controller classes that manage incoming HTTP requests. These controllers utilize the methods defined in the "service" package to execute operations and return the appropriate HTTP responses, allowing users to access to resources provided by the program at a certain rest endpoint. 


## Model Package

This package contains the five entity classes of the program, and they're the following: 
- "Program" offered by the school, such as "Full-stack Developer & Integrator"
- "StudentClass", namely classes made by students
- "Student"
- "Teacher"
- "Subject"

  
I've I've used JPA annotations such as @Id and @Column to  map  the  entities to the database tables. For example, "Employee.java" maps to the "tbl_employee" table and "Department.java" maps to the "tbl_departments" table.

Besides, I've made use of Hibernate mapping annotations to set up the relationship between this entity with the other one. 

Finally, I've made use of various lombak annotations such as "@getter "@Getter", "@Setter", "@ToString", "@NoArgsConstructor", "@AllArgsConstructor". They automatically generate getter, setter, toString, and constructor methods.

### Relationships between Entities



To avoid over-complication, I decided to not set up any relationshisp between "Program" and "Teacher", and between "Teacher" and "Student". Rather, one "Program" can have many "StudentClass", and then one "StudentClass" can have many "Teacher". And one "StudentClass" can have many "Teacher" and many "Student", even though there's no direct link between "Teacher" and "Student".

### @JsonIdentityInfo and Circular Reference Problem 

At the beginning, when I didn't use this annotation, whenever I used Postman to test the endpoins, I always had circular references. Therefore, I've added this annotation  to handle them. It specifies that the "id" field should be used as the identifier for instances of "Employee" during serialization and deserialization.



## Repository Package

The "repository" package  provides the interface for interacting with the database. In this case, the "DepartmentRepository" interface extends "JpaRepository", which is a part of Spring Data JPA. "JpaRepository" provides methods for all the CRUD operations (Create, Read, Update, Delete) on the entity.

In addition to the standard CRUD methods offered by JpaRepository, I've implemented custom queries using JPQL annotated with "@Query". This not only enhances the compatibility and readability of our queries but also extends the range of operations available to users.

## Service Package
The "service" package contains two types of classes: interfaces, or classes/implementation of the interfaces. 

For example, The "ProgramService" interface  defines the contract for a service that manages "Program" entities in the application. It declares methods for creating, reading, updating, and deleting "Program" entities, as well as for performing various queries on them. 

But then, it's the job of "ProgramServiceImpl" to implement the methods of the interface, calling the repository methods of various repository classes to manage not only "Program" entities, but also the associated "Subject" and "StudentClass" entities.

Among all the methods, save and update methods are of particular interest. Take the update and save methods in "ProgramServiceImpl" as an example. I had to write code to not only check the existance of the current "Program" entities, but also to manage the correlated "Subject" and "StudentClass" entities. This is mainly because I had set the cascade type of "Program" to "Subject" and "StudentClass" as "ALL". Therefore, I needed to take into account the situations in which the user enters a new record of "Program", but also specifying already the related "StudentClass" and "Subject" records. 


## Exception Package

The "exception" package in the ITS Prodigi V3.0 application contains classes for handling exceptions. These will be called by methods in service implementation classes when necessary.


"ErrorObject" class represents an error that occurs in the application. It contains three fields: "statusCode", "message" and "timestamp"

"GlobalExceptionHandler" class extends "ResponseEntityExceptionHandler". It contains two methods: "handleResourceNotFoundException" and "handleResourceAlreadyExistsException"


The way it works is the following: 

When a ResourceNotFoundException is thrown, "handleResourceNotFoundException" creates an "ErrorObject" with the exception's message, a status code of 404 (representing a "Not Found" error), and the current timestamp. It then returns a "ResponseEntity" containing the "ErrorObject" and a HTTP status of "HttpStatus.NOT_FOUND".

"handleResourceAlreadyExistsException" works very similarly to "handleResourceNotFoundException", just the code and HTTP status will be different. 
