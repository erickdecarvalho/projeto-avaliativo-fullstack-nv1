package car_rent.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Enumerated(EnumType.STRING)
    private TypeVehicleModel type;
    
    private String color;

    private Integer year;

    private Boolean rented;

    @ManyToOne
    @JsonIgnore
    private RentalOfficeModel office;

    @OneToOne
    @JsonIgnore
    private RentalModel rental;






}
