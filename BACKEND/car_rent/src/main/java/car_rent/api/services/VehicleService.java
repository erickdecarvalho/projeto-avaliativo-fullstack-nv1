package car_rent.api.services;

import car_rent.api.dtos.VehicleDto;
import car_rent.api.exceptions.NotFoundException;
import car_rent.api.models.TypeVehicleModel;
import car_rent.api.models.VehicleModel;
import car_rent.api.repositories.VehicleRepository;
import car_rent.api.services.specification.VehicleSpecification;
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

    public Page<VehicleModel> getVehicles(
            TypeVehicleModel type, Integer minYear, Integer maxYear, String color, Boolean rented, Pageable pageable){
        Specification<VehicleModel> spec = Specification
                .where(VehicleSpecification.hasColor(color))
                .and(VehicleSpecification.hasYear(minYear,maxYear))
                .and(VehicleSpecification.hasRented(rented))
                .and(VehicleSpecification.hasType(type));

        return vehicleRepository.findAll(spec, pageable );
    }

    public VehicleModel getVehicleByID(Long id){

        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found."));
    }

    @Transactional
    public VehicleModel addVehicles (VehicleModel vehicle){
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public VehicleModel updateVehicle(Long id, VehicleDto vehicleDto) {
        VehicleModel vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found."));

        BeanUtils.copyProperties(vehicleDto, vehicle);
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public VehicleModel deleteVehicle(Long id){
        VehicleModel vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found."));
        vehicleRepository.delete(vehicle);
        return vehicle;
    }


}
