package io.honeymon.springboot.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@link Book} Repository
 * 기본적인 CRUD 는 JpaRepository 에 선언되어 있음
 * @author honeymon
 *
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
