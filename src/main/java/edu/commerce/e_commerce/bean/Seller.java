package edu.commerce.e_commerce.bean;

import java.util.ArrayList;
import java.util.List;

public class Seller {
    private int seller_no;
    private String seller_name;
    private List<Integer> seller_commodities;

    public int getSeller_no() {
        return seller_no;
    }

    public void setSeller_no(int seller_no) {
        this.seller_no = seller_no;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public List<Integer> getSeller_commodities() {
        return seller_commodities;
    }

    public void setSeller_commodities(List<Integer> seller_commodities) {
        this.seller_commodities = seller_commodities;
    }


    public Seller(int seller_no, String seller_name, List<Integer> seller_commodities) {
        this.seller_no = seller_no;
        this.seller_name = seller_name;
        this.seller_commodities = seller_commodities;
    }
    
}
