/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author LT Dan
 */
public class CustomerDTO extends RecursiveTreeObject<CustomerDTO> {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty street = new SimpleStringProperty();
    private StringProperty zipcode = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty contactperson = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty fax = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private IntegerProperty active = new SimpleIntegerProperty();

    public Integer getId() {
        return id.get();
    }

    public final IntegerProperty getIdProperty() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id.set(id);
    }

    public final String getName() {
        return name.get();
    }

    public final StringProperty getNameProperty() {
        return name;
    }

    public final void setName(final String name) {
        this.name.set(name);
    }

    public final String getStreet() {
        return street.get();
    }

    public final StringProperty getStreetProperty() {
        return street;
    }

    public final void setStreet(final String street) {
        this.street.set(street);
    }

    public final String getZipcode() {
        return zipcode.get();
    }

    public final StringProperty getZipCodeProperty() {
        return zipcode;
    }

    public final void setZipcode(final String zipcode) {
        this.zipcode.set(zipcode);
    }

    public final String getCity() {
        return city.get();
    }

    public final StringProperty getCityProperty() {
        return city;
    }

    public final void setCity(final String city) {
        this.city.set(city);
    }

    public final String getContactperson() {
        return contactperson.get();
    }

    public final StringProperty getContactpersonProperty() {
        return contactperson;
    }

    public final void setContactperson(final String contactperson) {
        this.contactperson.set(contactperson);
    }

    public final String getPhone() {
        return phone.get();
    }

    public final StringProperty getPhoneProperty() {
        return phone;
    }

    public final void setPhone(final String phone) {
        this.phone.set(phone);
    }

    public final String getFax() {
        return fax.get();
    }

    public final StringProperty getFaxProperty() {
        return fax;
    }

    public final void setFax(final String fax) {
        this.fax.set(fax);
    }

    public final String getEmail() {
        return email.get();
    }

    public final StringProperty getEmailProperty() {
        return email;
    }

    public final void setEmail(final String email) {
        this.email.set(email);
    }

    public final Integer getActive() {
        return active.get();
    }

    public final Boolean getActiveBoolean() {
        Boolean boolActive = false;
        switch (this.active.get()) {
            case 0:
                boolActive = false;
                break;
            default:
                boolActive = true;
                break;
        }        
        return boolActive;
    }

    public final IntegerProperty getActiveProperty() {
        return active;
    }

    public final void setActive(final Integer active) {
        this.active.set(active);
    }

}
