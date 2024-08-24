package car_rent.api.domain.customer;

import car_rent.api.domain.rental.RentalModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    private String name;

    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve ter entre 10 e 11 dígitos.")
    private String phone;

    @Email(message = "Email deve ser válido.")
    @NotBlank(message = "Email é obrigatório.")
    private String email;

    @Column(unique = true)
    @NotBlank(message = "CPF é obrigatório.")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 dígitos.")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas números.")
    private String cpf;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<RentalModel> rentals;
}
