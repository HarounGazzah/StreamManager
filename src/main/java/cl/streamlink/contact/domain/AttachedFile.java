package cl.streamlink.contact.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(indexes = {@Index(name = "index_attachedFile _reference", columnList = "reference", unique = true)})
public class AttachedFile  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    private String label;

    private String name;

    private String note;




    @ManyToOne
    private Resource resource;

    @ManyToOne
    private TimeList timeList;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }


    public TimeList getTimeList() {
        return timeList;
    }

    public void setTimeList(TimeList timeList) {
        this.timeList = timeList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
