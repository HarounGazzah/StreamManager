package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.ActivityArea;
import cl.streamlink.contact.utils.enums.SocietyStage;

import java.util.ArrayList;
import java.util.List;

public class SocietyDTO extends AbstractProfileDTO {

    private String label;

    private Integer staffNumber;

    private String SupplierNumber;

    private SocietyStage stage;

    private ActivityArea activityArea;

    private List<String> services = new ArrayList<String>();

    public String getLabel() {
        return label;
    }

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

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
