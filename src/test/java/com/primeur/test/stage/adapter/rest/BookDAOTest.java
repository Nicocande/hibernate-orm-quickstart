package com.primeur.test.stage.adapter.rest;

import com.primeur.stage.domain.BookDAO;
import com.primeur.stage.domain.dto.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;


import java.util.Optional;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static io.smallrye.common.constraint.Assert.assertTrue;


@ExtendWith(MockitoExtension.class)
class BookDAOTest {


    @InjectMocks
    BookDAO dao;

    @Mock
    TypedQuery<Book> typedQuery;

    @Mock
    EntityManager entityManager;

    Book book;
    private VerificationMode times;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1);
        book.setTitle("FirstMovie");

    }

    @Test
    public void findById() {

        Mockito.when(entityManager.find(Book.class, 1)).thenReturn(book);

        dao.findById(1);

        Mockito.verify(entityManager, Mockito.times(1)).find(Book.class, 1);
        Mockito.verify(entityManager, Mockito.only()).find(Book.class, 1);

    }
    @Test
    public void findByIdGivenAbsentId() {

        Mockito.when(entityManager.find(Book.class, 1)).thenReturn(null);

        Optional<Book> result = dao.findById(1);

        assertNotNull(result);

        assertTrue(result.isEmpty());



    }

    @Test
    public void getAll() {
        Mockito.when(entityManager.createNamedQuery("Books.findAll", Book.class)).thenReturn(typedQuery);

        dao.getAll();

        Mockito.verify(entityManager, Mockito.times(1)).createNamedQuery("Books.findAll", Book.class);
        Mockito.verify(entityManager, Mockito.only()).createNamedQuery("Books.findAll", Book.class);

    }

    @Test
    public void create() {


        dao.create(book);

        Mockito.verify(entityManager, Mockito.times(1)).persist(book);
        Mockito.verify(entityManager, Mockito.only()).persist(Mockito.any());


    }


    @Test
    public void update(){

        dao.update(book,1);

        Mockito.verify(entityManager, Mockito.times(1)).merge(book);
        Mockito.verify(entityManager, Mockito.only()).merge(book);


    }

    @Test
    public void delete(){

        dao.delete(book);
        Mockito.verify(entityManager, Mockito.times(1)).remove(book);
        Mockito.verify(entityManager, Mockito.only()).remove(book);
    }








}

