package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HireAbilityDegree {

    @JsonAlias(value = {"LicenseName", "CertificationName", "Name"})
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
