package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.enums.ContractType;
import cl.streamlink.contact.utils.enums.Currency;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(indexes = {@Index(name = "index_wished_contract_reference", columnList = "reference", unique = true)})
public class WishedContract {

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

    private BigDecimal currentSalary;

    @Enumerated(EnumType.STRING)
    private ContractType type;

    private BigDecimal wishedSalaryMax;

    private BigDecimal wishedSalaryMin;

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

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
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
