package cl.streamlink.contact.domain;


import cl.streamlink.contact.utils.enums.DurationType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(indexes = {@Index(name = "index_time_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    private LocalDate day;


    @Enumerated(EnumType.STRING)
    private DurationType duration;

    @ManyToOne
    private TimeLine timeLine;


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


    public TimeLine getTimeLine() {
        return timeLine;
    }


    public void setTimeLine(TimeLine timeLine) {
        this.timeLine = timeLine;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public DurationType getDuration() {
        return duration;
    }

    public void setDuration(DurationType duration) {
        this.duration = duration;
    }
}
