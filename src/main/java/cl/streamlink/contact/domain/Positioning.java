package cl.streamlink.contact.domain;


import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.PositioningStage;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {@Index(name = "index_project_reference", columnList = "reference", unique = true)})
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
@DiscriminatorValue("POSITIONING")
public class Positioning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    @ManyToOne(optional = false)
    private Resource resource;

    @ManyToOne(optional = false)
    private Need need;

    @ManyToOne(optional = false)
    private User responsible;

    @Enumerated(EnumType.STRING)
    private PositioningStage stage;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal tjm;

    private BigDecimal cjm;

    private Integer invoicedDays;

    private Integer freeDays;

    @Lob
    private String note;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Need getNeed() {
        return need;
    }

    public void setNeed(Need need) {
        this.need = need;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
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

    @Override
    public boolean equals(Object object) {
        return Optional.ofNullable(object).filter(obj -> obj instanceof Positioning).map(obj -> (Positioning) obj).
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
