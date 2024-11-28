# Web Scraper for Books to Scrape

This project is a web scraper that extracts book information from the "Books to Scrape" website and stores the data in a PostgreSQL database.

## Requirements

- Java 21+
- Spring Boot
- PostgreSQL (configured in `application.properties`)

## Endpoints

- **POST /books/scrape**: Scrapes books data from the specified URL.
- **GET /books**: Retrieves books with pagination.

Example request:
/books/scrape
GET /books?page=0&size=10

## Database
Books are stored in the PostgreSQL database. You can view them using a PostgreSQL client or a database management tool.
