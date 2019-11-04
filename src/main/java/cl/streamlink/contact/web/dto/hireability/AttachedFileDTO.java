package cl.streamlink.contact.web.dto.hireability;

public class AttachedFileDTO {


    private String reference;

    private String label;

    private String name;

    private String note;



    private String resourceReference;


    private String timeListReference;

    private String url;



    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getResourceReference() {
        return resourceReference;
    }

    public void setResourceReference(String resourceReference) {
        this.resourceReference = resourceReference;
    }

    public String getTimeListReference() {
        return timeListReference;
    }

    public void setTimeListReference(String timeListReference) {
        this.timeListReference = timeListReference;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
