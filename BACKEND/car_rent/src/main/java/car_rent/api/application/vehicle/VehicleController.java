package car_rent.api.application.vehicle;

import car_rent.api.domain.vehicle.VehicleType;
import car_rent.api.domain.vehicle.VehicleModel;
import car_rent.api.shared.utils.PaginationHeaders;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleModel>> getVehicles(
            @RequestParam(value = "type", required = false) VehicleType type,
            @RequestParam(value = "minYear", required = false) Integer minYear,
            @RequestParam(value = "maxYear", required = false) Integer maxYear,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "rented", required = false) Boolean rented,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "5", required = false) Integer size
    ) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<VehicleModel> vehiclePage = vehicleService.getVehicles(type, minYear, maxYear, color, rented, brand, model, pageable);
        HttpHeaders headers = PaginationHeaders.createPaginationHeaders(vehiclePage);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(vehiclePage.getContent());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VehicleModel> getVehicleById (@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getVehicleByID(id));
    }

    @PostMapping
    public ResponseEntity<VehicleModel> addVehicle (@RequestBody @Valid VehicleDto vehicleDto){
        VehicleModel vehicle = new VehicleModel();
        BeanUtils.copyProperties(vehicleDto,vehicle);
        VehicleModel created = vehicleService.addVehicles(vehicle);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<VehicleModel> updateVehicle(@PathVariable(value = "id") Long id, @RequestBody @Valid VehicleDto vehicleDto) {
        VehicleModel updatedVehicle = vehicleService.updateVehicle(id, vehicleDto);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteVehicle (@PathVariable(value = "id")Long id){
        VehicleModel vehicle = vehicleService.deleteVehicle(id);
        return ResponseEntity.status(HttpStatus.OK).body("Veículo de placa" + vehicle.getLicensePlate() + " deletado com sucesso.");
    }
}
