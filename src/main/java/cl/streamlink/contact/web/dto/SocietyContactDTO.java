package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.SocietyStage;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SocietyContactDTO extends AbstractProfileDTO {

    @NotNull
    @Size(min = 2, max = 255)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 255)
    private String lastname;

    @NotNull
    @Size(min = 2, max = 255)
    private String title;

    @Size(max = 255)
    private String service;

    private SocietyStage stage;

    private String societyReference;

    private String technicalScope;

    private String functionalScope;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public SocietyStage getStage() {
        return stage;
    }

    public void setStage(SocietyStage stage) {
        this.stage = stage;
    }

    public String getSocietyReference() {
        return societyReference;
    }

    public void setSocietyReference(String societyReference) {
        this.societyReference = societyReference;
    }

    public String getTechnicalScope() {
        return technicalScope;
    }

    public void setTechnicalScope(String technicalScope) {
        this.technicalScope = technicalScope;
    }

    public String getFunctionalScope() {
        return functionalScope;
    }

    public void setFunctionalScope(String functionalScope) {
        this.functionalScope = functionalScope;
    }
}
