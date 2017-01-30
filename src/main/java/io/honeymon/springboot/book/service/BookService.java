package io.honeymon.springboot.book.service;

import java.util.List;

import io.honeymon.springboot.book.domain.Book;

public interface BookService {

	/**
	 * Find All
	 * 
	 * @return
	 */
	List<Book> findAll();

	/**
	 * Find {@link Book} by Id
	 * 
	 * @param id
	 * @return
	 */
	Book findOne(Long id);

	Book save(Book book);
	
	void delete(Book book);
}
