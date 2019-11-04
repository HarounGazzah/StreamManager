package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.TimeLineType;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class TimeLineDTO {

    private String reference;

    private String timeListReference;

    private String project;

    private String note;

    private float timeWork;

    private LocalDate start;

    private String resourceReference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTimeListReference() {
        return timeListReference;
    }

    public void setTimeListReference(String timeListReference) {
        this.timeListReference = timeListReference;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(float timeWork) {
        this.timeWork = timeWork;
    }


    public LocalDate getStart() {

        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public String getResourceReference() {
        return resourceReference;
    }

    public void setResourceReference(String resourceReference) {
        this.resourceReference = resourceReference;
    }

    @Override
    public String toString() {
        return "TimeLineDTO{" +
                "reference='" + reference + '\'' +
                ", timeListReference='" + timeListReference + '\'' +
                ", project='" + project + '\'' +
                ", note='" + note + '\'' +
                ", timeWork=" + timeWork +
                ", start=" + start +
                ", resourceReference='" + resourceReference + '\'' +
                '}';
    }
}
