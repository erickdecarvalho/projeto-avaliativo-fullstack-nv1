package car_rent.api.application.vehicle;


import car_rent.api.domain.vehicle.VehicleType;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VehicleDto {

    private VehicleType type;

    private String color;

    private Integer year;

    private String brand;

    private String licensePlate;

    private Boolean rented;

    public VehicleDto(VehicleType type, String color, Integer year, String brand, String licensePlate ) {
        this.type = type;
        this.color = color;
        this.year = year;
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.rented = false;
    }
}
