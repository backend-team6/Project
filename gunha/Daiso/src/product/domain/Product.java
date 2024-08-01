package product.domain;


import java.sql.Date;

public class Product {
    private int productId;

    private String productName;

    private String category;

    private int productPrice;

    private String event;

    private int productStock;

    private Date arrivalDate;

    public Product(int productId, String productName, String category, int productPrice, String event, int productStock, java.sql.Date arrivalDate) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.productPrice = productPrice;
        this.event = event;
        this.productStock = productStock;
        this.arrivalDate = arrivalDate;
    }

    public Product(String productName, String category, int productPrice, String event, int productStock, Date arrivalDate) {
        this.productName = productName;
        this.category = category;
        this.productPrice = productPrice;
        this.event = event;
        this.productStock = productStock;
        this.arrivalDate = arrivalDate;
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

    public Date getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public String toString() {
        return "Product(" + productId+ ", " + productName + ", " + category + ", " + productPrice + ", "
                + event + ", " + productStock + ", " + arrivalDate + ")\n";
    }
}