package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public enum ProjectType {

    Authority,
    FlatRate,
    InternalProject,
    Product,
    Recruitment;

    @JsonCreator
    public static ProjectType fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(Authority)
                : Authority;
    }

    public static List<ProjectType> getAll() {
        return Arrays.asList(values());
    }
}
