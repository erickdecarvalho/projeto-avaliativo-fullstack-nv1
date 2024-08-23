package car_rent.api.services;

import car_rent.api.models.TypeVehicleModel;
import car_rent.api.models.VehicleModel;
import car_rent.api.repositories.VehicleRepository;
import car_rent.api.services.specification.VehicleSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Page<VehicleModel> getVehicles(
            TypeVehicleModel type, Integer minYear, Integer maxYear, String color, Boolean rented, String officeName, Pageable pageable){
        Specification<VehicleModel> spec = Specification
                .where(VehicleSpecification.hasColor(color))
                .and(VehicleSpecification.hasOffice(officeName))
                .and(VehicleSpecification.hasYear(minYear,maxYear))
                .and(VehicleSpecification.hasRented(rented))
                .and(VehicleSpecification.hasType(type));

        return vehicleRepository.findAll(spec, pageable );
    }
}
