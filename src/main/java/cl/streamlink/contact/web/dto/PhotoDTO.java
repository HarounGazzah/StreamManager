package cl.streamlink.contact.web.dto;

public class PhotoDTO {

    private String reference;

    private String label;

    private String name;

    private String userReference;

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

    public String getUserReference() {
        return userReference;
    }

    public void setUserReference(String userReference) {
        this.userReference = userReference;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
