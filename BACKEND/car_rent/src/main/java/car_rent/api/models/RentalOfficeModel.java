package car_rent.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RentalOffice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalOfficeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String name;

    private String phone;

    private String email;

}
