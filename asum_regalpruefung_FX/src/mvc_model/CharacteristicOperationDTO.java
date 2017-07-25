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
    private CharacteristicTypeDAO characteristicType;
    private CharacteristicGroupOperationDTO characteristicgroup;

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

    public CharacteristicTypeDAO getCharacteristicType() {
        return characteristicType;
    }

    public void setCharacteristicType(CharacteristicTypeDAO characteristicType) {
        this.characteristicType = characteristicType;
    }

    public CharacteristicGroupOperationDTO getCharacteristicgroup() {
        return characteristicgroup;
    }

    public void setCharacteristicgroup(CharacteristicGroupOperationDTO characteristicgroup) {
        this.characteristicgroup = characteristicgroup;
    }

    
    
}
