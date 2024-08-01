package Daiso;

import java.util.Date;

class Product {
    private int productId;

    private String productName;

    private String category;

    private int productPrice;

    private String event;

    private int productStock;

    private Date arrivalDate;


    public Product(String productName, String category, int price, String event, int stock, java.sql.Date date) {
        this.productName = productName;
        this.category = category;
        this.productPrice = price;
        this.event = event;
        this.productStock = stock;
        this.arrivalDate = date;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getEvent() {
        return event;
    }

    public int getProductStock() {
        return productStock;
    }

    public java.sql.Date getArrivalDate() {
        return (java.sql.Date) arrivalDate;
    }
}