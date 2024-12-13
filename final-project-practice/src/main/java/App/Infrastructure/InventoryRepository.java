package App.Infrastructure;

import App.Domain.Inventory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventoryRepository {

    private final JdbcTemplate databaseConnection;

    public InventoryRepository(JdbcTemplate databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    // Get inventory quantity for a specific item by name
    public int getInventoryQuantity(String itemName) {
        String sql = "SELECT quantity FROM INVENTORY WHERE item_name = ?";
        return this.databaseConnection.queryForObject(sql, new Object[]{itemName}, Integer.class);
    }

    // Update inventory quantity for a specific item
    public void updateInventory(String itemName, int newQuantity) {
        String sql = "UPDATE INVENTORY SET quantity = ? WHERE item_name = ?";
        this.databaseConnection.update(sql, newQuantity, itemName);
    }

    // Get all inventory items (should return a List of inventory items)
    public List<Inventory> getAll() {
        String sql = "SELECT * FROM INVENTORY";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Inventory.class));
    }
}
