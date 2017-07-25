/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import java.sql.Date;

/**
 *
 * @author LT Dan
 */
public class InspectionPlanOperationDTO {
    private int id;
    private Date date;
    private String norm;
    private String description;
    private String storacgeRack;
    private String location;
    private CustomerDAO customer;
    private InspectorDAO inspector;
    private InspectionPlanTemplateDAO inspectionplan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStoracgeRack() {
        return storacgeRack;
    }

    public void setStoracgeRack(String storacgeRack) {
        this.storacgeRack = storacgeRack;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String locatioN) {
        this.location = locatioN;
    }

    public CustomerDAO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDAO customer) {
        this.customer = customer;
    }

    public InspectorDAO getInspector() {
        return inspector;
    }

    public void setInspector(InspectorDAO inspector) {
        this.inspector = inspector;
    }

    public InspectionPlanTemplateDAO getInspectionplan() {
        return inspectionplan;
    }

    public void setInspectionplan(InspectionPlanTemplateDAO inspectionplan) {
        this.inspectionplan = inspectionplan;
    }

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
