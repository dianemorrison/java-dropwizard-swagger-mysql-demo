package org.kainos.ea.api;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.Product;
import org.kainos.ea.client.FailedToGetOrderException;
import org.kainos.ea.client.FailedToGetProductException;
import org.kainos.ea.client.OrderDoesNotExistException;
import org.kainos.ea.client.ProductDoesNotExistException;
import org.kainos.ea.db.OrderDao;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
 private  OrderDao orderDao = new OrderDao();
 public List<Order> getAllOrders() throws SQLException, FailedToGetOrderException {
try {
       List<Order> orderList = orderDao.getAllOrders();

       Map<Integer, Long> orderMap = orderList
             .stream()
             .collect(Collectors.groupingBy(Order::getCustomerId,Collectors.counting()));
     System.out.println("Customer ID with most orders: " + Collections.max(orderMap.entrySet(), Map.Entry.comparingByValue()).getKey());
     System.out.println("Customer ID with least orders: " + Collections.min(orderMap.entrySet(), Map.Entry.comparingByValue()).getKey());

     return orderList;
    } catch (SQLException e) {
    System.err.println(e.getMessage());
    }
    throw new FailedToGetOrderException();
}
    public Order getOrderById(int id) throws FailedToGetOrderException, OrderDoesNotExistException {
        try {
            Order order = orderDao.getOrderById(id);

            if (order == null) {
                throw new OrderDoesNotExistException();
            }
            return getOrderById(id);

        }catch (SQLException e){ throw new  OrderDoesNotExistException();

        }

    }
 }

