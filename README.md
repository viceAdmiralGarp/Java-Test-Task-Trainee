# Web Scraper for Books to Scrape

This project is a web scraper that extracts book information from the "Books to Scrape" website and stores the data in a PostgreSQL database.

## Requirements

- Java 21+
- Spring Boot
- PostgreSQL (configured in `application.properties`)

## Endpoints and example request:

### To get a list of books with pagination:
- Request type: GET
- URL: http://localhost:8080/books?page=0&size=10
- Response: JSON containing a list of books and pagination information.

### To scrape books with page count:
--Request type: GET
--URL: http://localhost:8080/books/scrape?pages=3
--Response: message about successful scraping of books.

## Database
Books are stored in the PostgreSQL database. You can view them using a PostgreSQL client or a database management tool.
