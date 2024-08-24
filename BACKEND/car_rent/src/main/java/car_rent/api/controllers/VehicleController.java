package car_rent.api.controllers;

import car_rent.api.dtos.VehicleDto;
import car_rent.api.models.CustomerModel;
import car_rent.api.models.TypeVehicleModel;
import car_rent.api.models.VehicleModel;
import car_rent.api.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleModel>> getVehicles(
            @RequestParam(value = "type", required = false) TypeVehicleModel type,
            @RequestParam(value = "minYear", required = false) Integer minYear,
            @RequestParam(value = "maxYear", required = false) Integer maxYear,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "rented", required = false) Boolean rented,
            @RequestParam(value = "officeName",required = false)String officeName,
            @RequestParam(value = "page",defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size",defaultValue = "5", required = false) Integer size

            ){
        Pageable pageable = PageRequest.of(page, size);
    return ResponseEntity.status(HttpStatus.OK)
            .body(vehicleService.getVehicles(type, minYear, maxYear,color, rented, pageable).getContent());

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VehicleModel> getVehicleById (@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getVehicleByID(id));
    }

    @PostMapping
    public ResponseEntity<VehicleModel> addVehicle (@RequestBody @Valid VehicleDto vehicleDto){
        VehicleModel vehicle = new VehicleModel();
        BeanUtils.copyProperties(vehicleDto,vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.addVehicles(vehicle));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<VehicleModel> updateVehicle (@PathVariable(value = "id") Long id, @RequestBody @Valid VehicleDto vehicleDto){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.updateVehicle(id, vehicleDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteVehicle (@PathVariable(value = "id")Long id){
        VehicleModel vehicle = vehicleService.deleteVehicle(id);

        return ResponseEntity.status(HttpStatus.OK).body("Vehicle with id: " + vehicle.getId() + " deleted!");

    }


}
