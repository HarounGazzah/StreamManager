package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.Stage;
import cl.streamlink.contact.utils.MiscUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDTO extends AbstractProfileDTO {

    @NotNull
    @Size(min = 2, max = 255)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 255)
    private String lastname;

    private Stage stage;

    private String rhReference;


    private LocalDate availability;

    private boolean resource;

    private List<String> mobility;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getRhReference() {
        return rhReference;
    }

    public void setRhReference(String rhReference) {
        this.rhReference = rhReference;
    }

    public LocalDate getAvailability() {
        return availability;
    }

    public void setAvailability(LocalDate availability) {
        this.availability = availability;
    }

    public List<String> getMobility() {
        if (MiscUtils.isEmpty(mobility))
            return new ArrayList<>();
        return mobility;
    }

    public void setMobility(List<String> mobility) {
        this.mobility = mobility;
    }

    public boolean isResource() {
        return resource;
    }

    public void setResource(boolean resource) {
        this.resource = resource;
    }
}
