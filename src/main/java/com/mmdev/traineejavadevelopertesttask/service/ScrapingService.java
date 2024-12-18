package com.mmdev.traineejavadevelopertesttask.service;

import com.mmdev.traineejavadevelopertesttask.entity.Book;
import com.mmdev.traineejavadevelopertesttask.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapingService {

	private final BookRepository bookRepository;

	@Value("${books.scrape.url}")
	private String scrapeUrl;

	public void scrapeAndSaveBooks(int maxPages) throws IOException {
		List<Book> bookList = new ArrayList<>();

		for (int page = 1; page <= maxPages; page++) {
			String pageUrl = scrapeUrl + "/catalogue/page-" + page + ".html";
			Document doc = Jsoup.connect(pageUrl).get();

			Elements books = doc.select(".product_pod");

			if (books.isEmpty()) {
				break;
			}

			for (Element bookElement : books) {
				String title = bookElement.select("h3 a").attr("title");
				String price = bookElement.select(".price_color").text();
				String availability = bookElement.select(".instock.availability").text().trim();
				String rating = bookElement.select("p.star-rating").attr("class").replace("star-rating", "").trim();

				Book book = new Book();
				book.setTitle(title);
				book.setPrice(price);
				book.setAvailabilityStatus(availability);
				book.setRating(rating);

				bookList.add(book);
			}
		}

		if (!bookList.isEmpty()) {
			bookRepository.saveAll(bookList);
		}
	}
}
