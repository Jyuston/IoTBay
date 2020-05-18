package uts.isd.model.reporting;

import java.io.Serializable;
import java.util.ArrayList;

public class Report implements Serializable {
    private String reportName;
    private String reportDescription;
    private ArrayList<ReportElement> reportElements = new ArrayList<>();

    public Report (String reportName, String reportDescription) {
        this.reportName = reportName;
        this.reportDescription = reportDescription;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public void addReportElement(ReportElement element) {
        reportElements.add(element);
    }
}