package com.primeur.stage.port;


import com.primeur.stage.domain.dto.Book;

import java.util.List;
import java.util.Optional;


public interface BookStorageService {

	Book create(Book book);

	void delete(Book book);

	Book update(Book book, int id);

	Optional<Book> findById(int id);

	List<Book> getAll();
}
