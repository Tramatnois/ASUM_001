/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import java.sql.Date;
import java.util.ArrayList;

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
    private CustomerDTO customer;
    private InspectorDTO inspector;
    private InspectionPlanTemplateDTO inspectionplan;
    private ArrayList<CharacteristicGroupOperationDTO> characteristicGroupList;
//    private int inspectionplan_operation_status_id;

    public ArrayList<CharacteristicGroupOperationDTO> getCharacteristicGroupList() {
        return characteristicGroupList;
    }

    public void setCharacteristicGroupList(ArrayList<CharacteristicGroupOperationDTO> characteristicGroupList) {
        this.characteristicGroupList = characteristicGroupList;
    }

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

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public InspectorDTO getInspector() {
        return inspector;
    }

    public void setInspector(InspectorDTO inspector) {
        this.inspector = inspector;
    }


    public InspectionPlanTemplateDTO getInspectionplan() {
        return inspectionplan;
    }

    public void setInspectionplan(InspectionPlanTemplateDTO inspectionplan) {
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
