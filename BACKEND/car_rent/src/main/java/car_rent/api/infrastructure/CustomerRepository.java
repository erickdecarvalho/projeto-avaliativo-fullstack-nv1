package car_rent.api.infrastructure;

import car_rent.api.domain.customer.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long>, PagingAndSortingRepository<CustomerModel, Long>, JpaSpecificationExecutor<CustomerModel> {
}
