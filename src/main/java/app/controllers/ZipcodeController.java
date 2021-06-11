package app.controllers;

import app.entities.Address;
import app.exceptions.InvalidSearchCepException;
import app.services.AddressService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/search")
public class ZipcodeController {
    @Inject
    private AddressService service;

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response findByZipcode(@QueryParam("zipcode") String zipcode) {
        try {
            Address address = service.verify(zipcode);
            return Response.ok(address).build();
        } catch (InvalidSearchCepException e) {
            return Response.serverError().build();
        }
    }
}