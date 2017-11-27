package model;

import entities.Customer;
import data.CustomerDataStore;
import java.util.Date;

/**
 * Alba Airways application M813-TMA02-RegisterCustomer
 * https://github.com/jc184/M813-TMA02-RegCustomer
 *
 * @author james chalmers Open University F6418079
 */
public class CustomerManager {

    CustomerDataStore customerStore;

    /* 
     * Constructor for CustomerManager. Initialises a new CustomerDataStore
     */
    public CustomerManager() {
        customerStore = new CustomerDataStore();
    }

    /* 
     * This method communicates with customerStore to create a new Customer instance, and creates a record of it in the applications database
     */
    public void addCustomer(String title, String firstName, String surname, String mobileNo, String homePhoneNumber, String emailAddress, String loginName, String loginPassword, Date dateOfBirth) {
        for (int newId = 1; newId < Integer.MAX_VALUE; newId++) {
            if (customerStore.getRecord(newId) == null) {
                Customer customer = new Customer(newId, title, firstName, surname, mobileNo, homePhoneNumber, emailAddress, loginName, loginPassword, dateOfBirth);
                customerStore.createRecord(customer);
                return;
            }
        }
    }

    /* 
     * This method is needed to retrieve customerId from customerStore.
     */
    public int getCustomerIdByAdd(String loginName, String loginPassword) {
        int customerId = 0;
        for (Customer customer : customerStore.getAllRecords()) {
            if (customer.getLoginName().equals(loginName) && (customer.getLoginPassword().equals(loginPassword))) {
                customerId = customer.getCustomerId();
            }
        }
        return customerId;
    }

    /* 
     * This method is needed to verify if a customer already exists.
     */
    public boolean validateCustomer(String loginName, String loginPassword, String emailAddress) {
        for (Customer customer : customerStore.getAllRecords()) {
            if (customer.getLoginName().equals(loginName) && (customer.getLoginPassword().equals(loginPassword)) || (customer.getEmailAddress().equals(emailAddress))) {
                return true;
            } 
        }
        return false;
    }
}
