/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.Date;
import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author LT Dan
 */
public class InspectionPlanOperationDTO extends RecursiveTreeObject<InspectionPlanOperationDTO> {

    private IntegerProperty id;
    private Date date;
    private String norm;
    private StringProperty description;
    private String storacgeRack;
    private String location;
    private CustomerDTO customer;
    private InspectorDTO inspector;
    private InspectionPlanTemplateDTO inspectionplan;
    private ArrayList<CharacteristicGroupOperationDTO> characteristicGroupList;
    private InspectionPlanOperationStatusDTO inspectionPlanOperationStatus;

    public ArrayList<CharacteristicGroupOperationDTO> getCharacteristicGroupList() {
        return characteristicGroupList;
    }

    public void setCharacteristicGroupList(ArrayList<CharacteristicGroupOperationDTO> characteristicGroupList) {
        this.characteristicGroupList = characteristicGroupList;
    }

    public Integer getId() {
        return id.get();
    }

    public IntegerProperty getIdProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty();
        this.id.set(id);
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

//    public String getDescription() {
//        return description;
//    }
    public String getDescription() {
        return description.get();
    }

    public StringProperty getDescriptionProperty() {
        return description;

    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty();
        this.description.set(description);
    }

    public StringProperty getNameProperty() {
        return this.customer.getNameProperty();
    }

    public InspectionPlanOperationStatusDTO getInspectionPlanOperationStatus() {
        return inspectionPlanOperationStatus;
    }

    public void setInspectionPlanOperationStatus(InspectionPlanOperationStatusDTO inspectionPlanOperationStatus) {
        this.inspectionPlanOperationStatus = inspectionPlanOperationStatus;
    }

}
