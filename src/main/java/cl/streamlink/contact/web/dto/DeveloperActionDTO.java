package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.ActionType;

import java.time.LocalDateTime;


public class DeveloperActionDTO {

    private String reference;

    private String developerReference;

    private String responsibleReference;

    private String responsibleFullName;

    private ActionType type;

    private String note;

    private LocalDateTime date;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDeveloperReference() {
        return developerReference;
    }

    public void setDeveloperReference(String developerReference) {
        this.developerReference = developerReference;
    }

    public String getResponsibleReference() {
        return responsibleReference;
    }

    public void setResponsibleReference(String responsibleReference) {
        this.responsibleReference = responsibleReference;
    }

    public String getResponsibleFullName() {
        return responsibleFullName;
    }

    public void setResponsibleFullName(String responsibleFullName) {
        this.responsibleFullName = responsibleFullName;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
