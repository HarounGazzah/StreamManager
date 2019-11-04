package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public enum ProjectStage {

    InProgress,
    Postponed,
    Won,
    Lost,
    Abandoned;

    @JsonCreator
    public static ProjectStage fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(InProgress)
                : InProgress;
    }

    public static List<ProjectStage> getAll() {
        return Arrays.asList(values());
    }
}
