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
class InspectionOperationCommentDTO {
    private int id;
    private String comment;
    private CharacteristicOperationDTO characteristic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public CharacteristicOperationDTO getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(CharacteristicOperationDTO characteristic) {
        this.characteristic = characteristic;
    }


    
}
