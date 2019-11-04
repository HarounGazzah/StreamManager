package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public enum SocietyStage {

    Prospect,
    Customer,
    Partner,
    Provider,
    Archive;

    @JsonCreator
    public static SocietyStage fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(Customer)
                : Customer;
    }

    public static List<SocietyStage> getAll() {
        return Arrays.asList(values());
    }
}
