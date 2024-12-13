package App.Infrastructure;

import App.Domain.Orders;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersRepository {

    private final JdbcTemplate databaseConnection;

    public OrdersRepository(JdbcTemplate databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    // Create a new order
    public Orders create(Orders order) {
        String sql = "INSERT INTO ORDERS (order_id, customer_id, employee_id, order_date, total_amount) " +
                     "VALUES (?, ?, ?, ?, ?)";
        this.databaseConnection.update(sql, order.getOrderId(), order.getCustomerId(), 
                                        order.getEmployeeId(), order.getOrderDate(), 
                                        order.getTotalAmount());
        return order;
    }

    // Get a specific order by order_id
    public Orders get(int id) {
        String sql = "SELECT * FROM ORDERS WHERE order_id = ?";
        try {
            return this.databaseConnection.queryForObject(sql, new Object[]{id}, 
                                                          BeanPropertyRowMapper.newInstance(Orders.class));
        } catch (Exception e) {
            return null; // If no order is found, return null
        }
    }

    // Get all orders
    public List<Orders> getAll() {
        String sql = "SELECT * FROM ORDERS";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Orders.class));
    }

    // Delete order by ID
    public void delete(int id) {
        String sql = "DELETE FROM ORDERS WHERE order_id = ?";
        this.databaseConnection.update(sql, id);
    }
}

