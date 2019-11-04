package cl.streamlink.contact.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(indexes = {@Index(name = "index_absence_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    private String description;
    private String type;


    private Date dateAbsence;

    private float duration ;
    private float sum ;

   @ManyToOne(optional = false , cascade=CascadeType.ALL)
   private AbsenceList absenceList;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public Date getDateAbsence() {
        return dateAbsence;
    }

    public void setDateAbsence(Date dateAbsence) {
        this.dateAbsence = dateAbsence;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public AbsenceList getAbsenceList() {
        return absenceList;
    }

    public void setAbsenceList(AbsenceList absenceList) {
        this.absenceList = absenceList;
    }
}
