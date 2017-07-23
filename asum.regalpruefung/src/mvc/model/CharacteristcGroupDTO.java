/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.util.ArrayList;

/**
 *
 * @author LT Dan
 */
class CharacteristcGroupDTO {
    private int idcharacteristicgroup;
    private int groupnumber;
    private String description;
    private InspectionPlanDTO inspectionPlanDTO;
    private ArrayList<CharacteristicDTO> characteristicList;

    public int getIdcharacteristicgroup() {
        return idcharacteristicgroup;
    }

    public void setIdcharacteristicgroup(int idcharacteristicgroup) {
        this.idcharacteristicgroup = idcharacteristicgroup;
    }

    public int getGroupnumber() {
        return groupnumber;
    }

    public void setGroupnumber(int groupnumber) {
        this.groupnumber = groupnumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InspectionPlanDTO getInspectionPlanDTO() {
        return inspectionPlanDTO;
    }

    public void setInspectionPlanDTO(InspectionPlanDTO inspectionPlanDTO) {
        this.inspectionPlanDTO = inspectionPlanDTO;
    }

    public ArrayList<CharacteristicDTO> getCharacteristicList() {
        return characteristicList;
    }

    public void setCharacteristicList(ArrayList<CharacteristicDTO> characteristicList) {
        this.characteristicList = characteristicList;
    }
    
}
