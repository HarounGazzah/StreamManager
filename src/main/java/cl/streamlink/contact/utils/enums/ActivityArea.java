package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public enum ActivityArea {

    Other,
    Aerospace,
    Agri,
    Insurance,
    Automobile,
    Bank,
    Buildings,
    Biomedical,
    Chemistry,
    Advice,
    Defense,
    SoftwarePublishing,
    Energy,
    Environment,
    Rail,
    LargeDistribution,
    Infrastructure,
    Logistics,
    Media,
    MetalSteelIndustry,
    Naval,
    Nuclear,
    OilAndGas,
    Petrochemicals,
    Pharmacy,
    Health,
    PublicSector,
    Services,
    Telecommunications;

    @JsonCreator
    public static ActivityArea fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(Other)
                : Other;
    }

    public static List<ActivityArea> getAll() {
        return Arrays.asList(values());
    }
}
