package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;


public enum Formation {

    NOT_DEFINED,
    BAC,
    BAC_PLUS_2,
    BAC_PLUS_3,
    BAC_PLUS_4,
    BAC_PLUS_5,
    BAC_PLUS_6,
    BAC_PLUS_7,
    BAC_PLUS_8;

    @JsonCreator
    public static Formation fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(NOT_DEFINED)
                : NOT_DEFINED;
    }

    public static List<Formation> getAll() {
        return Arrays.asList(values());
    }
}
