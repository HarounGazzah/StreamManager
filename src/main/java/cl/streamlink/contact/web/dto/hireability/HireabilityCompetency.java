package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HireabilityCompetency {

    @JsonAlias(value = "CompetencyLevel")
    private String competencyLevel;

    @JsonAlias(value = "CompetencyName")
    private String competencyName;

    public String getCompetencyLevel() {
        return competencyLevel;
    }

    public void setCompetencyLevel(String competencyLevel) {
        this.competencyLevel = competencyLevel;
    }

    public String getCompetencyName() {
        return competencyName;
    }

    public void setCompetencyName(String competencyName) {
        this.competencyName = competencyName;
    }
}
