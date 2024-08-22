package car_rent.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TypeVehicleModel type;
    
    private String color;

    private Integer year;

    private Boolean rented;

    @ManyToOne
    private RentalOfficeModel office;

    @OneToOne
    private RentalModel rental;






}
