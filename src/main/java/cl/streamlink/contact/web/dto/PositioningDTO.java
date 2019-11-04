package cl.streamlink.contact.web.dto;


import cl.streamlink.contact.utils.enums.PositioningStage;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PositioningDTO {


    private String reference;

    @NotEmpty
    private String resourceReference;

    @NotEmpty
    private String needReference;

//    private String projectReference;

    private String responsibleReference;

    private String client;

    private String needTitle;

    private String email;

    // private String projectTitle;

    private String resourceFullName;

    private PositioningStage stage;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal tjm;

    private BigDecimal cjm;

    private Integer invoicedDays;

    private Integer freeDays;

    private String note;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private boolean project;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getResourceReference() {
        return resourceReference;
    }

    public void setResourceReference(String resourceReference) {
        this.resourceReference = resourceReference;
    }

    public String getResponsibleReference() {
        return responsibleReference;
    }

    public void setResponsibleReference(String responsibleReference) {
        this.responsibleReference = responsibleReference;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

//    public String getProjectTitle() {
//        return projectTitle;
//    }
//
//    public void setProjectTitle(String projectTitle) {
//        this.projectTitle = projectTitle;
//    }

    public String getResourceFullName() {
        return resourceFullName;
    }

    public void setResourceFullName(String resourceFullName) {
        this.resourceFullName = resourceFullName;
    }

    public PositioningStage getStage() {
        return stage;
    }

    public void setStage(PositioningStage stage) {
        this.stage = stage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTjm() {
        return tjm;
    }

    public void setTjm(BigDecimal tjm) {
        this.tjm = tjm;
    }

    public BigDecimal getCjm() {
        return cjm;
    }

    public void setCjm(BigDecimal cjm) {
        this.cjm = cjm;
    }

    public Integer getInvoicedDays() {
        return invoicedDays;
    }

    public void setInvoicedDays(Integer invoicedDays) {
        this.invoicedDays = invoicedDays;
    }

    public Integer getFreeDays() {
        return freeDays;
    }

    public void setFreeDays(Integer freeDays) {
        this.freeDays = freeDays;
    }

    public BigDecimal getPeriodCost() {
       // return cjm.multiply(BigDecimal.valueOf(this.getFreeDays() + this.getInvoicedDays()));
        return null;
    }

    public BigDecimal getPeriodCA() {
       // return tjm.multiply(BigDecimal.valueOf(this.getFreeDays() + this.getInvoicedDays()));
        return null;
    }

    public BigDecimal getPeriodMargin() {
        // return this.getPeriodCA().min(this.getPeriodCost());
        return null;
    }

    public BigDecimal getPeriodProfitability() {

//        if (getPeriodCA().equals(BigDecimal.ZERO))
//            return BigDecimal.ZERO;
//
//        return this.getPeriodMargin().divide(getPeriodCA()).multiply(BigDecimal.valueOf(100));
        return null;
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

    public String getNeedReference() {
        return needReference;
    }

    public void setNeedReference(String needReference) {
        this.needReference = needReference;
    }

    public String getNeedTitle() {
        return needTitle;
    }

    public void setNeedTitle(String needTitle) {
        this.needTitle = needTitle;
    }

//	public String getProjectReference() {
//		return projectReference;
//	}
//
//	public void setProjectReference(String projectReference) {
//		this.projectReference = projectReference;
//	}
public boolean isProject() {
    return project;
}

    public void setProject(boolean project) {
        this.project = project;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
