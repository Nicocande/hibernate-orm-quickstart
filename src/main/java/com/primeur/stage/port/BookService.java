package com.primeur.stage.port;

import com.primeur.stage.application.vo.BookVO;

import java.util.List;
import java.util.Optional;


public interface BookService {


	BookVO create(BookVO bookvo);

	BookVO update(BookVO bookvo, String id);

	void delete(String id);

	List<BookVO> getAll();

	Optional<BookVO> findById(String id);


}
