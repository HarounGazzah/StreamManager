package cl.streamlink.contact.utils.enums;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

import cl.streamlink.contact.utils.MiscUtils;

public enum NeedType {

    Authority,
    FlatRate,
    InternalProject,
    Product,
    Recruitment;

    @JsonCreator
    public static NeedType fromString(final String value) {
        return value != null
                ? Arrays.stream(values())
                .filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase())).findFirst()
                .orElse(Authority)
                : Authority;
    }

    public static List<NeedType> getAll() {
        return Arrays.asList(values());
    }
}
