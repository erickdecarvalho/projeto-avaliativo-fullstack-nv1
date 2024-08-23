package car_rent.api.repositories;

import car_rent.api.models.RentalOfficeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RentalOfficeRepository  extends JpaRepository<RentalOfficeModel, Long> {
}

