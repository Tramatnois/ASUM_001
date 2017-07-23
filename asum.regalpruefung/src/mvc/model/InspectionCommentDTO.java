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
class InspectionCommentDTO {
    private int idinspectioncomment;
    private String comment;
    private int inspectionresultid;
    private int characteristicid;

    public int getIdinspectioncomment() {
        return idinspectioncomment;
    }

    public void setIdinspectioncomment(int idinspectioncomment) {
        this.idinspectioncomment = idinspectioncomment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getInspectionresultid() {
        return inspectionresultid;
    }

    public void setInspectionresultid(int inspectionresultid) {
        this.inspectionresultid = inspectionresultid;
    }

    public int getCharacteristicid() {
        return characteristicid;
    }

    public void setCharacteristicid(int characteristicid) {
        this.characteristicid = characteristicid;
    }
    
}
