package App.Infrastructure;

import App.Domain.Ingredients;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientRepository {

    private final JdbcTemplate databaseConnection;

    public IngredientRepository(JdbcTemplate databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    // Create a new ingredient
    public Ingredients create(Ingredients ingredient) {
        String sql = "INSERT INTO INGREDIENTS (ingredient_id, name, type, quantity_in_stock) VALUES (?, ?, ?, ?)";
        this.databaseConnection.update(sql, ingredient.getIngredientId(), ingredient.getName(), ingredient.getType(), ingredient.getQuantityInStock());
        return ingredient;
    }

    // Get ingredient by ID
    public Ingredients get(int id) {
        String sql = "SELECT * FROM INGREDIENTS WHERE ingredient_id = ?";
        return this.databaseConnection.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(Ingredients.class));
    }

    // Get all ingredients (should return a List)
    public List<Ingredients> getAll() {
        String sql = "SELECT * FROM INGREDIENTS";
        return this.databaseConnection.query(sql, BeanPropertyRowMapper.newInstance(Ingredients.class));
    }

    // Delete ingredient by ID
    public void delete(int id) {
        String sql = "DELETE FROM INGREDIENTS WHERE ingredient_id = ?";
        this.databaseConnection.update(sql, id);
    }
}
