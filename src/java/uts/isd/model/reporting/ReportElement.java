package uts.isd.model;

import java.io.Serializable;

public class ReportElement implements Serializable {
    private String elementName;
    private String elementDescription;

    public ReportElement(String elementName, String elementDescription) {
        this.elementName = elementName;
        this.elementDescription = elementDescription;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementDescription() {
        return elementDescription;
    }

    public void setElementDescription(String elementDescription) {
        this.elementDescription = elementDescription;
    }
}