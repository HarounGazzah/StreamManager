package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.ActivityArea;
import cl.streamlink.contact.utils.enums.NeedStage;
import cl.streamlink.contact.utils.enums.NeedType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(indexes = {@Index(name = "index_need_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Need {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    private String title;

    @ManyToOne
    private User manager;

    @Enumerated(EnumType.STRING)
    private NeedType type;

    @Enumerated(EnumType.STRING)
    private NeedStage stage;

    @ManyToOne(optional = false)
    private SocietyContact societyContact;

    @ManyToOne
    private User rh;

    @Lob
    private String note;

    @Embedded
    private NeedInformation needInformation = new NeedInformation(ActivityArea.Media);

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public NeedType getType() {
        return type;
    }

    public void setType(NeedType type) {
        this.type = type;
    }

    public NeedStage getStage() {
        return stage;
    }

    public void setStage(NeedStage stage) {
        this.stage = stage;
    }

    public SocietyContact getSocietyContact() {
        return societyContact;
    }

    public void setSocietyContact(SocietyContact societyContact) {
        this.societyContact = societyContact;
    }

    public User getRh() {
        return rh;
    }

    public void setRh(User rh) {
        this.rh = rh;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public NeedInformation getNeedInformation() {
        return needInformation;
    }

    public void setNeedInformation(NeedInformation needInformation) {
        this.needInformation = needInformation;
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
        return Optional.ofNullable(object).filter(obj -> obj instanceof Need).map(obj -> (Need) obj)
                .filter(ag -> getId() == null || MiscUtils.equals(ag.getReference(), this.getReference()))
                .filter(ag -> getId() != null || MiscUtils.equals(ag, this)).isPresent();
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
