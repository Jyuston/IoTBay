package uts.isd.model;

import java.io.Serializable;

public class Log implements Serializable {
    public int ID;
    public String performedOn;
    public String action;
    public int userEdited;
    
    public Log() { }
    
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }
    
    public String getPerformedOn() { return performedOn; }
    public void setPerformedOn(String performedOn) { this.performedOn = performedOn; }
    
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public int getUserEdited() { return userEdited; }
    public void setUserEdited(int userEdited) { this.userEdited = userEdited; }
}

