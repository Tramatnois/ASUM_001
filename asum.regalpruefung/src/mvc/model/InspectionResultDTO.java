/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

/**
 *
 * @author LT Dan
 */
public class InspectionResultDTO {
    private int inspectionResult;
    private String result;
    private InspectionOperationDTO inspectionOperation;
    private CharacteristicDAO characteristic;
    private InspectionCommentDTO inspectioncomment;

    public int getInspectionResult() {
        return inspectionResult;
    }

    public void setInspectionResult(int inspectionResult) {
        this.inspectionResult = inspectionResult;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public InspectionOperationDTO getInspectionOperation() {
        return inspectionOperation;
    }

    public void setInspectionOperation(InspectionOperationDTO inspectionOperation) {
        this.inspectionOperation = inspectionOperation;
    }

    public CharacteristicDAO getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(CharacteristicDAO characteristic) {
        this.characteristic = characteristic;
    }

    public InspectionCommentDTO getInspectioncomment() {
        return inspectioncomment;
    }

    public void setInspectioncomment(InspectionCommentDTO inspectioncomment) {
        this.inspectioncomment = inspectioncomment;
    }
    
    
    
}
