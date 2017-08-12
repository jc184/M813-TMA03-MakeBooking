/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Alba Airways application M813-TMA02-RegisterCustomer
 * https://github.com/jc184/M813-TMA02-RegCustomer Implementing the Facade
 * Pattern
 *
 * @author james chalmers Open University F6418079
 */
public class CustomerFacade {

    public CustomerManager customerManager = new CustomerManager();

    public CustomerFacade() {
    }


}
