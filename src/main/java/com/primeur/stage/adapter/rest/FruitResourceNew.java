package com.primeur.stage.adapter.rest;

import com.primeur.stage.application.vo.VOFruit;
import com.primeur.stage.domain.port.FruitService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.List;

@Path("fruits")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")


public class FruitResourceNew {


    @Inject
    FruitService fruitService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VOFruit> getAll() {
        return fruitService.getAll();
    }


    @POST
    @Transactional
    public Response create(VOFruit voFruit) {
        if (voFruit.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        VOFruit voFruitSaved = fruitService.create(voFruit);
        return Response.ok(voFruitSaved).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(VOFruit voFruit, String id) {
        if (voFruit.getName() == null) {
            throw new WebApplicationException("Fruit Name was not set on request.", 422);
        }

        VOFruit voFruitPut = fruitService.update(voFruit, id);
        return Response.ok(voFruitPut).status(202).build();
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(String id) {
        if (id == null) {
            throw new WebApplicationException("Fruit with id of does not exist.", 404);
        }
        fruitService.delete(id);
        return null;

    }


}





