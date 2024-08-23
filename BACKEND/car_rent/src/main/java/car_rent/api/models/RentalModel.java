package car_rent.api.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
public class RentalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime Date;

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerModel customer;

    @OneToOne(fetch = FetchType.LAZY)
    private VehicleModel vehicle;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String statusDescription;

}

