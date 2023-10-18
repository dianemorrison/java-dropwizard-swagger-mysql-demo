package org.kainos.ea.resources;

import org.kainos.ea.api.OrderService;
import org.kainos.ea.cli.Order;
import org.kainos.ea.client.FailedToGetOrderException;
import org.kainos.ea.client.FailedToGetProductException;
import org.kainos.ea.client.OrderDoesNotExistException;
import org.kainos.ea.client.ProductDoesNotExistException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/api")

public class OrderController {
    private OrderService orderService = new OrderService();

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders(){
        try {
            return Response.ok(orderService.getAllOrders()).build();
        } catch (FailedToGetOrderException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @GET
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id")int id) {
        try {
            return Response.ok(orderService.getOrderById(id)).build();

        } catch (FailedToGetOrderException e) {
            return Response.serverError().build();
        } catch (OrderDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

}
