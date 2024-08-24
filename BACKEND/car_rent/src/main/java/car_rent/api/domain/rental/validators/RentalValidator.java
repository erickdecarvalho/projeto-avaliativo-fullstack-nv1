package car_rent.api.domain.rental.validators;

import car_rent.api.domain.rental.RentalModel;

public interface RentalValidator {
    void validate(RentalModel rental) throws RuntimeException;
}
