package model;

import java.sql.Date;

public class Report {
    private int id;
    private Date writtenDate;
    private Date deliveredDate;
    private long marketValue;
    private short growth;
    private short revoltRate;
    private int foodSupply;
    private String description;
    private int requestId;
    private int emperorId;
    private int consultantId;

    // Constructors, getters, and setters

    // Default constructor
    public Report() {}

    // Constructor with parameters
    public Report(int id, Date writtenDate, Date deliveredDate, long marketValue, short growth, short revoltRate,
                  int foodSupply, String description, int requestId, int emperorId, int consultantId) {
        this.id = id;
        this.writtenDate = writtenDate;
        this.deliveredDate = deliveredDate;
        this.marketValue = marketValue;
        this.growth = growth;
        this.revoltRate = revoltRate;
        this.foodSupply = foodSupply;
        this.description = description;
        this.requestId = requestId;
        this.emperorId = emperorId;
        this.consultantId = consultantId;
    }

    public int getId() {
        return id;
    }

    public Date getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(Date writtenDate) {
        this.writtenDate = writtenDate;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public long getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(long marketValue) {
        this.marketValue = marketValue;
    }

    public short getGrowth() {
        return growth;
    }

    public void setGrowth(short growth) {
        this.growth = growth;
    }

    public short getRevoltRate() {
        return revoltRate;
    }

    public void setRevoltRate(short revoltRate) {
        this.revoltRate = revoltRate;
    }

    public int getFoodSupply() {
        return foodSupply;
    }

    public void setFoodSupply(int foodSupply) {
        this.foodSupply = foodSupply;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getEmperorId() {
        return emperorId;
    }

    public void setEmperorId(int emperorId) {
        this.emperorId = emperorId;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }
}