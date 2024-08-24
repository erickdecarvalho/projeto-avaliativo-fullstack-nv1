package car_rent.api.services;

import car_rent.api.dtos.CustomerDto;
import car_rent.api.dtos.VehicleDto;
import car_rent.api.models.CustomerModel;
import car_rent.api.models.VehicleModel;
import car_rent.api.repositories.CustomerRepository;
import car_rent.api.services.specification.CustomerScpecification;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Page<CustomerModel> getCustomers (String name, String phone, String email, Pageable pageable){
        Specification<CustomerModel> spec = Specification
                .where(CustomerScpecification.hasName(name))
                .and(CustomerScpecification.hasPhone(phone))
                .and(CustomerScpecification.hasEmail(email));

        return customerRepository.findAll(spec, pageable);
    }

    public CustomerModel getCustomerById(Long id){

        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));
    }

    public CustomerModel addCustomer (CustomerModel customer){
        return customerRepository.save(customer);
    }

    public CustomerModel updateCustomer (Long id, CustomerDto customerDto){
        CustomerModel customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        BeanUtils.copyProperties(customerDto, customer);
        customerRepository.save(customer);
        return customer;

    }

    public CustomerModel deleteCustomer(Long id){
        CustomerModel customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));
        customerRepository.delete(customer);
        return customer;
    }
}
