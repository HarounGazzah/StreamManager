package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum ContractClassification {

    NOT_DEFINED,
    POSITION11_COEFFICIENT95,
    POSITION12_COEFFICIENT100,
    POSITION21_COEFFICIENT105,
    POSITION21_COEFFICIENT115,
    POSITION22_COEFFICIENT130,
    POSITION23_COEFFICIENT150,
    POSITION31_COEFFICIENT170,
    POSITION32_COEFFICIENT210,
    POSITION33_COEFFICIENT270,
    POSITION11_COEFFICIENT200,
    POSITION12_COEFFICIENT210,
    POSITION131_COEFFICIENT220,
    POSITION132_COEFFICIENT230,
    POSITION141_COEFFICIENT240,
    POSITION142_COEFFICIENT250,
    POSITION21_COEFFICIENT275,
    POSITION22_COEFFICIENT310,
    POSITION23_COEFFICIENT355,
    POSITION31_COEFFICIENT400;

    @JsonCreator
    public static ContractClassification fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(NOT_DEFINED)
                : NOT_DEFINED;
    }
}
