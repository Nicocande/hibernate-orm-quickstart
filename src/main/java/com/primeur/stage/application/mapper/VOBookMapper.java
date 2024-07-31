package com.primeur.stage.application.mapper;

import java.util.Date;

import com.primeur.stage.application.vo.BookVO;
import com.primeur.stage.domain.dto.Book;
import jakarta.enterprise.context.RequestScoped;


@RequestScoped
public class VOBookMapper {

	BookVO vo;
	
	public VOBookMapper(Book book) {
		this.vo = convertEntity(book);
	}
	
	private BookVO convertEntity(Book book) {
		BookVO vo = new BookVO("","","");
		vo.setId("1");
		vo.setTitle((book.getTitle()));
		vo.setAuthor(book.getAuthor());
		vo.setDescription("MY DESC " + book.getTitle());
		return vo;
	}


	public BookVO getVO() {
		return this.vo;
	}

	
}
