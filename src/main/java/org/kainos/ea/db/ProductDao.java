package org.kainos.ea.db;


import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.kainos.ea.db.DatabaseConnector.getConnection;


public class ProductDao {
    private static Connection conn;
    private final DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<Product> getAllProducts() {
        try (Connection c = getConnection()) {
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT ProductID,Name, Description, Price, Category FROM Product;");

            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        rs.getString("Category")

                );

                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            return null;
        }
    }

    public Product getProductById(int id) throws SQLException {
        Connection c = getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT ProductID,Name, Description, Price, Category FROM Product where ProductID=" + id);

        while (rs.next()) {
            return new Product(
                    rs.getInt("ProductID"),
                    rs.getString("Name"),
                    rs.getString("Description"),
                    rs.getDouble("Price"),
                    rs.getString("Category")

            );

        }
        return null;
    }

    public int createProduct(ProductRequest product) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Product (Name, Description, Price, Category ) VALUES (?,?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, product.getName());
        st.setString(2, product.getDescription());
        st.setDouble(3, product.getPrice());
        st.setString(4, product.getCategory());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;

    }
}
