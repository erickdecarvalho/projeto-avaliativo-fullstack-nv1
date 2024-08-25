package car_rent.api.application.rental;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RentalRequestDto(
        Long customerId,
        LocalDateTime startDate,
        LocalDateTime finalDate,
        BigDecimal price
) {}
