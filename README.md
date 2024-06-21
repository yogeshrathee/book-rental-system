# Book Rental System

## Overview
The Book Rental System is a RESTful API service developed using Spring Boot and MySQL to manage an online book rental system. This system allows users to register, browse available books, rent books, and return books. The system also includes authentication and authorization features.

## Features
1. **User Registration and Login**
   - Users can register with an email address and password.
   - Passwords are encrypted and stored using BCrypt.
   - Fields: Email, Password, First Name, Last Name, Role (defaulted to "USER" if not specified).
   - Registered users can log in using their email address and password.

2. **Book Management**
   - Store and manage book details.
   - Fields: Title, Author, Genre, Availability Status (indicates whether the book is available to rent or not).
   - Any user can browse all available books.
   - Only the administrator can create, update, and delete books.

3. **Rental Management**
   - Users can rent books using the service.
   - A user cannot have more than two active rentals.
   - Users can return books they have rented.

## Technologies Used
- **Spring Boot**: For building the RESTful web services.
- **MySQL**: For persisting the data.
- **Spring Security**: For implementing authentication and authorization.
- **BCrypt**: For password encryption.
- **JUnit**: For unit testing.
- **MockMvc**: For testing the web layer.

## Setup and Installation

### Prerequisites
- Java 17 or higher
- Maven 3.8.1 or higher
- MySQL Server

### Clone the Repository
```sh
git clone https://github.com/yourusername/book-rental-system.git
cd book-rental-system


### API Endpoints

// Register a new user
POST /api/auth/register

// Log in a user
POST /api/auth/login

// Get all available books (authenticated users)
GET /api/books

// Create a new book (admin only)
POST /api/books

// Update an existing book (admin only)
PUT /api/books/{id}

// Delete a book (admin only)
DELETE /api/books/{id}

// Rent a book (authenticated users)
POST /api/books/{bookId}/rent

// Return a rented book (authenticated users)
POST /api/books/{bookId}/return

