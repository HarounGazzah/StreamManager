package cl.streamlink.contact.web.dto;


public class CurriculumVitaeDTO {


    private String reference;

    private String label;

    private String name;

    private String developerReference;

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

    public String getDeveloperReference() {
        return developerReference;
    }

    public void setDeveloperReference(String developerReference) {
        this.developerReference = developerReference;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
