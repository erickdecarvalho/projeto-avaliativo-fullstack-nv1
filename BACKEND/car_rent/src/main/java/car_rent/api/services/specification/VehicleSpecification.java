package car_rent.api.services.specification;

import car_rent.api.models.TypeVehicleModel;
import car_rent.api.models.VehicleModel;
import org.springframework.data.jpa.domain.Specification;

public class VehicleSpecification {
    public static Specification<VehicleModel> hasType(TypeVehicleModel type){
        return ((root, query, criteriaBuilder) ->{
            if (type == null) return null;
            else return criteriaBuilder.equal(root.get("type"), type);
        });
    }

    public static Specification<VehicleModel> hasYear(Integer minYear, Integer maxYear){
        return ((root, query, criteriaBuilder) -> {
            if(minYear != null && maxYear != null) return criteriaBuilder.between(root.get("year"), minYear, maxYear);
            else if( minYear != null)    return criteriaBuilder.greaterThan(root.get("year"), minYear);
            else if (maxYear != null)   return criteriaBuilder.lessThan(root.get("year"), maxYear);
            else return null;
        });
    }

    public static Specification<VehicleModel> hasColor(String color){
        return ((root, query, criteriaBuilder) ->
            color == null? null:
                criteriaBuilder.like(criteriaBuilder.lower(root.get("color")), "%" + color.toLowerCase() +"%"));
    }



    public static Specification<VehicleModel> hasRented(Boolean rented){
        return ((root, query, criteriaBuilder) ->{
            if (rented == null) return null;
            else return criteriaBuilder.equal(root.get("rented"), rented);
        });

    }
}
