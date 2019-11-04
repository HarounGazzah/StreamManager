package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.enums.ActionType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(indexes = {@Index(name = "index_action_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    @ManyToOne
    private Developer developer;


    @ManyToOne
    private SocietyContact societyContact;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Need need;


    @ManyToOne(optional = false)
    private User responsible;

    @Enumerated(EnumType.STRING)
    private ActionType type;

    @Lob
    private String note;

    private LocalDateTime date;

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

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public SocietyContact getSocietyContact() {
        return societyContact;
    }

    public void setSocietyContact(SocietyContact societyContact) {
        this.societyContact = societyContact;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public Need getNeed() {
        return need;
    }

    public void setNeed(Need need) {
        this.need = need;
    }
}
