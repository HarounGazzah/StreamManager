package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HireAbilityProcessingError {

    @JsonAlias(value = "Error")
    private List<HireabilityError> errors = new ArrayList<>();

    public List<HireabilityError> getErrors() {
        return errors;
    }

    public void setErrors(List<HireabilityError> errors) {
        this.errors = errors;
    }
}
