package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.enums.EvaluationNote;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by chemakh on 12/07/2018.
 */

@Entity
@Table(indexes = {@Index(name = "index_evaluation_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    @ManyToOne(optional = false)
    private Developer developer;

    @ManyToOne(optional = false)
    private User responsible;

    @Enumerated(EnumType.STRING)
    private EvaluationNote relational;

    @Enumerated(EnumType.STRING)
    private EvaluationNote technical;

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

    public EvaluationNote getRelational() {
        return relational;
    }

    public void setRelational(EvaluationNote relational) {
        this.relational = relational;
    }

    public EvaluationNote getTechnical() {
        return technical;
    }

    public void setTechnical(EvaluationNote technical) {
        this.technical = technical;
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
}
