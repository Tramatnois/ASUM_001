/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

/**
 *
 * @author danie
 */
class CharacteristicOperationDTO {
    private int id;
    private String description;
    private int position;
    private InspectionPlanOperationDTO inspectionPlan;
    private CharacteristicTypeDTO characteristicType;
    private CharacteristicGroupOperationDTO characteristicgroup;
    private InspectionResultDTO result;
    private InspectionOperationCommentDTO comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public InspectionResultDTO getRestult() {
        return result;
    }

    public void setRestult(InspectionResultDTO restult) {
        this.result = restult;
    }

    public InspectionOperationCommentDTO getComment() {
        return comment;
    }

    public void setComment(InspectionOperationCommentDTO comment) {
        this.comment = comment;
    }

    
    
}
