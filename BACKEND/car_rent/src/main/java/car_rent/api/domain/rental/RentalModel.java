package car_rent.api.domain.rental;

import car_rent.api.domain.customer.CustomerModel;
import car_rent.api.domain.vehicle.VehicleModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rental")
public class RentalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data do aluguel é obrigatória.")
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "O cliente é obrigatório.")
    private CustomerModel customer;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "rental")
    @NotNull(message = "O veículo é obrigatório.")
    private VehicleModel vehicle;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do aluguel é obrigatório.")
    private StatusRentalType status = StatusRentalType.AWAITING_VALIDATION;

    private String statusDescription;
}