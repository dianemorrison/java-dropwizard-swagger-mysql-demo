package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.cli.Product;
import org.kainos.ea.api.ProductService;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.FailedToCreateProductException;
import org.kainos.ea.client.FailedToGetProductException;
import org.kainos.ea.client.ProductDoesNotExistException;
//import sun.security.krb5.internal.CredentialsUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api("Engineering Academy Dropwizard Product API")
@Path("/api")

public class ProductController {
    private ProductService productService = new ProductService();

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() {
        try {
            return Response.ok(productService.getAllProducts()).build();
        } catch (FailedToGetProductException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsById(@PathParam("id") int id) {
        try {
            return Response.ok(productService.getProductById(id)).build();

        } catch (FailedToGetProductException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();

        } catch (ProductDoesNotExistException e) {
            System.err.println(e.getMessage());
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductRequest product) {
        try {
            return Response.ok(productService.createProduct(product)).build();
        } catch (FailedToCreateProductException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}