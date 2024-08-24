package car_rent.api.services.specification;

import car_rent.api.models.CustomerModel;
import org.springframework.data.jpa.domain.Specification;

public class CustomerScpecification {
    public static Specification<CustomerModel> hasName(String name){
        return ((root, query, criteriaBuilder) ->{
            if (name == null) return null;
            else return  criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() +"%");
        });
    }

    public static Specification<CustomerModel> hasPhone(String phone){
        return ((root, query, criteriaBuilder) ->{
            if (phone == null) return null;
            else return  criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + phone.toLowerCase() +"%");
        });
    }

    public static Specification<CustomerModel> hasEmail(String email){
        return ((root, query, criteriaBuilder) ->{
            if (email == null) return null;
            else return  criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + email.toLowerCase() +"%");
        });
    }


}
