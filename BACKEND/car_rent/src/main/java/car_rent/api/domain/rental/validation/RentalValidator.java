package car_rent.api.domain.rental.validation;

import car_rent.api.domain.rental.RentalModel;

public interface RentalValidator {
    void validate(RentalModel rental) throws RuntimeException;
}
