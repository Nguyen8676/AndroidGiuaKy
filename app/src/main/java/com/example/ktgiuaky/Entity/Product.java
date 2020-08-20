package com.example.ktgiuaky.Entity;

public class Product  {
    private Integer Id;
    private String TenSP;
    private Double Cost;

    public Product(int id, String tenSP, double cost) {
        Id = id;
        TenSP = tenSP;
        Cost = cost;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }
}
