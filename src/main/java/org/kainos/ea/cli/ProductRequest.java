package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequest {
    private String name;
    private String description;
    private double price;
    private String category;

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {return name;}

    @JsonCreator
    public ProductRequest(@JsonProperty("name") String name,
        @JsonProperty("description") String description,
    @JsonProperty("price") double price) {
    this.name = name;
    this.description = description;
    this.price = price;
    }


}
