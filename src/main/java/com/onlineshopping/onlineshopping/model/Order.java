package com.onlineshopping.onlineshopping.model;

public class Order {
    private Long orderID;

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getCatergoryID() {
        return catergoryID;
    }

    public void setCatergoryID(Long catergoryID) {
        this.catergoryID = catergoryID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    private Long catergoryID;
    private Long productID;
    private String orderStatus;
}
