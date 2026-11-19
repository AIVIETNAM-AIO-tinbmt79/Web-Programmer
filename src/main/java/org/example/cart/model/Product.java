package org.example.cart.model;
import java.io.Serializable;
public class Product implements Serializable {
    private String productCode;
    private String description;
    private double price;

    public Product() {}

    public Product(String productCode, String description, double price) {
        this.productCode = productCode;
        this.description = description;
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
}
