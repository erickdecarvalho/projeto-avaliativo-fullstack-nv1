package car_rent.api.infrastructure;

import car_rent.api.domain.rental.RentalModel;
import car_rent.api.domain.rental.StatusRentalType;
import car_rent.api.domain.vehicle.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<RentalModel, Long> {
    List<RentalModel> findByVehicleAndStatusOrderByDateAsc(VehicleModel vehicle, StatusRentalType status);
}
