/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.util.ArrayList;

/**
 *
 * @author danie
 */
public class CharacteristicGroupOperationDTO {
    private int id;
    private int groupnumber;
    private String description;
    private InspectionPlanOperationDTO inspectionPlanDTO;
    private ArrayList<CharacteristicOperationDTO> characteristicList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public InspectionPlanOperationDTO getInspectionPlanDTO() {
        return inspectionPlanDTO;
    }

    public void setInspectionPlanDTO(InspectionPlanOperationDTO inspectionPlanDTO) {
        this.inspectionPlanDTO = inspectionPlanDTO;
    }

    public ArrayList<CharacteristicOperationDTO> getCharacteristicList() {
        return characteristicList;
    }

    public void setCharacteristicList(ArrayList<CharacteristicOperationDTO> characteristicList) {
        this.characteristicList = characteristicList;
    }



}
