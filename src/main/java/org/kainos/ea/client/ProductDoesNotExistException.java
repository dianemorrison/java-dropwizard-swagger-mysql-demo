package org.kainos.ea.client;

public class ProductDoesNotExistException extends Throwable {

    @Override
    public String getMessage() {
        return "Failed to get products from the database";
    }
}
