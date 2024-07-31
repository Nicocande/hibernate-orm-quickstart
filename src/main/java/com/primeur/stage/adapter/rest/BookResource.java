package com.primeur.stage.adapter.rest;

import com.primeur.stage.application.vo.BookVO;
import com.primeur.stage.port.BookService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("books")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")


public class BookResource {


    @Inject
    BookService bookService;


    @GET
    public List<BookVO> getAll() {
        return bookService.getAll();

    }

    @GET
    @Path("{id}")
    public BookVO getById(String id) {

        return bookService.findById(id)
                .orElseThrow(() -> new WebApplicationException("Book with id of does not exist.", 404));


    }

    @POST
    @Transactional
    public Response create(BookVO bookvo) {
        if (bookvo.getId() == null) {
            throw new WebApplicationException("Id was invalidly set on request", 400);
        }
        if(bookvo.getTitle() == null){
            throw new NullPointException("should not create Book when title is null");
        }
        if (bookvo.getAuthor() == null){
            throw new NullPointException("should not create Book when author is null");
        }
        if(bookvo.getDescription() == null){
            throw new NullPointException("should not create Book when description is null");
        }
        BookVO bookSaved = bookService.create(bookvo);
        return Response.ok(bookSaved).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(BookVO bookvo, String id) {
        if (id==null){
            throw new WebApplicationException("Method called with null Id", 400);
        }

        BookVO voBookPutted = bookService.update(bookvo, id);
        return Response.ok(voBookPutted).status(202).build();
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(String id) {
        if (id == null) {
            throw new WebApplicationException("Method called with null Id", 400);
        }
        bookService.delete(id);
        return null;

    }


}





