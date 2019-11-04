package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.enums.Experience;
import cl.streamlink.contact.utils.enums.Formation;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Embeddable
public class SkillsInformation {

    private String title;

    @Lob
    private String languages;

    @ElementCollection()
    @LazyCollection(LazyCollectionOption.FALSE)
    @Lob
    private List<String> qualifications = new ArrayList<String>();

    @Enumerated(EnumType.STRING)
    private Experience experience = Experience.NOT_DEFINED;

    @Enumerated(EnumType.STRING)
    private Formation formation = Formation.NOT_DEFINED;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public List<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}
