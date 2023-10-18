package org.kainos.ea.db;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.Product;
import org.kainos.ea.client.FailedToGetOrderException;
import org.kainos.ea.client.FailedToGetProductException;
import org.kainos.ea.client.OrderDoesNotExistException;
import org.kainos.ea.client.ProductDoesNotExistException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OrderDao {

    private final DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<Order> getAllOrders() throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT OrderID, CustomerID, OrderDate FROM `Order`;");

        List<Order> orderList = new ArrayList<>();

        while (rs.next()) {
            Order order = new Order(
                    rs.getInt("OrderID"),
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            );

            orderList.add(order);
        }
        return orderList;

    }


    public static Order getOrderById(int id) throws SQLException {

        Connection c = DatabaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT OrderID, CustomerID, OrderDate FROM `Order`;");

        while (rs.next()) {
            Order order = new Order(
                    rs.getInt("OrderID"),
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            );
            return order;
        }
        return null;

        }
    }
//st.setDate(2,new Date(order.getOrderDate().getTime());






