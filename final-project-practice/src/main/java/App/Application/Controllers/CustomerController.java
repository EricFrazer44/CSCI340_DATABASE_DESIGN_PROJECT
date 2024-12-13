package App.Application.Controllers;

import App.Domain.Customer;
import App.Infrastructure.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/Customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;  // Automatically injected by Spring

    // POST /Customer - Creates a new customer
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.create(customer);
    }

    // GET /Customer/{id} - Retrieves a customer by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable("id") int id) {
        Customer customer = customerRepository.get(id);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return customer;  // Return the customer
    }

    // PUT /Customer/{id} - Updates a customer by ID
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
        customerRepository.update(id, customer);  // Update customer in the repository
        return customer;  // Return the updated customer
    }

    // DELETE /Customer/{id} - Deletes a customer by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") int id) {
        customerRepository.delete(id);  // Delete customer from the repository
    }
}
