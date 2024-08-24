package car_rent.api.application.rental;

import car_rent.api.domain.rental.RentalModel;
import car_rent.api.domain.rental.StatusRentalType;
import car_rent.api.domain.rental.validators.RentalValidator;
import car_rent.api.infrastructure.RentalRepository;
import car_rent.api.shared.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final List<RentalValidator> validators;

    @Autowired
    public RentalService(RentalRepository rentalRepository, List<RentalValidator> validators) {
        this.rentalRepository = rentalRepository;
        this.validators = validators;
    }

    @Transactional
    public RentalModel validateAndSave(RentalModel rental) {
        validators.forEach(validator -> validator.validate(rental));
        return rentalRepository.save(rental);
    }

    @Transactional
    public RentalModel updateRental(Long id, RentalDto rentalDTO) {
        RentalModel rental = rentalRepository.findById(id).orElseThrow(() -> new NotFoundException("Aluguel não encontrado."));
        rental.setStatus(StatusRentalType.valueOf(rentalDTO.status()));
        rental.setDate(rentalDTO.date() != null ? rentalDTO.date() : LocalDateTime.now());
        validators.forEach(validator -> validator.validate(rental));
        return rentalRepository.save(rental);
    }

    public Optional<RentalModel> findById(Long id) {
        return rentalRepository.findById(id);
    }
}
