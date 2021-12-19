package com.appliance.homeMaintenanceApp;

import java.io.Serializable;

public class Appliance implements Serializable {
    private int id;
    private String applianceName;
    private String serviceFrequency;
    private String lastServiceDate;
    private String nextServiceDate;
    private String applianceLink;
    private String description;


    //no arg constructor
    public Appliance() {

    }

    //constructor with 7 args
    public Appliance(int id,
                     String applianceName,
                     String serviceFrequency,
                     String lastServiceDate,
                     String nextServiceDate,
                     String applianceLink,
                     String description) {
        super();
        this.id = id;
        this.applianceName = applianceName;
        this.serviceFrequency = serviceFrequency;
        this.lastServiceDate = lastServiceDate;
        this.nextServiceDate = nextServiceDate;
        this.applianceLink = applianceLink;
        this.description = description;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public String getServiceFrequency() {
        return serviceFrequency;
    }

    public void setServiceFrequency(String serviceFrequency) {
        this.serviceFrequency = serviceFrequency;
    }

    public String getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(String lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public String getNextServiceDate() {
        return nextServiceDate;
    }

    public void setNextServiceDate(String nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
    }

    public String getApplianceLink() {
        return applianceLink;
    }

    public void setApplianceLink(String applianceLink) {
        this.applianceLink = applianceLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
