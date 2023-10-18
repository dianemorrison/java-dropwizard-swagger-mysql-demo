package org.kainos.ea.client;

public class OrderDoesNotExistException extends Throwable {
    @Override
    public String getMessage() {
        return  "This order doe not exist. Please buy stuff";
    }
}
