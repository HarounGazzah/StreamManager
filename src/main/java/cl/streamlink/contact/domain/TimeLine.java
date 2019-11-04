package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.enums.TimeLineType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(indexes = {@Index(name = "index_time_line_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class TimeLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    private String note;


    @ManyToOne
    private TimeList listTemps;

    private float timeWork;

    private String project;

    private LocalDate start;
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

    public TimeList getListTemps() {
        return listTemps;
    }

    public void setListTemps(TimeList listTemps) {
        this.listTemps = listTemps;
    }

    public float getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(float timeWork) {
        this.timeWork = timeWork;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
