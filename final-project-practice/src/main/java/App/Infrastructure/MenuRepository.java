package App.Infrastructure;

import App.Domain.Menu;
import App.Domain.OrderSummary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepository {

    private final JdbcTemplate databaseConnection;

    public MenuRepository(JdbcTemplate databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    // Get all menu items ordered by price
    public List<Menu> getAllMenusOrderedByPrice() {
        String sql = "SELECT * FROM MENU ORDER BY price ASC";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Menu.class));
    }

    // Get a specific menu item by ID
    public Menu get(int id) {
        String sql = "SELECT * FROM MENU WHERE menu_item_id = ?";
        List<Menu> menus = this.databaseConnection.query(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(Menu.class));
        return menus.isEmpty() ? null : menus.get(0);
    }

    // Complex SQL Query - Get order summary (e.g., items sold and total amounts)
    public List<OrderSummary> getOrderSummary() {
        String sql = "SELECT menu_item_id, SUM(quantity) AS total_quantity, SUM(total_amount) AS total_amount " +
                     "FROM order_items " +
                     "JOIN orders ON order_items.order_id = orders.order_id " +
                     "GROUP BY menu_item_id";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(OrderSummary.class));
    }
}
