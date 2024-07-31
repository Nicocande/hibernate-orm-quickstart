package com.primeur.test.stage.adapter.rest;


import com.primeur.stage.application.BookBO;
import com.primeur.stage.application.BOException;
import com.primeur.stage.application.mapper.EntBookMapper;
import com.primeur.stage.application.mapper.VOBookMapper;
import com.primeur.stage.application.vo.BookVO;
import com.primeur.stage.domain.dto.Book;
import com.primeur.stage.port.BookStorageService;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookBOTest {

    @InjectMocks
    BookBO bookBO;

    @Mock
    BOException BOException;

    @Mock
    BookStorageService bookStorageService;

    @Mock
    EntBookMapper entBookMapper;

    @Mock
    VOBookMapper voBookMapper;

    private Book book;

    private BookVO vo;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1);
        book.setTitle("FirstMovie");


        vo = new BookVO("","","");
        vo.setId("1");
        vo.setTitle("FirstMovie");

    }


    //Test for method getById();

    @Test
    public void getById() {
        when(bookStorageService.findById(0)).thenReturn(Optional.of(book));

        bookBO.findById("0");

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            bookBO.findById(null);
        });

        Mockito.verify(bookStorageService, Mockito.times(1)).findById(0);
        Mockito.verify(bookStorageService, Mockito.only()).findById(Mockito.anyInt());
    }


    @Test
    public void whenGetByIdGivenValidId() {

        when(bookStorageService.findById(1)).thenReturn( Optional.of(book));

        Optional<Book> bookBO1 = bookStorageService.findById(Integer.parseInt("1"));

        assertTrue(bookBO1.isPresent(), "book with inserted Id be found");

    }

    @Test
    public void whenGetByIdGivenAbsentId() {


        Optional<Book> books = bookStorageService.findById(Integer.parseInt("1"));

        assertNotNull(books, "book with inserted id be not found");

        assertTrue(books.isEmpty());

    }

    //Test for method getALL;

    @Test
    public void getAll() {

        bookBO.getAll();


        Mockito.verify(bookStorageService, Mockito.times(1)).getAll();
        Mockito.verify(bookStorageService, Mockito.only()).getAll();

    }

    @Test
    public void getAllThenReturnBookVOListConverted(){
        List<Book> bookList= new ArrayList<>();
        bookList.add(book);
        when(bookStorageService.getAll()).thenReturn(bookList);
        bookBO.getAll();

    }

    @Test
    public void whenGetAllFromBookBO_thenGetAllShouldBeNullAndThrowRuntimeException() {

        Mockito.when(bookStorageService.getAll()).thenReturn(null);

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            bookBO.getAll();
        });

        Assertions.assertEquals("The list doesn't exist", thrown.getMessage());

    }


    @Test
    public void whenGetAllFromBookBO_thenReturnEmptyList() {

        bookBO.getAll();

        List<Book> books = bookStorageService.getAll();

        assertNotNull(books, "The return list doesn't exist");
        assertTrue(books.isEmpty());

    }


    @Test
    public void whenGetAllFromBookBO_thenShouldNotReturnNullList() {
        ArrayList<Book> objects = new ArrayList<>();

        Mockito.when(bookStorageService.getAll()).thenReturn(objects);

        List<Book> result = bookStorageService.getAll();
        assertNotNull(result);
        assertInstanceOf(ArrayList.class, result);

    }

    //Test for method CREATE;

    @Test
    public void create() {


        when(bookStorageService.create(Mockito.any())).thenReturn(book);

        bookBO.create(vo);

        Mockito.verify(bookStorageService, Mockito.times(1)).create(Mockito.any());
        Mockito.verify(bookStorageService, Mockito.only()).create(Mockito.any());
    }




    //Test method for UPDATE
    @Test
    public void update() {


        lenient().when(bookStorageService.update(Mockito.any(), Mockito.anyInt())).thenReturn(book);

        BookVO bookVO = new BookVO("","","");
        bookVO.setTitle("FirstMovie");

        bookBO.update(bookVO, "1");


        Mockito.verify(bookStorageService, Mockito.times(1)).update(Mockito.any(), Mockito.anyInt());
        Mockito.verify(bookStorageService, Mockito.only()).update(Mockito.any(), Mockito.anyInt());

    }


    @Test
    public void delete() {
        when(bookStorageService.findById(0)).thenReturn(Optional.ofNullable(book)).thenThrow(RuntimeException.class);


        bookBO.delete("0");

        Mockito.verify(bookStorageService, Mockito.times(1)).delete(Mockito.any());
        Mockito.verify(bookStorageService,Mockito.times(1)).findById(Mockito.anyInt());

    }

}