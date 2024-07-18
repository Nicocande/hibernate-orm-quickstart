package com.primeur.stage.adapter.rest;

import com.primeur.stage.application.vo.VOFruit;
import com.primeur.stage.domain.port.FruitService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@Path("fruits")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FruitResourceNew {

	
	@Inject
	FruitService fruitService;
	
	
	@POST
    @Transactional
    public Response create(VOFruit voFruit) {
        if (voFruit.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        VOFruit voFruitSaved= fruitService.create(voFruit);
        return Response.ok(voFruitSaved).status(201).build();
    }
	
}
