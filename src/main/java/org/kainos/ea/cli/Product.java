package org.kainos.ea.cli;

public class Product {

    private int ProductId;
    private String Name;
    private String Description;
    private double Price;
    private String Category;

    public Product(int productId, String name, String description, double price, String category) {
        ProductId = productId;
        Name = name;
        Description = description;
        Price = price;
        Category = category;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
