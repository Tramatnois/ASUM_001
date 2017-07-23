/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

/**
 *
 * @author LT Dan
 */
public class InspectorDTO {

    private int idinspector;
    private String name;
    private String street;
    private String zipcode;
    private String city;

    public int getIdinspector() {
        return idinspector;
    }

    public void setIdinspector(int idinspector) {
        this.idinspector = idinspector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
