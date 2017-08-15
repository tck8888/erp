package com.tck.entity;

/**
 * Created by admin on 2017/8/15.
 */
public class ConsumeRecord {

    private int id;
    private String consumeType;
    private String consumeContent;
    private double consumePrice;
    private String consumeDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType;
    }

    public String getConsumeContent() {
        return consumeContent;
    }

    public void setConsumeContent(String consumeContent) {
        this.consumeContent = consumeContent;
    }

    public double getConsumePrice() {
        return consumePrice;
    }

    public void setConsumePrice(double consumePrice) {
        this.consumePrice = consumePrice;
    }

    public String getConsumeDate() {
        return consumeDate;
    }

    public void setConsumeDate(String consumeDate) {
        this.consumeDate = consumeDate;
    }
}
