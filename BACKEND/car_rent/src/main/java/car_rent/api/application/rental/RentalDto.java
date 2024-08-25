package car_rent.api.application.rental;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RentalDto(
        Long id,
        Long customerId,
        Long vehicleId,
        LocalDateTime rentalDate,
        String status,
        BigDecimal price,
        LocalDateTime startDate,
        LocalDateTime finalDate
) {}
