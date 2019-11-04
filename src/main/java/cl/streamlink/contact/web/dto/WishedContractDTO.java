package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.ContractType;
import cl.streamlink.contact.utils.enums.Currency;

import java.math.BigDecimal;


public class WishedContractDTO {

    private String developerReference;

    private String responsibleReference;

    private String reference;

    private Currency currency;

    private BigDecimal currentSalary;

    private ContractType type;

    private BigDecimal wishedSalaryMax;

    private BigDecimal wishedSalaryMin;

    public String getDeveloperReference() {
        return developerReference;
    }

    public void setDeveloperReference(String developerReference) {
        this.developerReference = developerReference;
    }

    public String getResponsibleReference() {
        return responsibleReference;
    }

    public void setResponsibleReference(String responsibleReference) {
        this.responsibleReference = responsibleReference;
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

    public BigDecimal getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(BigDecimal currentSalary) {
        this.currentSalary = currentSalary;
    }

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public BigDecimal getWishedSalaryMax() {
        return wishedSalaryMax;
    }

    public void setWishedSalaryMax(BigDecimal wishedSalaryMax) {
        this.wishedSalaryMax = wishedSalaryMax;
    }

    public BigDecimal getWishedSalaryMin() {
        return wishedSalaryMin;
    }

    public void setWishedSalaryMin(BigDecimal wishedSalaryMin) {
        this.wishedSalaryMin = wishedSalaryMin;
    }
}
