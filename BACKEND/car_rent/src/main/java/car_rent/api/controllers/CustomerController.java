package car_rent.api.controllers;

import car_rent.api.dtos.CustomerDto;
import car_rent.api.dtos.VehicleDto;
import car_rent.api.models.CustomerModel;
import car_rent.api.models.TypeVehicleModel;
import car_rent.api.models.VehicleModel;
import car_rent.api.services.CustomerService;
import car_rent.api.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(value = "page",defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size",defaultValue = "5", required = false) Integer size

    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getCustomers(name, phone, email, pageable).getContent());

    }

    @PostMapping
    public ResponseEntity<CustomerModel> addCustomer (@RequestBody @Valid CustomerDto customerDto){
        CustomerModel customer = new CustomerModel();
        BeanUtils.copyProperties(customerDto,customer);
        return ResponseEntity.status(HttpStatus.OK).body(customerService.addCustomer(customer));
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
