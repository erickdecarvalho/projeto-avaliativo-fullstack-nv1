package car_rent.api.dtos;

import car_rent.api.models.TypeVehicleModel;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddVehicleDto {

    private TypeVehicleModel type;

    private String color;

    private Integer year;

    private String brand;

    private String licensePlate;

    private Boolean rented;

    public AddVehicleDto(TypeVehicleModel type, String color, Integer year, String brand, String licensePlate, Boolean rented) {
        this.type = type;
        this.color = color;
        this.year = year;
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.rented = false;
    }
}
