package cl.streamlink.contact.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LanguageDTO {

    private String reference;

    @NotNull
    @Size(min = 2, max = 255)
    private String name;

    private String description;


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
