/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import java.util.ArrayList;

/**
 *
 * @author LT Dan
 */
public class InspectionPlanTemplateDTO {

    private int id;
    private String norm;
    private String description;
    private ArrayList<CharacteristicGroupTemplateDTO> characteristicGroupList;
    //private ArrayList<Object> characteristicList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<CharacteristicGroupTemplateDTO> getCharacteristicGroupList() {
        return characteristicGroupList;
    }

    public void setCharacteristicGroupList(ArrayList<CharacteristicGroupTemplateDTO> characteristicGroupList) {
        this.characteristicGroupList = characteristicGroupList;
    }
    }

