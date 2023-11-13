package com.constellation.backend.catalogservice;

import java.sql.Timestamp;
// Entity Class
public class CatalogItem {
    private int id;
    private String sellerName;
    private int sellerId;
    private String itemName;
    private String itemDescription;
    private boolean isDutch;
    private int daysToShip;
    private int initialPrice;
    private Timestamp auctionEnd;
    private boolean available;
    private double highestBid;
    //Constructors
    public CatalogItem() {
        // Default constructor
    }

    public CatalogItem(int id, String sellerName, String itemName, String itemDescription,
                       boolean isDutch, int daysToShip, int initialPrice,
                       Timestamp auctionEnd, boolean available, double highestBid, int sellerId) {
        this.id = id;
        this.sellerName = sellerName;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.isDutch = isDutch;
        this.daysToShip = daysToShip;
        this.initialPrice = initialPrice;
        this.auctionEnd = auctionEnd;
        this.available = available;
        this.highestBid = highestBid;
        this.sellerId = sellerId;
    }
    // Getters and Setters
    public int getSellerId() { return sellerId; }
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public boolean isDutch() {
        return isDutch;
    }

    public void setDutch(boolean isDutch) {
        isDutch = this.isDutch;
    }

    public int getdaysToShip() {
        return daysToShip;
    }

    public void setdaysToShip(int daysToShip) {
        this.daysToShip = daysToShip;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(int initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Timestamp getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(Timestamp auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }
    public void setSellerName(String sellerName) {this.sellerName = sellerName;}
    public String getSellerName() { return this.sellerName; }
    public void setHighestBid(double highestBid) { this.highestBid = highestBid; }
    public double getHighestBid() { return this.highestBid; }
}