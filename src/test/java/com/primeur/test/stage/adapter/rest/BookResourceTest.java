package com.primeur.test.stage.adapter.rest;


import com.primeur.stage.adapter.rest.BookResource;
import com.primeur.stage.adapter.rest.NullPointException;
import com.primeur.stage.application.vo.BookVO;
import com.primeur.stage.port.BookService;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BookResourceTest {

    @InjectMocks
    BookResource bookResource;

    @Mock
    BookService bookService;

    BookVO vo;


    @BeforeEach
    void setUp() {

        vo = new BookVO("title",
                "author",
                "description");

    }

//  Test CREATE BookResource

    @Test
    public void shouldCreateBookWhenGivenValidInput() {

        vo.setId("1");
        bookResource.create(vo);

        Mockito.verify(bookService, Mockito.times(1)).create(Mockito.any());
        Mockito.verify(bookService, Mockito.only()).create(Mockito.any());

    }

    @Test
    public void whenCreateBookThenReturnResponse201() {
        vo.setId("1");
        when(bookService.create(vo)).thenReturn(vo);

        Response build = bookResource.create(vo);
        assertEquals(build.getStatus(), 201);

    }
    @Test
    public void shouldCreateBookFromBookService_givenNullId(){
        vo.setId(null);
        assertThrows(WebApplicationException.class,
                ()-> {
                    try(Response response = bookResource.create(vo)) {

                        assertEquals(400, response.getStatus());
                    }

                });
    }

    @Test
    public void shouldThrowWebApplicationExceptionWhenIdIsInvalid() {
        vo.setId("-1");

        lenient().when(bookService.create(vo)).thenThrow(new NullPointException("Id was invalidly set on request"));
        assertThrows(NullPointException.class,
                () -> bookResource.create(vo));

    }

    @Test
    @DisplayName("Should not create Book when title is null")
    public void shouldThrowNullPointExceptionWhenTitleIsNull() {
        vo.setId("1");
        vo.setTitle(null);

        lenient().when(bookService.create(vo)).thenThrow(new NullPointException("Should not create Book when title is null"));
        assertThrows(NullPointException.class,
                () -> bookResource.create(vo));


    }


    @Test
    @DisplayName("Should not create Book when author is null")
    public void shouldThrowNullPointExceptionWhenAuthorIsNull() {
        vo.setId("1");
        vo.setAuthor(null);

        lenient().when(bookService.create(vo)).thenThrow(new NullPointException("Should not create Book when author is null"));
        assertThrows(NullPointException.class,
                () -> bookResource.create(vo));

    }


    @Test
    public void shouldThrowNullPointExceptionWhenDescriptionIsNull() {
        vo.setId("1");
        vo.setDescription(null);

        lenient().when(bookService.create(vo)).thenThrow(new NullPointException("Should not create Book when description is null"));
        assertThrows(NullPointException.class,
                () -> bookResource.create(vo));

    }

    // Test getAll BookResource

    @Test
    public void getAllTimesCalled() {
        List<BookVO> vobooks = new ArrayList<>();
        vobooks.add(vo);
        when(bookService.getAll()).thenReturn(vobooks);

        bookResource.getAll();

        Mockito.verify(bookService, Mockito.times(1)).getAll();
        Mockito.verify(bookService, Mockito.only()).getAll();


    }

    @Test
    public void getAllThenReturnList() {
        List<BookVO> vobooks = new ArrayList<>();
        vobooks.add(vo);
        when(bookService.getAll()).thenReturn(vobooks);

        bookResource.getAll();

        assertSame(vobooks.get(0), vo);


    }

    //Test FIND_BY_ID BookResource

    @Test
    public void findById() {
        vo.setId("0");
        when(bookService.findById("0")).thenReturn(Optional.of(vo));

        bookResource.getById("0");

        Mockito.verify(bookService, Mockito.times(1)).findById("0");
        Mockito.verify(bookService, Mockito.only()).findById("0");

    }


    @Test
    public void findByIdGivenAbsentId() {


        Optional<BookVO> books = (bookService.findById("1"));

        assertNotNull(books, "book with inserted id be not found");

        assertTrue(books.isEmpty());

        assertThrows(WebApplicationException.class, () -> bookResource.getById(""));


    }

    //Test UPDATE BookResource

    @Test
    public void updateTimesCalled() {
        vo.setId("1");

        when(bookService.update(vo, "1")).thenReturn(vo);

        bookResource.update(vo, "1");

        Mockito.verify(bookService, Mockito.times(1)).update(vo, "1");
        Mockito.verify(bookService, Mockito.only()).update(vo, "1");

    }

    @Test
    public void getUpdateFromBookService_thenReturnResponse201() {
        vo.setId("0");
        when(bookService.update(vo, "0")).thenReturn(vo);

        Response build = bookResource.update(vo, "0");
        assertEquals(202, build.getStatus());


    }



    @Test
    public void getUpdateFromBookService_thenReturnResponse404() {

        assertThrows(WebApplicationException.class,
                () -> {
                    try (Response response = bookResource.update(vo, null)) {

                        assertEquals(400, response.getStatus());
                    }
                });

    }


    // Test DELETE BookResource

    @Test
    public void deleteTimesCalled() {
        vo.setId("1");

        bookResource.delete("1");

        Mockito.verify(bookService, Mockito.times(1)).delete("1");
        Mockito.verify(bookService, Mockito.only()).delete("1");

    }


    @Test
    public void deleteBookGivenValidId() {

        vo.setId("1");
        try (Response response = bookResource.delete("1")) {

            assertNull(response);
        }




    }

    @Test
    public void deleteFromBookService_thenReturnResponse404() {

        assertThrows(WebApplicationException.class,
                () -> bookResource.delete(null));

    }


}


