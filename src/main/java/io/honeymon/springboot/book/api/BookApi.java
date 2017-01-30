package io.honeymon.springboot.book.api;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.honeymon.springboot.book.domain.Book;
import io.honeymon.springboot.book.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookApi {

	@Autowired
	BookService service;
	
	@GetMapping
	public ResponseEntity<List<Book>> getAll() {
		List<Book> books = service.findAll();
		return ResponseEntity.ok(books);
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Book req) {
		Book book = service.save(req);
		URI createUri = UriComponentsBuilder.fromUriString("/api/books/{id}").buildAndExpand(book.getId()).toUri();
		return ResponseEntity.created(createUri).build();
	}
}
