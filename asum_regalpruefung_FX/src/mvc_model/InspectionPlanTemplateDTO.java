/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author LT Dan
 */
public class InspectionPlanTemplateDTO extends RecursiveTreeObject<InspectionPlanTemplateDTO> {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty norm = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private IntegerProperty active = new SimpleIntegerProperty();
    private ArrayList<CharacteristicGroupTemplateDTO> characteristicGroupList;
    //private ArrayList<Object> characteristicList;

    public Integer getId() {
        return id.get();
    }

    public final IntegerProperty getIdProperty() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id.set(id);
    }

    public final String getNorm() {
        return norm.get();
    }

    public final StringProperty getNormProperty() {
        return norm;
    }

    public final void setNorm(final String norm) {
        this.norm.set(norm);
    }

    public final String getDescription() {
        return description.get();
    }

    public final StringProperty getDescriptionProperty() {
        return description;
    }

    public final void setDescription(final String description) {
        this.description.set(description);
    }
    
    public final Integer getActive() {
        return active.get();
    }

    public final Boolean getActiveBoolean() {
        Boolean boolActive = false;
        switch (this.active.get()) {
            case 0:
                boolActive = false;
                break;
            default:
                boolActive = true;
                break;
        }        
        return boolActive;
    }

    public final IntegerProperty getActiveProperty() {
        return active;
    }

    public final void setActive(final Integer active) {
        this.active.set(active);
    }

    public ArrayList<CharacteristicGroupTemplateDTO> getCharacteristicGroupList() {
        return characteristicGroupList;
    }

    public void setCharacteristicGroupList(ArrayList<CharacteristicGroupTemplateDTO> characteristicGroupList) {
        this.characteristicGroupList = characteristicGroupList;
    }
}
