
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Customer;
import entities.Customer;

/**
 * Alba Airways application M813-TMA02-RegisterCustomer
 * https://github.com/jc184/M813-TMA02-RegCustomer
 * @author james chalmers Open University F6418079
 * 
 */
public class CustomerDataStore {

    /*
     * 
     */
    public void createRecord(Customer customer) {

        Connection connection = getConnection();

        try {
            PreparedStatement create = (PreparedStatement) connection.prepareStatement("INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            create.setInt(1, customer.getCustomerId());
            create.setString(2, customer.getTitle());
            create.setString(3, customer.getFirstName());
            create.setString(4, customer.getSurname());
            create.setString(5, customer.getMobileNo());
            create.setString(6, customer.getHomePhoneNumber());
            create.setString(7, customer.getEmailAddress());
            create.setString(8, customer.getLoginName());
            create.setString(9, customer.getLoginPassword());
            // A fix from StackOverflow to allow Dates to be entered:
            java.util.Date utilDate = customer.getDateOfBirth();
            java.sql.Date sqlDate;
            sqlDate = new java.sql.Date(utilDate.getTime());

            create.setDate(10, sqlDate);

            create.executeUpdate();

            create.close();

            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to create record: [" + sqle.getErrorCode() + "]" + sqle.getMessage());
        }
    }

    /*
     * 
     */
    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException cnfe) {
            System.err.println("Database driver not found: " + cnfe.getMessage());
        }

        String dbUrl = "jdbc:mysql://localhost:3306/flightbookingsystem";
        String dbUser = "root";
        String dbPass = "stcallans";
        Connection connection = null;

        try {
            connection = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException sqle) {
            System.err.println("Unable to connect to Database: [" + sqle.getErrorCode() + "]" + sqle.getMessage());
        }
        return (connection);
    }

    /*
     * 
     */
    public Customer getRecord(int id) {
        Customer customer = null;
        Connection connection = getConnection();
        try {
            PreparedStatement get = (PreparedStatement) connection.prepareStatement("SELECT * FROM customer WHERE CustomerId=?");

            get.setInt(1, id);

            ResultSet results = get.executeQuery();

            while (results.next()) {
                customer = new Customer(
                        results.getInt("CustomerId"),
                        results.getString("Title"),
                        results.getString("FirstName"),
                        results.getString("Surname"),
                        results.getString("MobileNo"),
                        results.getString("HomePhoneNumber"),
                        results.getString("EmailAddress"),
                        results.getString("LoginName"),
                        results.getString("LoginPassword"),
                        results.getDate("DateOfBirth"));
            }

            get.close();

            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to find Record: [" + sqle.getErrorCode() + "] " + sqle.getMessage());
        }
        return (customer);
    }

    /*
     * 
     */
    public List<Customer> getAllRecords() {

        Connection connection = getConnection();
        List<Customer> allCustomers = new ArrayList<>();

        try {
            PreparedStatement get = (PreparedStatement) connection.prepareStatement(
                    "SELECT * FROM customer");

            ResultSet results = get.executeQuery();
            Customer resultBean = null;
            while (results.next()) {
                resultBean = new Customer(
                        results.getInt("CustomerId"),
                        results.getString("Title"),
                        results.getString("FirstName"),
                        results.getString("Surname"),
                        results.getString("MobileNo"),
                        results.getString("HomePhoneNumber"),
                        results.getString("EmailAddress"),
                        results.getString("LoginName"),
                        results.getString("LoginPassword"),
                        results.getDate("DateOfBirth"));

                allCustomers.add(resultBean);
            }

            get.close();
            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to get all records: [" + sqle.getErrorCode() + "] " + sqle.getMessage());
        }
        return (allCustomers);
    }

}
