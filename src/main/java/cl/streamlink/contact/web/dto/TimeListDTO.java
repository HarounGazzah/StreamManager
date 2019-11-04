package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.TimeListStage;

import java.time.LocalDate;
import java.util.Date;

public class TimeListDTO {

    private String reference;

    private TimeListStage stage;

    private String note;

    private LocalDate date;

    private boolean closing;

    private String resourceReference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public TimeListStage getStage() {
        return stage;
    }

    public void setStage(TimeListStage stage) {
        this.stage = stage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isClosing() {
        return closing;
    }

    public void setClosing(boolean closing) {
        this.closing = closing;
    }

    public String getResourceReference() {
        return resourceReference;
    }

    public void setResourceReference(String resourceReference) {
        this.resourceReference = resourceReference;
    }


}
