package car_rent.api.application.rental;

import car_rent.api.domain.rental.RentalModel;
import car_rent.api.domain.rental.StatusType;
import car_rent.api.shared.utils.PaginationHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public ResponseEntity<List<RentalDto>> getRentals(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "5", required = false) Integer size
    ) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<RentalDto> rentals = rentalService.getRentals(status, pageable);
        HttpHeaders headers = PaginationHeaders.createPaginationHeaders(rentals);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(rentals.getContent());
    }


    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable(value = "id") Long id) {
        RentalModel rental = rentalService.findRentalById(id);
        return ResponseEntity.status(HttpStatus.OK).body(rentalService.convertToDto(rental));
    }

    @PostMapping("/{id}/finalize")
    public ResponseEntity<RentalDto> finalizeRental(@PathVariable Long id) {
        RentalDto rental = rentalService.finalizeRental(id);
        return ResponseEntity.status(HttpStatus.OK).body(rental);
    }

    @PostMapping
    public ResponseEntity<RentalModel> createRental(@RequestBody RentalModel rental) {
        RentalModel createdRental = rentalService.save(rental);
        return new ResponseEntity<>(createdRental, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> updateRental(@PathVariable Long id, @RequestBody RentalDto rentalDto) {
        RentalModel updatedRental = rentalService.updateRental(id, rentalDto);
        return ResponseEntity.status(HttpStatus.OK).body(rentalService.convertToDto(updatedRental));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RentalDto> updateRentalStatus(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        RentalModel rental = rentalService.findRentalById(id);
        String status = updates.get("status");
        if (status != null) rental.setStatus(StatusType.valueOf(status));
        RentalModel updatedRental = rentalService.save(rental);
        return ResponseEntity.status(HttpStatus.OK).body(rentalService.convertToDto(updatedRental));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        rentalService.destroyRental(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
