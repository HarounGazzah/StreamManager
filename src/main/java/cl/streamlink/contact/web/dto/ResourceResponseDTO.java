package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.ResourceStage;
import cl.streamlink.contact.utils.enums.ResourceType;

public class ResourceResponseDTO extends DeveloperResponseDTO {

    private String registrationNumber;

    private ResourceType resourceType;

    private ResourceStage resourceStage;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceStage getResourceStage() {
        return resourceStage;
    }

    public void setResourceStage(ResourceStage resourceStage) {
        this.resourceStage = resourceStage;
    }

    @Override
    public boolean isResource() {
        return true;
    }
}
