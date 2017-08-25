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
 * @author LT Dan
 */
public class InspectionResultDTO extends RecursiveTreeObject<InspectionResultDTO> {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty result = new SimpleStringProperty();
    private InspectionPlanOperationDTO inspectionOperation;
    private CharacteristicOperationDTO characteristic;
//    private InspectionOperationCommentDTO inspectioncomment;

    public final int getId() {
        return id.get();
    }

    public final IntegerProperty getIdProperty() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id.set(id);
    }

    public final String getResult() {
        return result.get();
    }

    public final StringProperty getResultProperty() {
        return result;
    }

    public final void setResult(final String result) {
        this.result.set(result);
    }

    public InspectionPlanOperationDTO getInspectionOperation() {
        return inspectionOperation;
    }

    public void setInspectionOperation(InspectionPlanOperationDTO inspectionOperation) {
        this.inspectionOperation = inspectionOperation;
    }

//    public InspectionOperationCommentDTO getInspectioncomment() {
//        return inspectioncomment;
//    }
//
//    public void setInspectioncomment(InspectionOperationCommentDTO inspectioncomment) {
//        this.inspectioncomment = inspectioncomment;
//    }

    public CharacteristicOperationDTO getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(CharacteristicOperationDTO characteristic) {
        this.characteristic = characteristic;
    }

}
