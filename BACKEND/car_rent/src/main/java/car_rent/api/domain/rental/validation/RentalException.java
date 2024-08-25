package car_rent.api.domain.rental.validation;

public class RentalException extends RuntimeException {
    public RentalException(String message) {
        super(message);
    }
}
