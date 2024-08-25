package car_rent.api.application.vehicle;

import car_rent.api.shared.exceptions.NotFoundException;
import car_rent.api.domain.vehicle.VehicleType;
import car_rent.api.domain.vehicle.VehicleModel;
import car_rent.api.infrastructure.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    protected Page<VehicleModel> getVehicles(
            VehicleType type,
            Integer minYear,
            Integer maxYear,
            String color,
            Boolean rented,
            String brand,
            String model,
            Pageable pageable
    ) {

        Specification<VehicleModel> spec = Specification
                .where(VehicleSpecification.hasColor(color))
                .and(VehicleSpecification.hasYear(minYear, maxYear))
                .and(VehicleSpecification.hasRented(rented))
                .and(VehicleSpecification.hasType(type))
                .and(VehicleSpecification.hasBrand(brand))
                .and(VehicleSpecification.hasModel(model));

        return vehicleRepository.findAll(spec, pageable);
    }

    protected VehicleModel getVehicleByID(Long id){

        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found."));
    }

    @Transactional
    protected VehicleModel addVehicles (VehicleModel vehicle){
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    protected VehicleModel updateVehicle(Long id, VehicleDto vehicleDto) {
        VehicleModel vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found."));

        BeanUtils.copyProperties(vehicleDto, vehicle);
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    protected VehicleModel deleteVehicle(Long id){
        VehicleModel vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found."));
        vehicleRepository.delete(vehicle);
        return vehicle;
    }


}
