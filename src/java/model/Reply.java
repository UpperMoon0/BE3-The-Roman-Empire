package model;

import java.sql.Date;

public class Reply {
    private int id;
    private Date writtenDate;
    private Date deliveredDate;
    private String content;
    private int reportId;
    private int emperorId;
    private int consultantId;

    // Constructors, getters, and setters

    // Default constructor
    public Reply() {}

    // Constructor with parameters
    public Reply(int id, Date writtenDate, Date deliveredDate, String content, int reportId, int emperorId, int consultantId) {
        this.id = id;
        this.writtenDate = writtenDate;
        this.deliveredDate = deliveredDate;
        this.content = content;
        this.reportId = reportId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
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
