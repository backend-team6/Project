package daiso;

import java.sql.Date;

class Product {
    private int productId;

    private String productName;

    private String category;

    private int productPrice;

    private String event;

    private int productStock;

    private Date arrivalDate;

    public Product(String productName, String category, int price, String event, int stock, java.sql.Date date) {
        this.productName=productName;
        this.category=category;
        this.productPrice=price;
        this.event=event;
        this.productStock=stock;
        this.arrivalDate=date;
    }

    public Product(int productId, String productName, String category, int price, String event, int stock, java.sql.Date date) {
        this.productId=productId;
        this.productName=productName;
        this.category=category;
        this.productPrice=price;
        this.event=event;
        this.productStock=stock;
        this.arrivalDate=date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", productPrice=" + productPrice +
                ", event='" + event + '\'' +
                ", productStock=" + productStock +
                ", arrivalDate=" + arrivalDate +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public java.sql.Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}