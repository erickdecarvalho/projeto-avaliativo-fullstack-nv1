package car_rent.api.application.customer;

import car_rent.api.shared.exceptions.NotFoundException;
import car_rent.api.domain.customer.CustomerModel;
import car_rent.api.infrastructure.CustomerRepository;
import car_rent.api.shared.utils.Messages;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    protected Page<CustomerModel> getCustomers (String name, String phone, String email, Pageable pageable){
        Specification<CustomerModel> spec = Specification
                .where(CustomerScpecification.hasName(name))
                .and(CustomerScpecification.hasPhone(phone))
                .and(CustomerScpecification.hasEmail(email));

        return customerRepository.findAll(spec, pageable);
    }

    protected CustomerModel getCustomerById(Long id){

        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Messages.notFount("Cliente")));
    }

    @Transactional
    protected CustomerModel addCustomer (CustomerModel customer){
        return customerRepository.save(customer);
    }

    @Transactional
    protected CustomerModel updateCustomer (Long id, CustomerDto customerDto){
        CustomerModel customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Messages.notFount("Cliente")));

        BeanUtils.copyProperties(customerDto, customer);
        customerRepository.save(customer);
        return customer;

    }

    @Transactional
    protected CustomerModel deleteCustomer(Long id){
        CustomerModel customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Messages.notFount("Cliente")));
        customerRepository.delete(customer);
        return customer;
    }
}
