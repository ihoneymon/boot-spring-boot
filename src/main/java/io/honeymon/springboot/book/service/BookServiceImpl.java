package io.honeymon.springboot.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.honeymon.springboot.book.domain.Book;
import io.honeymon.springboot.book.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository repository;
	
	@Override
	public List<Book> findAll() {
		log.trace("Find All books.");
		return repository.findAll();
	}

	@Override
	public Book findOne(Long id) {
		log.trace("Find by Id: {}", id);
		return repository.findOne(id);
	}

	@Override
	public Book save(Book book) {
		log.trace("Save book: {}", book);
		return repository.save(book);
	}

	@Override
	public void delete(Book book) {
		log.trace("Delete book: {}", book);
		repository.delete(book);
	}

}
