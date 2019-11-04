package cl.streamlink.contact.domain;


import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.SocietyStage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
@Table(indexes = {@Index(name = "index_society_contact_reference", columnList = "reference", unique = true)})
public class SocietyContact extends AbstractProfile {

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String title;

    private String service;

    @Enumerated(EnumType.STRING)
    private SocietyStage stage;

    @ManyToOne(optional = false)
    private Society society;

    @Lob
    private String technicalScope;

    @Lob
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

    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
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


    @Override
    public boolean equals(Object object) {
        return Optional.ofNullable(object).filter(obj -> obj instanceof SocietyContact).map(obj -> (SocietyContact) obj).
                filter(ag -> getId() == null || MiscUtils.equals(ag.getReference(), this.getReference())).
                filter(ag -> getId() != null || MiscUtils.equals(ag, this)).
                isPresent();
    }

    @Override
    public int hashCode() {
        if (this.getReference() != null)
            return this.getReference().hashCode();
        else if (this.getId() != null)
            return this.getId().hashCode();
        else
            return super.hashCode();
    }
}
