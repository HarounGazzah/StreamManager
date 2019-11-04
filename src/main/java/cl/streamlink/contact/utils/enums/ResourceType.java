package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public enum ResourceType {

    NOT_DEFINED,
    InternalConsultant,
    ExternalConsultant,
    BusinessEngineer,
    AgencyManager,
    Director,
    RecruitmentOfficer,
    HRManager,
    OfficeManager,
    Accounting;

    @JsonCreator
    public static ResourceType fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(NOT_DEFINED)
                : NOT_DEFINED;
    }

    public static List<ResourceType> getAll() {
        return Arrays.asList(values());
    }

}
