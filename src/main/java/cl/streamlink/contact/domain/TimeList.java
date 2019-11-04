package cl.streamlink.contact.domain;


import cl.streamlink.contact.utils.enums.TimeListStage;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(indexes = {@Index(name = "index_time_list_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class TimeList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    private String note;

    private LocalDate date;

    private boolean closing;

    @Enumerated(EnumType.STRING)
    private TimeListStage stage;

    @ManyToOne
    private Resource resource;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isClosing() {
        return closing;
    }

    public void setClosing(boolean closing) {
        this.closing = closing;
    }

    public TimeListStage getStage() {
        return stage;
    }

    public void setStage(TimeListStage stage) {
        this.stage = stage;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeList that = (TimeList) o;
        return reference.equals(that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }
}
