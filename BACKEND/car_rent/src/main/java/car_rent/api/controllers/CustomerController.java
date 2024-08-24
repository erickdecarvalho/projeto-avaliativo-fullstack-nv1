package car_rent.api.controllers;

import car_rent.api.dtos.CustomerDto;
import car_rent.api.models.CustomerModel;
import car_rent.api.services.CustomerService;
import car_rent.api.utils.PaginationHeaders;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getVehicles(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "page",defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "size",defaultValue = "5", required = false) Integer size

    ) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<CustomerModel> customer = customerService.getCustomers(name, phone, email, pageable);
        HttpHeaders headers = PaginationHeaders.createPaginationHeaders(customer);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(customer.getContent());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerModel> getCustomerById (@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerModel> addCustomer(@RequestBody @Valid CustomerDto customerDto) {
        CustomerModel customer = new CustomerModel();
        BeanUtils.copyProperties(customerDto, customer);
        CustomerModel createdCustomer = customerService.addCustomer(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCustomer.getId()).toUri();
        return ResponseEntity.created(location).body(createdCustomer);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomerModel> updateCustomer (@PathVariable(value = "id") Long id, @RequestBody @Valid CustomerDto customerDto){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id, customerDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteCustomer (@PathVariable(value = "id")Long id){
        CustomerModel customer = customerService.deleteCustomer(id);

        return ResponseEntity.status(HttpStatus.OK).body("Customer with id: " + customer.getId() + " deleted!");

    }
}
