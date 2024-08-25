package car_rent.api.domain.rental.validation;

import car_rent.api.domain.rental.RentalModel;
import car_rent.api.domain.rental.StatusType;
import car_rent.api.infrastructure.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RentalQueueValidator implements RentalValidator {

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public void validate(RentalModel rental) throws RuntimeException {
        if (rental.getStatus() == StatusType.APPROVED) {
            List<RentalModel> rentalsAwaiting = rentalRepository.findByVehicleAndStatusOrderByDateAsc(rental.getVehicle(), StatusType.AWAITING_VALIDATION);
            if (!rentalsAwaiting.isEmpty() && !rentalsAwaiting.get(0).getId().equals(rental.getId())) {
                throw new RentalException("Não é possível aprovar o aluguel, pois há outro cliente na fila aguardando.");
            }
        }
    }
}
