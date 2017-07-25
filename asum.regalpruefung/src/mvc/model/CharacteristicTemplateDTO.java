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
class CharacteristicTemplateDTO {
    private int id;
    private String description;
    private int position;
    private InspectionPlanTemplateDTO inspectionPlan;
    private CharacteristicTypeDAO characteristicType;
    private CharacteristicGroupTemplateDTO characteristicgroup;

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

    public InspectionPlanTemplateDTO getInspectionPlan() {
        return inspectionPlan;
    }

    public void setInspectionPlan(InspectionPlanTemplateDTO inspectionPlan) {
        this.inspectionPlan = inspectionPlan;
    }

    public CharacteristicTypeDAO getCharacteristicType() {
        return characteristicType;
    }

    public void setCharacteristicType(CharacteristicTypeDAO characteristicType) {
        this.characteristicType = characteristicType;
    }

    public CharacteristicGroupTemplateDTO getCharacteristicgroup() {
        return characteristicgroup;
    }

    public void setCharacteristicgroup(CharacteristicGroupTemplateDTO characteristicgroup) {
        this.characteristicgroup = characteristicgroup;
    }
    
    
    
    
}
