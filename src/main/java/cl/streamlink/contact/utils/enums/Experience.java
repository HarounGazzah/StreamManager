package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;


public enum Experience {

    NOT_DEFINED,
    NON,
    BETWEEN1AND2,
    BETWEEN3AND5,
    BETWEEN6AND10,
    MORE_THAN_10;

    @JsonCreator
    public static Experience fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(NOT_DEFINED)
                : NOT_DEFINED;
    }

    public static List<Experience> getAll() {
        return Arrays.asList(values());
    }
}
