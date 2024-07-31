package com.primeur.stage.application.mapper;

import com.primeur.stage.application.vo.BookVO;
import com.primeur.stage.domain.dto.Book;
import jakarta.enterprise.context.RequestScoped;


@RequestScoped
public class EntBookMapper {

	Book entity;

	public EntBookMapper(BookVO bookvo) {
		this.entity = convertEntity(bookvo);
	}

	private Book convertEntity(BookVO bookvo) {
		Book ent = new Book();
		ent.setTitle(bookvo.getTitle());
		ent.setAuthor(bookvo.getAuthor());
		ent.setPublisher(bookvo.getPublisher());
		ent.setDescription(bookvo.getDescription());

		return ent;
	}

	public Book getEntity() {
		return this.entity;
	}

	public  int mapId(String id) {

		return Integer.parseInt(id);
	}
}

