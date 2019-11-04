package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.BillStage;
import cl.streamlink.contact.utils.enums.Currency;
import cl.streamlink.contact.utils.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BillDTO {
    private String reference;
    private String title;
    private String managerReference;
    private String societyContactReference;
    private String societyReference;
    private String resourceReference;
    private String resourceFullName;
    private String projectReference;
    private String projectName;
    private String societyName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String state;
    private LocalDate billDate;
    private LocalDate billStartDate;
    private LocalDate billEndDate;
    private LocalDate scheduledDate;
    private LocalDate receiptDate;
    private BigDecimal tva;
    private PaymentType paymentType;
    private BillStage billStage;
    private Currency currency;
    private float discountRate;
    private String note;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal total;
    private BigDecimal totalTtc;


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getManagerReference() {
        return managerReference;
    }

    public void setManagerReference(String managerReference) {
        this.managerReference = managerReference;
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

    public String getSocietyContactReference() {
        return societyContactReference;
    }

    public void setSocietyContactReference(String societyContactReference) {
        this.societyContactReference = societyContactReference;
    }

    public String getSocietyReference() {
        return societyReference;
    }

    public void setSocietyReference(String societyReference) {
        this.societyReference = societyReference;
    }

    public String getResourceFullName() {
        return resourceFullName;
    }

    public void setResourceFullName(String resourceFullName) {
        this.resourceFullName = resourceFullName;
    }

    public String getProjectReference() {
        return projectReference;
    }

    public void setProjectReference(String projectReference) {
        this.projectReference = projectReference;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getResourceReference() {
        return resourceReference;
    }

    public void setResourceReference(String resourceReference) {
        this.resourceReference = resourceReference;
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

    public BigDecimal getTotal() {
        return this.quantity.multiply(this.unitPrice);
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotalTtc() {
        return ((this.getTotal().multiply(this.getTva()))
                .divide(BigDecimal.valueOf(100))).add(this.getTotal());
    }

    public void setTotalTtc(BigDecimal totalTtc) {
        this.totalTtc = totalTtc;
    }
}
