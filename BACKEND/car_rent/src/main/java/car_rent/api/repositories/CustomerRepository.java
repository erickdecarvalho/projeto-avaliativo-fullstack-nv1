package car_rent.api.repositories;

import car_rent.api.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long>, PagingAndSortingRepository<CustomerModel, Long>, JpaSpecificationExecutor<CustomerModel> {
}
