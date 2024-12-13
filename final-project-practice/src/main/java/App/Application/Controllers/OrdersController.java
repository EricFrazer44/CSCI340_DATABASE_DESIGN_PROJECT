package App.Application.Controllers;

import App.Domain.Orders;
import App.Infrastructure.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/Order")
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository; // Automatically injected by Spring

    // POST /Order - Creates a new order
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Orders createOrder(@RequestBody Orders order) {
        return ordersRepository.create(order); // Save the order
    }

    // GET /Order/{id} - Retrieves a single order by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Orders getOrder(@PathVariable("id") int id) {
        Orders order = ordersRepository.get(id);  // Retrieve single order by ID
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        return order;  // Return the order
    }

    // DELETE /Order/{id} - Deletes an order by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") int id) {
        ordersRepository.delete(id); // Delete the order
    }
}

