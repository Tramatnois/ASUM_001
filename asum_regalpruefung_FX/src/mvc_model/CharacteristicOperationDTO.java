/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author danie
 */
public class CharacteristicOperationDTO extends RecursiveTreeObject<InspectionPlanOperationDTO> {

    private IntegerProperty id;
    private StringProperty description;
    private IntegerProperty position;
    private InspectionPlanOperationDTO inspectionPlan;
    private CharacteristicTypeDTO characteristicType;
    private CharacteristicGroupOperationDTO characteristicgroup;
//    private InspectionResultDTO result;
//    private InspectionOperationCommentDTO comment;

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

    public int getPosition() {
        return position.get();
    }

    public IntegerProperty getPositionProperty() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = new SimpleIntegerProperty();
        this.position.set(position);
    }

    public InspectionPlanOperationDTO getInspectionPlan() {
        return inspectionPlan;
    }

    public void setInspectionPlan(InspectionPlanOperationDTO inspectionPlan) {
        this.inspectionPlan = inspectionPlan;
    }

    public CharacteristicTypeDTO getCharacteristicType() {
        return characteristicType;
    }

    public void setCharacteristicType(CharacteristicTypeDTO characteristicType) {
        this.characteristicType = characteristicType;
    }

    public CharacteristicGroupOperationDTO getCharacteristicgroup() {
        return characteristicgroup;
    }

    public void setCharacteristicgroup(CharacteristicGroupOperationDTO characteristicgroup) {
        this.characteristicgroup = characteristicgroup;
    }

//    public InspectionResultDTO getRestult() {
//        return result;
//    }
//
//    public void setRestult(InspectionResultDTO restult) {
//        this.result = restult;
//    }

//    public InspectionOperationCommentDTO getComment() {
//        return comment;
//    }
//
//    public void setComment(InspectionOperationCommentDTO comment) {
//        this.comment = comment;
//    }

}
