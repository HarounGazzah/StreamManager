package cl.streamlink.contact.domain;


import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.Stage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;



@Entity
@Table(indexes = {@Index(name = "index_developer_reference", columnList = "reference", unique = true)})
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
@DiscriminatorValue("DEVELOPER")
public class Developer extends AbstractProfile {

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Stage stage = Stage.ToTreat;

    @Embedded
    private PersonalInformation personalInformation = new PersonalInformation();

    @Embedded
    private SkillsInformation skillsInformation = new SkillsInformation();

    @ManyToOne
    private User rh;

    private LocalDate availability;

    private String mobility;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public PersonalInformation getPersonalInformation() {

        if (personalInformation == null)
            personalInformation = new PersonalInformation();
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public SkillsInformation getSkillsInformation() {
        if (skillsInformation == null)
            skillsInformation = new SkillsInformation();
        return skillsInformation;
    }

    public void setSkillsInformation(SkillsInformation skillsInformation) {
        this.skillsInformation = skillsInformation;
    }

    public User getRh() {
        return rh;
    }

    public void setRh(User rh) {
        this.rh = rh;
    }

    public LocalDate getAvailability() {
        return availability;
    }

    public void setAvailability(LocalDate availability) {
        this.availability = availability;
    }

    public String getMobility() {

        if (MiscUtils.isEmpty(mobility))
            return "";
        return mobility;
    }

    public void setMobility(String mobility) {
        this.mobility = mobility;
    }


    @Override
    public boolean equals(Object object) {
        return Optional.ofNullable(object).filter(obj -> obj instanceof Developer).map(obj -> (Developer) obj).
                filter(ag -> getId() == null || MiscUtils.equals(ag.getReference(), this.getReference())).
                filter(ag -> getId() != null || MiscUtils.equals(ag, this)).
                isPresent();
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
