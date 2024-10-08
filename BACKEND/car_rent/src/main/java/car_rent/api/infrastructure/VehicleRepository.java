package car_rent.api.infrastructure;

import car_rent.api.domain.vehicle.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Long>, PagingAndSortingRepository<VehicleModel, Long>, JpaSpecificationExecutor<VehicleModel>  {
//    Optional<VehicleModel> findById(Long id);
}
