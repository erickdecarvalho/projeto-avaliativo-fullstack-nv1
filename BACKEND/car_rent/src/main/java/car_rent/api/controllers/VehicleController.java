package car_rent.api.controllers;

import car_rent.api.models.TypeVehicleModel;
import car_rent.api.models.VehicleModel;
import car_rent.api.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleModel>> getVehicles(
            @RequestParam(value = "type", required = false)     TypeVehicleModel type,
            @RequestParam(value = "minYear", required = false)  Integer minYear,
            @RequestParam(value = "maxYear", required = false)  Integer maxYear,
            @RequestParam(value = "color", required = false)    String color,
            @RequestParam(value = "rented", required = false)   Boolean rented,
            @RequestParam(value = "officeName",required = false)String officeName
    ){
    return ResponseEntity.status(HttpStatus.OK)
            .body(vehicleService.getVehicles(type, minYear, maxYear,color, rented, officeName));

    }

}
