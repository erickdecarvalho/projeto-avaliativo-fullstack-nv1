package car_rent.api.application.rental;

import car_rent.api.domain.rental.RentalModel;
import car_rent.api.domain.rental.StatusRentalType;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping
    public ResponseEntity<RentalModel> createRental(@RequestBody RentalModel rental) {

        RentalModel createdRental = rentalService.validateAndSave(rental);

        return new ResponseEntity<>(createdRental, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> updateRental(@PathVariable Long id, @RequestBody @Valid RentalDto rentalDTO) {
        RentalModel updatedRental = rentalService.updateRental(id, rentalDTO);

        RentalDto responseDto = new RentalDto(
                updatedRental.getDate(),
                updatedRental.getCustomer().getId(),
                updatedRental.getVehicle().getId(),
                updatedRental.getStatus().name(),
                updatedRental.getStatusDescription(),
                updatedRental.getId()
        );

        return ResponseEntity.ok(responseDto);
    }
}
