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
public class CharacteristicTypeDTO {
    private int idcharacteristictype;
    private String description;

    public int getIdcharacteristictype() {
        return idcharacteristictype;
    }

    
    public void setIdcharacteristictype(int idcharacteristictype) {
        this.idcharacteristictype = idcharacteristictype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
