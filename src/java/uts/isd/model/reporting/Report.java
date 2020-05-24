package uts.isd.model.reporting;

import java.io.Serializable;
import java.util.ArrayList;

public class Report implements Serializable {
    private String name;
    private String description;
    private String startDate;
    private String endDate;

    public Report (String reportName, String reportDescription, String startDate, String endDate) {
        this.name = reportName;
        this.description = reportDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setReportName(String reportName) {
        this.name = reportName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String reportDescription) {
        this.description = reportDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setReportStartDate(String reportStartDate) {
        this.startDate = reportStartDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setReportEndDate(String reportEndDate) {
        this.endDate = reportEndDate;
    }

    public void printReportInfo() {
        System.out.println("Report Name: " + name);
        System.out.println("Report Description: " + description);
        System.out.println("Report Start Date: " + startDate);
        System.out.println("Report End Date: " + endDate);
        System.out.println("-----------");
    }
}