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
class CharacteristicDTO {
    private int characteristic;
    private String description;
    private int position;
    private InspectionPlanDTO inspectionPlan;
    private CharacteristicTypeDAO characteristicType;
    private CharacteristcGroupDTO characteristicgroup;

    public int getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(int characteristic) {
        this.characteristic = characteristic;
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

    public InspectionPlanDTO getInspectionPlan() {
        return inspectionPlan;
    }

    public void setInspectionPlan(InspectionPlanDTO inspectionPlan) {
        this.inspectionPlan = inspectionPlan;
    }

    public CharacteristicTypeDAO getCharacteristicType() {
        return characteristicType;
    }

    public void setCharacteristicType(CharacteristicTypeDAO characteristicType) {
        this.characteristicType = characteristicType;
    }

    public CharacteristcGroupDTO getCharacteristicgroup() {
        return characteristicgroup;
    }

    public void setCharacteristicgroup(CharacteristcGroupDTO characteristicgroup) {
        this.characteristicgroup = characteristicgroup;
    }
    
    
    
    
}
