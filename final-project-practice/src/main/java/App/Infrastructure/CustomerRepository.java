package App.Infrastructure;

import App.Domain.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    private final JdbcTemplate databaseConnection;

    public CustomerRepository(JdbcTemplate databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    // Create a new customer
    public Customer create(Customer customer) {
        String sql = "INSERT INTO CUSTOMERS (Customer_ID, First_Name, Last_Name, Phone_Number, Email) " +
                     "VALUES (?, ?, ?, ?, ?)";
        this.databaseConnection.update(sql, 
            customer.getCustomerId(),
            customer.getFirstName(),
            customer.getLastName(),
            customer.getPhoneNumber(),
            customer.getEmail());
        return customer;  // Return the created customer
    }

    // Get a customer by customer_id
    public Customer get(int customerId) {
        String sql = "SELECT * FROM CUSTOMERS WHERE Customer_ID = ?";
        try {
            return this.databaseConnection.queryForObject(sql, new Object[]{customerId}, 
                BeanPropertyRowMapper.newInstance(Customer.class));
        } catch (Exception e) {
            return null; // If no customer is found, return null
        }
    }

    // Update customer information by customer_id
    public void update(int customerId, Customer customer) {
        String sql = "UPDATE CUSTOMERS SET " +
                     "First_Name = ?, Last_Name = ?, Phone_Number = ?, Email = ? " +
                     "WHERE Customer_ID = ?";
        this.databaseConnection.update(sql, 
            customer.getFirstName(), 
            customer.getLastName(), 
            customer.getPhoneNumber(), 
            customer.getEmail(), 
            customerId);  // Ensure customerId matches the database column
    }

    // Delete a customer by customer_id
    public void delete(int customerId) {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        this.databaseConnection.update(sql, customerId);
    }
}
