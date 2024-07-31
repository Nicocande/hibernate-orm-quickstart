package com.primeur.stage.application;

import com.primeur.stage.application.mapper.EntBookMapper;
import com.primeur.stage.application.mapper.VOBookMapper;
import com.primeur.stage.application.vo.BookVO;
import com.primeur.stage.domain.dto.Book;
import com.primeur.stage.port.BookService;
import com.primeur.stage.port.BookStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class BookBO implements BookService {


    @Inject
    BookStorageService bookStorageService;

    @Inject
    EntBookMapper entBookMapper;

    @Inject
    VOBookMapper voBookMapper;

    @Override
    public Optional<BookVO> findById(String id) {
        if(id==null){
            throw new RuntimeException("inserted id doesn't exist");
        }
        int intId = entBookMapper.mapId(id);
        Optional<Book> book = bookStorageService.findById(intId);
        return book.isPresent() ? book.map(b -> new VOBookMapper(b).getVO()) : Optional.empty();
    }

    @Override
    public List<BookVO> getAll() {
        List<Book> bookList = bookStorageService.getAll();
        if(bookList==null){
            throw new RuntimeException("The list doesn't exist");
        }
        List<BookVO> vobookList = new ArrayList<>();

        for (Book book : bookList) {
            VOBookMapper voBookMapper = new VOBookMapper(book);
            BookVO bookvo = voBookMapper.getVO();
            vobookList.add(bookvo);

        }
        return vobookList;
    }

    public BookVO create(BookVO bookvo) {
        EntBookMapper entMapper = new EntBookMapper(bookvo);
        Book entbook = bookStorageService.create(entMapper.getEntity());
        VOBookMapper mapperVO = new VOBookMapper(entbook);
        return mapperVO.getVO();

    }

    @Override
    public BookVO update(BookVO bookvo, String id) {
        EntBookMapper entMapper = new EntBookMapper(bookvo);
        Book book = entMapper.getEntity();
        book.setId(entBookMapper.mapId(id));
        Book putbook = bookStorageService.update(book, book.getId());
        VOBookMapper mapperVO = new VOBookMapper(putbook);
        return mapperVO.getVO();
    }

    @Override
    public void delete(String id) {

        int i = entBookMapper.mapId(id);
        Book bookFound = bookStorageService.findById(i)
                .orElseThrow(() -> new RuntimeException("Delete failed: could not find book with id " + id));
        bookStorageService.delete(bookFound);

    }





}






