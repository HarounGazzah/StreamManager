package cl.streamlink.contact.web.dto;

import java.util.Date;

public class ActionDTO {
    private String reference;
    private String developerReference;
    private String projectReference;
    private String societyContactReference;
    private String responsibleFullName;
    private String societyContactFullName;
    private String responsibleReference;
    private String type;
    private String note;
    private Date date;
    private Date createdDate;
    private Date modifiedDate;

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

    public String getProjectReference() {
        return projectReference;
    }

    public void setProjectReference(String projectReference) {
        this.projectReference = projectReference;
    }

    public String getSocietyContactReference() {
        return societyContactReference;
    }

    public void setSocietyContactReference(String societyContactReference) {
        this.societyContactReference = societyContactReference;
    }

    public String getResponsibleFullName() {
        return responsibleFullName;
    }

    public void setResponsibleFullName(String responsibleFullName) {
        this.responsibleFullName = responsibleFullName;
    }

    public String getSocietyContactFullName() {
        return societyContactFullName;
    }

    public void setSocietyContactFullName(String societyContactFullName) {
        this.societyContactFullName = societyContactFullName;
    }

    public String getResponsibleReference() {
        return responsibleReference;
    }

    public void setResponsibleReference(String responsibleReference) {
        this.responsibleReference = responsibleReference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
