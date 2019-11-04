package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.enums.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(indexes = {@Index(name = "index_contract_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    @OneToOne(optional = false)
    private Developer developer;

    @ManyToOne(optional = false)
    private User responsible;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private ContractType type;

    @Enumerated(EnumType.STRING)
    private WorkTime workTime;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate trialPeriodEndDate;

    private LocalDate trialPeriodRenewalEndDate;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    private BigDecimal coefficient;

    private BigDecimal businessDays;

    @Enumerated(EnumType.STRING)
    private ContractCategory contractCategory;

    @Enumerated(EnumType.STRING)
    private ContractClassification contractClassification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
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
