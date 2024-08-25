package car_rent.api.application.rental;

import car_rent.api.domain.rental.RentalModel;
import car_rent.api.domain.rental.StatusType;
import car_rent.api.domain.rental.validation.RentalValidator;
import car_rent.api.domain.vehicle.VehicleModel;
import car_rent.api.domain.customer.CustomerModel;
import car_rent.api.infrastructure.RentalRepository;
import car_rent.api.infrastructure.VehicleRepository;
import car_rent.api.infrastructure.CustomerRepository;
import car_rent.api.shared.exceptions.NotFoundException;
import car_rent.api.shared.utils.Messages;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private List<RentalValidator> validators;

    @Transactional
    public RentalDto finalizeRental(Long rentalId) {
        RentalModel rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new NotFoundException(Messages.notFount("Aluguel")));

        VehicleModel vehicle = rental.getVehicle();
        vehicle.setRented(false);
        vehicleRepository.save(vehicle);

        rental.setStatus(StatusType.FINALIZED);
        rentalRepository.save(rental);

        return convertToDto(rental);
    }

    @Transactional
    protected RentalModel updateRental(Long id, RentalDto rentalDto) {
        RentalModel rental = findRentalById(id);
        BeanUtils.copyProperties(rentalDto, rental);

        return rentalRepository.save(rental);
    }

    @Transactional
    protected void destroyRental(Long id) {
        RentalModel rental = findRentalById(id);
        VehicleModel vehicle = rental.getVehicle();

        vehicle.setRented(false);
        vehicle.setRental(null);

        vehicleRepository.save(vehicle);
        rentalRepository.delete(rental);
    }

    protected Page<RentalDto> getRentals(String status, Pageable pageable) {
        Specification<RentalModel> spec = Specification
                .where(RentalSpecification.hasStatus(status != null ? StatusType.valueOf(status) : null));
        Page<RentalModel> rentals = rentalRepository.findAll(spec, pageable);
        return rentals.map(this::convertToDto);
    }

    @Transactional
    protected RentalModel save(RentalModel rental) {
        validate(rental);
        return rentalRepository.save(rental);
    }

    private void validate(RentalModel rental) {
        validators.forEach(validator -> validator.validate(rental));
    }

    protected RentalModel findRentalById(Long rentalId) {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new NotFoundException(Messages.notFount("Aluguel")));
    }

    protected RentalDto convertToDto(RentalModel rental) {
        return new RentalDto(
                rental.getId(),
                rental.getCustomer().getId(),
                rental.getVehicle().getId(),
                rental.getDate(),
                rental.getStatus().name(),
                rental.getPrice(),
                rental.getStartDate(),
                rental.getFinalDate()
        );
    }
}
