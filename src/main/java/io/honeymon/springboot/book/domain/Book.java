package io.honeymon.springboot.book.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Book {

	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	private String author;
}
