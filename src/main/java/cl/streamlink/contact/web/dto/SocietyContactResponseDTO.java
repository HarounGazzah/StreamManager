package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.Gender;
import cl.streamlink.contact.utils.enums.SocietyStage;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.time.LocalDateTime;

public class SocietyContactResponseDTO {

    private String reference;

    private String firstname;

    private String lastname;

    private String titre;

    private String service;

    private SocietyStage stage;

    private Gender gender;

    private String managerReference;

    private String societyReference;

    private String technicalScope;

    private String note;

    private String functionalScope;

    @JsonUnwrapped
    private ContactDTO contact;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getManagerReference() {
        return managerReference;
    }

    public void setManagerReference(String managerReference) {
        this.managerReference = managerReference;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFunctionalScope() {
        return functionalScope;
    }

    public void setFunctionalScope(String functionalScope) {
        this.functionalScope = functionalScope;
    }

    public ContactDTO getContact() {
        return contact;
    }

    public void setContact(ContactDTO contact) {
        this.contact = contact;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
