package car_rent.api.infrastructure;

import car_rent.api.domain.rental.RentalModel;
import car_rent.api.domain.rental.StatusType;
import car_rent.api.domain.vehicle.VehicleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<RentalModel, Long> {
    List<RentalModel> findByVehicleAndStatusOrderByDateAsc(VehicleModel vehicle, StatusType status);
    Page<RentalModel> findAll(Specification<RentalModel> spec, Pageable pageable);
}
