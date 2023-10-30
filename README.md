# Microservices E-commerce Application

A microservices-based e-commerce application that includes services for product management, order processing, and payments. This README provides an overview of the project's architecture, how to set it up, and its key components.

## Table of Contents

- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Directory Structure](#directory-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Setup and Configuration](#setup-and-configuration)
- [Components](#components)
- [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
- [Centralized Configuration](#centralized-configuration)
- [Service Registration and Discovery](#service-registration-and-discovery)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

This project is a microservices-based e-commerce application consisting of three main components: **Product Service**, **Order Service**, and **Payment Service**. These services interact with each other to provide end-to-end functionality for managing products, processing orders, and handling payments.

The architecture follows best practices for microservices, including centralized configuration management with Spring Cloud Config and service registration and discovery using Spring Cloud Eureka.

## Architecture

The project follows a microservices architecture with the following key components:

- **Product Service**: Manages product information, including creation and retrieval.

- **Order Service**: Handles order placement and retrieves order details.

- **Payment Service**: Manages payments, including recording transactions and providing payment details.

- **Spring Cloud Config Server**: Centralized configuration management for microservices.

- **Spring Cloud Eureka Server**: Service registry for service registration and discovery.

The communication between microservices is facilitated using Spring Cloud Feign clients.

## Directory Structure

The project's directory structure is organized as follows:

### Project Directory Structure

- [config-server](./ConfigServer/)
  - Configuration server code and files
- [eureka-server](./service-registry/)
  - Eureka service registry server code and files
- [product-service](./ProductService/)
  - Product Service code and files
- [order-service](./OrderService/)
  - Order Service code and files
- [payment-service](./PaymentService/)
  - Payment Service code and files
- [README.md](./README.md)
  - Project documentation (this file)


Each microservice (`product-service`, `order-service`, `payment-service`) has its directory with its source code and resources. The `config-server` and `eureka-server` directories contain the respective configurations.

## Getting Started

### Prerequisites

Before setting up and running the application, make sure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- Maven (for building the application)
- MySQL (for the database)

### Setup and Configuration

1. **Database Configuration**:

   - Create a MySQL database for each microservice (`productdb`, `orderdb`, `paymentdb`).
   - Update the `application.yml` or `application.properties` in each microservice's directory to configure the database settings.

2. **Spring Cloud Config Server**:

   - Configure the `config-server` to point to your Git repository or use local files for configurations.
   - Update the `config-server`'s `bootstrap.yml` with the correct configurations.

3. **Spring Cloud Eureka Server**:

   - Update the `eureka-server`'s `application.yml` with the desired port and hostname settings.

## Components

### Product Service

- **Description**: Manages products, including adding, retrieving, and reducing quantities.
- **Technologies**: Spring Boot, Spring Data JPA, Feign Clients.
- **Source Directory**: `product-service/`
- **API Endpoints**: See [Product Service Endpoints](#product-service-endpoints).

### Order Service

- **Description**: Handles order placement, retrieves order details, and communicates with the `Product Service`.
- **Technologies**: Spring Boot, Spring Data JPA, Feign Clients.
- **Source Directory**: `order-service/`
- **API Endpoints**: See [Order Service Endpoints](#order-service-endpoints).

### Payment Service

- **Description**: Manages payments, records transactions, and provides payment details.
- **Technologies**: Spring Boot, Spring Data JPA.
- **Source Directory**: `payment-service/`
- **API Endpoints**: See [Payment Service Endpoints](#payment-service-endpoints).

### Spring Cloud Config Server

- **Description**: Centralized configuration server for microservices.
- **Technologies**: Spring Cloud Config.
- **Source Directory**: `config-server/`
- **Configuration Files**: Update the `config-server` to point to your Git repository or local configuration files.

### Spring Cloud Eureka Server

- **Description**: Service registry for service registration and discovery.
- **Technologies**: Spring Cloud Eureka.
- **Source Directory**: `eureka-server/`
- **Configuration Files**: Update the `eureka-server` for port and hostname settings.

## Running the Application

1. **Build**: Build each microservice using Maven.

2. **Run**: Run the microservices and servers in the following order:

   - Start the `config-server`.
   - Start the `eureka-server`.
   - Start the microservices (`product-service`, `order-service`, `payment-service`).

## Endpoints

Here are the endpoints for each microservice:

### Product Service Endpoints

- `POST /product` : Create a new product.
- `GET /product/{productId}` : Retrieve product details.
- `PUT /product/reduceQuantity/{productId}` : Reduce product quantity.

### Order Service Endpoints

- `POST /order/placeOrder` : Place a new order.
- `GET /order/{orderId}` : Retrieve order details.

### Payment Service Endpoints

- `POST /` : Record a payment transaction.
- `GET /{orderId}` : Retrieve payment details by order ID.

## Centralized Configuration

Centralized configuration management is achieved through Spring Cloud Config. The configuration files are stored in a Git repository or local files. Each microservice fetches its configuration from the `config-server`.

## Service Registration and Discovery

Service registration and discovery are implemented using Spring Cloud Eureka. Each microservice registers itself with the Eureka server, allowing other services to discover and communicate with it.

## Configuration Server

The Configuration Server is a crucial component in our microservices architecture. It serves as a centralized configuration management system that stores configuration data for all services in a single location. This allows us to configure and manage various aspects of our services, such as database connections, feature toggles, and environment-specific settings, without the need to modify code.

### Key Features:
- Centralized configuration management.
- Environment-specific configuration profiles.
- Versioning and history of configuration changes.
- Integration with version control systems for managing configuration files.

## Service Registry

The Service Registry, powered by Eureka, plays a vital role in managing service discovery and registration within our microservices ecosystem. It helps services locate and communicate with each other in a dynamic and scalable way. Services register themselves with the Service Registry, making it easy to discover and interact with other services.

### Key Features:
- Service registration and discovery.
- Load balancing and failover for service communication.
- Dynamic updates to service availability.
- Integration with other microservices components.

