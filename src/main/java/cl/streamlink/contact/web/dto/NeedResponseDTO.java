package cl.streamlink.contact.web.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import cl.streamlink.contact.domain.SocietyContact;
import cl.streamlink.contact.domain.User;
import cl.streamlink.contact.utils.enums.NeedStage;
import cl.streamlink.contact.utils.enums.NeedType;

public class NeedResponseDTO {

    private String reference;

    private String title;

    private User manager;

    private NeedType type;

    private NeedStage stage;

    private SocietyContact societyContact;

    private User rh;

    private String note;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @JsonUnwrapped
    private NeedInformationDTO needInformation;

    public SocietyContact getSocietyContact() {
        return societyContact;
    }

    public void setSocietyContact(SocietyContact societyContact) {
        this.societyContact = societyContact;
    }

    public User getRh() {
        return rh;
    }

    public void setRh(User rh) {
        this.rh = rh;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public NeedType getType() {
        return type;
    }

    public void setType(NeedType type) {
        this.type = type;
    }

    public NeedStage getStage() {
        return stage;
    }

    public void setStage(NeedStage stage) {
        this.stage = stage;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getClient() {
        return societyContact.getSociety().getLabel().concat(" - ")
                .concat(societyContact.getFirstname()).concat(" ").concat(societyContact.getLastname());
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

    public NeedInformationDTO getNeedInformation() {
        return needInformation;
    }

    public void setNeedInformation(NeedInformationDTO needInformation) {
        this.needInformation = needInformation;
    }

}
