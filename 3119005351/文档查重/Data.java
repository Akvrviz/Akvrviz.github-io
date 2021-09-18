package com.试验田.文档查重;

public class Data {
    private double  sameQuantity = 0;
    private double total = 0;
    private double rate = 0;

    public void setSameQuantity(double sameQuantity) {
        this.sameQuantity = sameQuantity;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTotal() {
        return total;
    }

    public double getRate() {
        return rate;
    }
    public double getSameQuantity() {
        return sameQuantity;
    }
}
