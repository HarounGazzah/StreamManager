package cl.streamlink.contact.domain;


import cl.streamlink.contact.utils.enums.ResourceStage;
import cl.streamlink.contact.utils.enums.ResourceType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
@DiscriminatorValue("RESOURCE")
public class Resource extends Developer {


    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    @Enumerated(EnumType.STRING)
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
}
