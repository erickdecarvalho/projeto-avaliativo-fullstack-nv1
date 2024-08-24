# Car Rental CRM - Backend API

This repository contains the backend of a Car Rental CRM API, developed as part of a T-Academy Bootcamp project. The API was built using Java with Spring Boot and serves as the backend for a car rental management application. The frontend application, which consumes this API, was developed in Angular and is stored in the same repository. However, this document focuses on the backend documentation.

# Overview

The Car Rental CRM API allows managing vehicles, customers, and car rentals. It provides endpoints for full CRUD (Create, Read, Update, Delete) operations for the main entities in the system.

## Features

- **Vehicle Management**: Full CRUD operations for vehicles available for rent.
- **Customer Management**: Full CRUD operations for customers using the rental service.
- **Rental Management**: Full CRUD operations for managing rental transactions.
- **Advanced Filtering**: Search and filter vehicles by type, year, color, and rental status.
- **Validation**: Data integrity validation, such as unique vehicle license plates.

## Installation and Setup

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL or MySQL database
