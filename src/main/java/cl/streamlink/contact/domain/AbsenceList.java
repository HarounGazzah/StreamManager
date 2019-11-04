package cl.streamlink.contact.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(indexes = {@Index(name = "index_absenceList_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class AbsenceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    private String comment;
    private String subject;
    private String state;
    @ManyToOne
    private Resource resource;
    @ManyToOne
    private User manager;

    private Date absenceListDate ;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Date getAbsenceListDate() {
        return absenceListDate;
    }

    public void setAbsenceListDate(Date absenceListDate) {
        this.absenceListDate = absenceListDate;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}
