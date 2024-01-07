package model;

import java.sql.Date;

public class Request {
    private int id;
    private Date writtenDate;
    private Date deliveredDate;
    private int period;
    private int regionId;
    private int emperorId;
    private int consultantId;

    // Constructors, getters, and setters

    // Default constructor
    public Request() {}

    // Constructor with parameters
    public Request(int id, Date writtenDate, Date deliveredDate, int period, int regionId, int emperorId, int consultantId) {
        this.id = id;
        this.writtenDate = writtenDate;
        this.deliveredDate = deliveredDate;
        this.period = period;
        this.regionId = regionId;
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
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
