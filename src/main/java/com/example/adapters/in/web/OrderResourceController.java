package com.example.adapters.in.web;

import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import com.example.application.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResourceController {
    @Inject
    OrderService orderService;

    @GET
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @POST
    public Response createOrder(Order order){

        orderService.createOrder(order);
        return Response.status(Response.Status.CREATED).entity(order).build();

    }

    @POST
    @Path("/{orderId}/items")
    public Response addItemsToOrder(@PathParam("orderId")Long orderId, OrderItem orderItem){

        orderService.addItemToOrder(orderId,orderItem);
        return Response.ok().build();
    }

    @PUT
    @Path("/{orderId}/status")
    public Response updateOrderStatus(@PathParam("orderId")Long orderId, String status){

        orderService.updateOrderSatatur(orderId,status);
        return Response.ok().build();
    }

    @GET
    @Path("/{orderId}")
    public Order getOrder(@PathParam("orderId")Long orderId){
        return  orderService.findOrderById(orderId);
    }
}
