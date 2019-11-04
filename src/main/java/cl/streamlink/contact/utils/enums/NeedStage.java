package cl.streamlink.contact.utils.enums;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

import cl.streamlink.contact.utils.MiscUtils;

public enum NeedStage {

    InProgress,
    Postponed,
    Won,
    Lost,
    Abandoned;

    @JsonCreator
    public static NeedStage fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(InProgress)
                : InProgress;
    }

    public static List<NeedStage> getAll() {
        return Arrays.asList(values());
    }
}
