/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author LT Dan
 */
public class InspectionPlanOperationStatusDTO {

    private IntegerProperty id;
    private StringProperty description;

    public int getId() {
        return id.get();
    }

    public IntegerProperty getIdProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty();
        this.id.set(id);
    }

    public String getDescription() {
        return description.get();
    }
    public StringProperty getDescriptionProperty(){
        return description;
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty();
        this.description.set(description);
    }

}
