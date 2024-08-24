package car_rent.api.application.rental;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record RentalDto(
        LocalDateTime date,
        Long customerId,
        Long vehicleId,
        String status,
        String statusDescription,
        Long id
) {
}
