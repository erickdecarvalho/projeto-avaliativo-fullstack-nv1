package car_rent.api.shared.utils;

import java.util.function.Function;

public class Messages {
    public static final Function<String, String> NOT_FOUND = field -> field + " não encontrado.";

    public static String notFount(String entity) {
        return NOT_FOUND.apply(entity);
    }
}
