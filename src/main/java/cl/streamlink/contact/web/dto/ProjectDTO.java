package cl.streamlink.contact.web.dto;


import cl.streamlink.contact.utils.enums.ProjectStage;
import cl.streamlink.contact.utils.enums.ProjectType;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class ProjectDTO {

    private String reference;

    private String title;

    private String managerReference;

    @NotEmpty
    private String societyContactReference;

    private String societyReference;

    private ProjectType type;

    private ProjectStage stage;

    private String rhReference;

    private String note;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

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

    public String getManagerReference() {
        return managerReference;
    }

    public void setManagerReference(String managerReference) {
        this.managerReference = managerReference;
    }

    public String getSocietyContactReference() {
        return societyContactReference;
    }

    public void setSocietyContactReference(String societyContactReference) {
        this.societyContactReference = societyContactReference;
    }

    public String getSocietyReference() {
        return societyReference;
    }

    public void setSocietyReference(String societyReference) {
        this.societyReference = societyReference;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }

    public ProjectStage getStage() {
        return stage;
    }

    public void setStage(ProjectStage stage) {
        this.stage = stage;
    }

    public String getRhReference() {
        return rhReference;
    }

    public void setRhReference(String rhReference) {
        this.rhReference = rhReference;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
