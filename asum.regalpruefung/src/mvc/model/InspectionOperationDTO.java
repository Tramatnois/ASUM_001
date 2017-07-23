/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.sql.Date;

/**
 *
 * @author LT Dan
 */
public class InspectionOperationDTO {
    private int idinspectionoperation;
    private Date date;
    private String storacgeRack;
    private String locatioN;
    private CustomerDAO customer;
    private InspectorDAO inspector;
    private InspectionPlanDAO inspectionplan;

    public int getIdinspectionoperation() {
        return idinspectionoperation;
    }

    public void setIdinspectionoperation(int idinspectionoperation) {
        this.idinspectionoperation = idinspectionoperation;
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

    public String getLocatioN() {
        return locatioN;
    }

    public void setLocatioN(String locatioN) {
        this.locatioN = locatioN;
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

    public InspectionPlanDAO getInspectionplan() {
        return inspectionplan;
    }

    public void setInspectionplan(InspectionPlanDAO inspectionplan) {
        this.inspectionplan = inspectionplan;
    }
    
}
