/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

/**
 *
 * @author LT Dan
 */
public class InspectionResultDTO {
    private int inspectionResult;
    private String result;
    private InspectionPlanOperationDTO inspectionOperation;
    private CharacteristicOperationDAO characteristic;
    private InspectionOperationCommentDTO inspectioncomment;

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

    public InspectionPlanOperationDTO getInspectionOperation() {
        return inspectionOperation;
    }

    public void setInspectionOperation(InspectionPlanOperationDTO inspectionOperation) {
        this.inspectionOperation = inspectionOperation;
    }

 

    public InspectionOperationCommentDTO getInspectioncomment() {
        return inspectioncomment;
    }

    public void setInspectioncomment(InspectionOperationCommentDTO inspectioncomment) {
        this.inspectioncomment = inspectioncomment;
    }

    public CharacteristicOperationDAO getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(CharacteristicOperationDAO characteristic) {
        this.characteristic = characteristic;
    }
    
    
    
}
