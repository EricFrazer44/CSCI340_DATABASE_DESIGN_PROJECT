package App.Application.Controllers;

import App.Domain.Ingredients;
import App.Infrastructure.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/Ingredients")
public class IngredientsController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientsController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    // POST /Ingredients - Add a new ingredient
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredients createIngredient(@RequestBody Ingredients ingredient) {
        return ingredientRepository.create(ingredient);
    }

    // GET /Ingredients/{id} - Get an ingredient by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ingredients getIngredient(@PathVariable("id") int id) {
        Ingredients ingredient = ingredientRepository.get(id);
        if (ingredient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
        }
        return ingredient;
    }

    // DELETE /Ingredients/{id} - Delete an ingredient by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") int id) {
        ingredientRepository.delete(id);
    }
}
