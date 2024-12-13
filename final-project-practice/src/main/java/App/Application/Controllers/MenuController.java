package App.Application.Controllers;

import App.Domain.Menu;
import App.Domain.OrderSummary;
import App.Infrastructure.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Menu")
public class MenuController {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // GET /Menu - Get all menu items ordered by price
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Menu> getAll() {
        return menuRepository.getAllMenusOrderedByPrice();
    }

    // GET /Menu/{id} - Get a menu item by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Menu get(@PathVariable("id") int id) {
        return menuRepository.get(id);
    }

    // GET /Menu/orders/summary - Get order summary with aggregation
    @GetMapping("/orders/summary")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderSummary> getOrderSummary() {
        return menuRepository.getOrderSummary();
    }
}

