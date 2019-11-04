package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.ProjectStage;
import cl.streamlink.contact.utils.enums.ProjectType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(indexes = {@Index(name = "index_project_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
//ext pos
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    private String title;

    @Enumerated(EnumType.STRING)
    private ProjectType type;

    @Enumerated(EnumType.STRING)
    private ProjectStage stage;

    @ManyToOne(optional = false)
    private SocietyContact societyContact;

    @ManyToOne(optional = true)
    @JoinColumn(name = "resource_id", nullable = true)
    private Resource resource;

    @ManyToOne
    private User rh;

    @ManyToOne
    private User manager;

    @Lob
    private String note;

    @Embedded
    private ProjectInformation projectInformation = new ProjectInformation();

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

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }

    public ProjectStage getStage() {
        return stage;
    }

    public void setStage(ProjectStage stage) {
        this.stage = stage;
    }

    public User getRh() {
        return rh;
    }

    public void setRh(User rh) {
        this.rh = rh;
    }

    public SocietyContact getSocietyContact() {
        return societyContact;
    }

    public void setSocietyContact(SocietyContact societyContact) {
        this.societyContact = societyContact;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ProjectInformation getProjectInformation() {

        if (projectInformation == null)
            projectInformation = new ProjectInformation();
        return projectInformation;
    }

    public void setProjectInformation(ProjectInformation projectInformation) {
        this.projectInformation = projectInformation;
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
        return Optional.ofNullable(object).filter(obj -> obj instanceof Project).map(obj -> (Project) obj)
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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

}
