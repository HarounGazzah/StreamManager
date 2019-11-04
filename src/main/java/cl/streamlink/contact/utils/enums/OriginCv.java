package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public enum OriginCv {
    INDIFFERENT,
    LINKEDIN,
    APPOINTMENT,
    SPONTANEOUS_APPLICATION,
    LIVING_ROOM,
    JOB_BOARD,
    OTHER;

    @JsonCreator
    public static OriginCv fromString(final String value) {
        return value != null
                ? Arrays.stream(values())
                .filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase())).findFirst()
                .orElse(APPOINTMENT)
                : APPOINTMENT;
    }

    public static List<OriginCv> getAll() {
        return Arrays.asList(values());
    }
}
