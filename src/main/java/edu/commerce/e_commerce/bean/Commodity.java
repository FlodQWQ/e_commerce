package edu.commerce.e_commerce.bean;

import java.util.List;

public class Commodity {
    private int commodity_no;
    private String commodity_name;
    private Float commodity_price;
    private List<Integer> commodity_sellers;

    public int getCommodity_no() {
        return commodity_no;
    }

    public void setCommodity_no(int commodity_no) {
        this.commodity_no = commodity_no;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public Float getCommodity_price() {
        return commodity_price;
    }

    public void setCommodity_price(Float commodity_price) {
        this.commodity_price = commodity_price;
    }

    public List<Integer> getCommodity_sellers() {
        return commodity_sellers;
    }

    public void setCommodity_sellers(List<Integer> commodity_sellers) {
        this.commodity_sellers = commodity_sellers;
    }

    public Commodity(int commodity_no, String commodity_name, Float commodity_price, List<Integer> commodity_sellers) {
        this.commodity_no = commodity_no;
        this.commodity_name = commodity_name;
        this.commodity_price = commodity_price;
        this.commodity_sellers = commodity_sellers;
    }
}
