package org.kainos.ea.api;

import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.FailedToCreateProductException;
import org.kainos.ea.client.FailedToGetProductException;
//import org.kainos.ea.client.InvalidProductException;
import org.kainos.ea.client.ProductDoesNotExistException;
import org.kainos.ea.db.OrderDao;
import org.kainos.ea.db.ProductDao;
import org.kainos.ea.db.ProductValidator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    //create productdao and then call method
    private ProductDao productDao = new ProductDao();
    private ProductValidator productValidator = new ProductValidator();

    public List<Product> getAllProducts() throws FailedToGetProductException {

        List<Product> productList = productDao.getAllProducts();
        try {
            //Product product = productList.get(1000);
            return productList;
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());

        }throw new FailedToGetProductException();


    }

    public  Product getProductById(int id) throws  FailedToGetProductException, ProductDoesNotExistException{
        try{
            Product product = productDao.getProductById(id);

            if (product == null) {
                throw new ProductDoesNotExistException();
            }
            return product;
        }catch (SQLException e) {
            System.err.println((e.getMessage()));

            throw new FailedToGetProductException();
        }
    }


    public int createProduct(ProductRequest product) throws FailedToCreateProductException {
        try {

            String validation = productValidator.isValidProduct(product);
            if (validation != null) {
//                throw new InvalidProductException(validation);
            }
            int id = productDao.createProduct(product);

            if (id == -1) {
                throw new FailedToCreateProductException();
            }

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateProductException();
        }
    }
}



