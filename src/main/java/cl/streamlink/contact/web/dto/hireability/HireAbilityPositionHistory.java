package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HireAbilityPositionHistory {

    @JsonAlias(value = "Employer")
    private String employer;

    @JsonAlias(value = "PositionTitle")
    private String positionTitle;


    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }
}
