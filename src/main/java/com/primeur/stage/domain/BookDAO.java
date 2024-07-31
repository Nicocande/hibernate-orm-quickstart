package com.primeur.stage.domain;

import com.primeur.stage.domain.dto.Book;
import com.primeur.stage.port.BookStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookDAO implements BookStorageService {


    @Inject
    EntityManager entityManager;

    @Override
    public Optional<Book> findById(int id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        return entityManager.createNamedQuery("Books.findAll", Book.class)
                .getResultList();

    }

    @Override
    public Book create(Book entity) {
        entityManager.persist(entity);
        return entity;

    }

    @Override
    public Book update(Book book, int id) {

        return entityManager.merge(book);

    }

    @Override
    public void delete(Book book) {

        entityManager.remove(book);
    }


}
