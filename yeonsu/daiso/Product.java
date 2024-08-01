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

    public Product(String productName, String category, int price, String event, int stock, Date date) {
        this.productName = productName;
        this.category = category;
        this.productPrice = price;
        this.event = event;
        this.productStock = stock;
        this.arrivalDate = date;
    }

    public Product(int productId, String productName, String category, int productPrice, String event, int productStock, Date arrivalDate) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.productPrice = productPrice;
        this.event = event;
        this.productStock = productStock;
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return productId + "  " + productName + "  " + category + "  " + productPrice + "  " + event + "  " + productStock + "  " + arrivalDate;
//        return "Product{" +
//                "productId=" + productId +
//                ", productName='" + productName + '\'' +
//                ", category='" + category + '\'' +
//                ", productPrice=" + productPrice +
//                ", event='" + event + '\'' +
//                ", productStock=" + productStock +
//                ", arrivalDate=" + arrivalDate +
//                '}';
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
}
