package com.mmdev.traineejavadevelopertesttask.controller;

import com.mmdev.traineejavadevelopertesttask.entity.Book;
import com.mmdev.traineejavadevelopertesttask.repository.BookRepository;
import com.mmdev.traineejavadevelopertesttask.service.ScrapingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

	private final ScrapingService scrapingService;
	private final BookRepository bookRepository;

	@GetMapping("/scrape")
	public ResponseEntity<String> scrapeBooks() {
		try {
			scrapingService.scrapeAndSaveBooks();
			return ResponseEntity.ok("Books scraped and saved successfully!");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to scrape books");
		}
	}

	@GetMapping
	public ResponseEntity<Page<Book>> getBooks(Pageable pageable) {
		Page<Book> booksPage = bookRepository.findAll(pageable);
		return ResponseEntity.ok(booksPage);
	}
}
