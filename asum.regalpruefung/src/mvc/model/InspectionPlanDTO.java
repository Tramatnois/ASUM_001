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
public class InspectionPlanDTO {

    private int idinspectionplan;
    private String norm;
    private String description;
    private ArrayList<CharacteristcGroupDTO> characteristicGroupList;
    //private ArrayList<Object> characteristicList;

    public int getIdinspectionplan() {
        return idinspectionplan;
    }

    public void setIdinspectionplan(int idinspectionplan) {
        this.idinspectionplan = idinspectionplan;
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

    public ArrayList<CharacteristcGroupDTO> getCharacteristicGroupList() {
        return characteristicGroupList;
    }

    public void setCharacteristicGroupList(ArrayList<CharacteristcGroupDTO> characteristicGroupList) {
        this.characteristicGroupList = characteristicGroupList;
    }
    }

