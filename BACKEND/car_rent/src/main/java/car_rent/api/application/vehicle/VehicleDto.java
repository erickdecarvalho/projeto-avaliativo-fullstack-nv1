package car_rent.api.application.vehicle;

import car_rent.api.domain.vehicle.VehicleType;

import java.util.Optional;

public record VehicleDto(
        Long id,
        VehicleType type,
        String color,
        Integer year,
        String brand,
        String model,
        String licensePlate,
        Boolean rented,
        Optional<Long> rentalId
) {}
