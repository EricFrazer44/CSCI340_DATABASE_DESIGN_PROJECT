package App.Application.Controllers;

import App.Infrastructure.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // GET /Inventory/{itemName} - Get the inventory quantity for a specific item
    @GetMapping("/{itemName}")
    @ResponseStatus(HttpStatus.OK)
    public int getInventory(@PathVariable("itemName") String itemName) {
        return inventoryRepository.getInventoryQuantity(itemName);
    }

    // PUT /Inventory/{itemName} - Update the inventory for a specific item
    @PutMapping("/{itemName}")
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@PathVariable("itemName") String itemName, @RequestParam("quantity") int quantity) {
        int currentInventory = inventoryRepository.getInventoryQuantity(itemName);
        int newQuantity = currentInventory + quantity;
        inventoryRepository.updateInventory(itemName, newQuantity);
    }
}
