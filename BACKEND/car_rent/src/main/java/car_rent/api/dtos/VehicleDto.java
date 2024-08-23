package car_rent.api.dtos;


import car_rent.api.models.TypeVehicleModel;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VehicleDto {

    private TypeVehicleModel type;

    private String color;

    private Integer year;

    private String brand;

    private String licensePlate;

    private Boolean rented;

    public VehicleDto(TypeVehicleModel type, String color, Integer year, String brand, String licensePlate ) {
        this.type = type;
        this.color = color;
        this.year = year;
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.rented = false;
    }
}
