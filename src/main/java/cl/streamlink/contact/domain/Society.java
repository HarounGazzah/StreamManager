package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.ActivityArea;
import cl.streamlink.contact.utils.enums.SocietyStage;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(indexes = {@Index(name = "index_society_reference", columnList = "reference", unique = true)})
public class Society extends AbstractProfile {

    @NotNull
    private String label;

    private Integer staffNumber;

    private String SupplierNumber;


    @Enumerated(EnumType.STRING)
    private SocietyStage stage;

    @Enumerated(EnumType.STRING)
    private ActivityArea activityArea;

    @Embedded
    private SocietyLegalInformation legalInformation = new SocietyLegalInformation();

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> services = new ArrayList<String>();


    public String getLabel() {
        return label;
    }
    @Embedded
    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getSupplierNumber() {
        return SupplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        SupplierNumber = supplierNumber;
    }

    public SocietyStage getStage() {
        return stage;
    }

    public void setStage(SocietyStage stage) {
        this.stage = stage;
    }

    public ActivityArea getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(ActivityArea activityArea) {
        this.activityArea = activityArea;
    }

    public SocietyLegalInformation getLegalInformation() {
        if (legalInformation == null)
            legalInformation = new SocietyLegalInformation();
        return legalInformation;
    }

    public void setLegalInformation(SocietyLegalInformation legalInformation) {
        this.legalInformation = legalInformation;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    @Override
    public boolean equals(Object object) {
        return Optional.ofNullable(object).filter(obj -> obj instanceof Society).map(obj -> (Society) obj).
                filter(ag -> getId() == null || MiscUtils.equals(ag.getReference(), this.getReference())).
                filter(ag -> getId() != null || MiscUtils.equals(ag, this)).
                isPresent();
    }

    @Override
    public int hashCode() {
        if (this.getReference() != null)
            return this.getReference().hashCode();
        else if (this.getId() != null)
            return this.getId().hashCode();
        else
            return super.hashCode();
    }
}
