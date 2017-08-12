/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author james
 */
public class Islander extends Customer {

    private static final long serialVersionUID = 1L;
    
    private double discount = 0.2;

    public Islander() {
    }

    public Islander(Integer customerId, String title, String firstName, String surname, String mobileNo, String homePhoneNumber, String emailAddress, String loginName, String loginPassword, Date dateOfBirth, double discount) {
        super(customerId, title, firstName, surname, mobileNo, homePhoneNumber, emailAddress, loginName, loginPassword, dateOfBirth);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

}
