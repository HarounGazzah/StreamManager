package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class ContractDTO {

    private String developerReference;

    private String responsibleReference;

    private String reference;

    private Currency currency;

    private BigDecimal salary;

    private ContractType type;

    private WorkTime workTime;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate trialPeriodEndDate;

    private LocalDate trialPeriodRenewalEndDate;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private BigDecimal cjm;

    private BigDecimal coefficient;

    private BigDecimal businessDays;

    private ContractCategory contractCategory;

    private ContractClassification contractClassification;

    public String getDeveloperReference() {
        return developerReference;
    }

    public void setDeveloperReference(String developerReference) {
        this.developerReference = developerReference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getResponsibleReference() {
        return responsibleReference;
    }

    public void setResponsibleReference(String responsibleReference) {
        this.responsibleReference = responsibleReference;
    }

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
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

    public LocalDate getTrialPeriodEndDate() {
        return trialPeriodEndDate;
    }

    public void setTrialPeriodEndDate(LocalDate trialPeriodEndDate) {
        this.trialPeriodEndDate = trialPeriodEndDate;
    }

    public LocalDate getTrialPeriodRenewalEndDate() {
        return trialPeriodRenewalEndDate;
    }

    public void setTrialPeriodRenewalEndDate(LocalDate trialPeriodRenewalEndDate) {
        this.trialPeriodRenewalEndDate = trialPeriodRenewalEndDate;
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

    public BigDecimal getCjm() {
        return cjm;
    }

    public void setCjm(BigDecimal cjm) {
        this.cjm = cjm;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public BigDecimal getBusinessDays() {
        return businessDays;
    }

    public void setBusinessDays(BigDecimal businessDays) {
        this.businessDays = businessDays;
    }

    public ContractCategory getContractCategory() {
        return contractCategory;
    }

    public void setContractCategory(ContractCategory contractCategory) {
        this.contractCategory = contractCategory;
    }

    public ContractClassification getContractClassification() {
        return contractClassification;
    }

    public void setContractClassification(ContractClassification contractClassification) {
        this.contractClassification = contractClassification;
    }
}
