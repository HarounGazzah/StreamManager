package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    @JsonAlias(value = "HireAbilityJSONResults")
    private List<HireAbilityJSONResults> hireAbilityJSONResults;


    public List<HireAbilityJSONResults> getHireAbilityJSONResults() {
        return hireAbilityJSONResults;
    }

    public void setHireAbilityJSONResults(List<HireAbilityJSONResults> hireAbilityJSONResults) {
        this.hireAbilityJSONResults = hireAbilityJSONResults;
    }
}
