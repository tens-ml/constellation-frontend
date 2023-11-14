package com.constellation.backend.bidservice;

public class BidService {
    private final BidDAO bidDAO = new BidDAO();
    public BidService() {
        // Default constructor
    }
    public void test() {
        System.out.println("test");
    }
    public Bid getHighestBid(int itemId) {
        return bidDAO.getHighestBid(itemId);
    }
    public void createBid(Bid bid) {
        bidDAO.create(bid);
    }
}
