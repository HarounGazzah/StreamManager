package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public enum TimeLineType {

    Absence, Intern, Project;
//    Janvier, Février,avril	,Mars,Mai ,Juin, Juillet, Août ,Septembre ,Octobre, Novembre,Décembre;





   /* @JsonCreator
    public static TimeLineType fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(Absence)
                : Absence;
    }*/


    @JsonCreator
    public static TimeLineType fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(Absence)
                : Absence;
    }

    public static List< TimeLineType> getAll() {
        return Arrays.asList(values());
    }
}
