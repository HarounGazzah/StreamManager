package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;


public enum Stage {

    ToTreat,
    InTheProcessOfQualifying,
    WaitingQualifiedManager,
    Vivier,
    VivierPlus,
    StopContacting,
    ConvertedToResource;

    @JsonCreator
    public static Stage fromString(final String stage) {
        return stage != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), stage.toUpperCase()))
                        .findFirst().orElse(ToTreat)
                : ToTreat;
    }


    public static List<Stage> getAll() {
        return Arrays.asList(values());
    }

}
