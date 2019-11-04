package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.enums.BillStage;
import cl.streamlink.contact.utils.enums.Currency;
import cl.streamlink.contact.utils.enums.PaymentType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(indexes = {@Index(name = "index_bill_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String reference;

    private String title;

    @ManyToOne
    private User manager;

    @ManyToOne(optional = false)
    private Resource resource;

    @ManyToOne(optional = false)
    private ProjectPos projectPos;

    @ManyToOne(optional = false)
    private SocietyContact societyContact;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    private String state;

    private LocalDate billDate;

    private LocalDate billStartDate;

    private LocalDate billEndDate;

    private LocalDate scheduledDate;

    private LocalDate receiptDate;

    private BigDecimal tva;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private BillStage billStage;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private float discountRate;

    private String note;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public LocalDate getBillStartDate() {
        return billStartDate;
    }

    public void setBillStartDate(LocalDate billStartDate) {
        this.billStartDate = billStartDate;
    }

    public LocalDate getBillEndDate() {
        return billEndDate;
    }

    public void setBillEndDate(LocalDate billEndDate) {
        this.billEndDate = billEndDate;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    public BigDecimal getTva() {
        return tva;
    }

    public void setTva(BigDecimal tva) {
        this.tva = tva;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public BillStage getBillStage() {
        return billStage;
    }

    public void setBillStage(BillStage billStage) {
        this.billStage = billStage;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public ProjectPos getProjectPos() {
        return projectPos;
    }

    public void setProjectPos(ProjectPos projectPos) {
        this.projectPos = projectPos;
    }

    public SocietyContact getSocietyContact() {
        return societyContact;
    }

    public void setSocietyContact(SocietyContact societyContact) {
        this.societyContact = societyContact;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
