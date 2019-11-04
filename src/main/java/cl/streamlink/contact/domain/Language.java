package cl.streamlink.contact.domain;


import cl.streamlink.contact.utils.MiscUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;

@Entity
@Table(indexes = {@Index(name = "index_language_reference", columnList = "reference", unique = true)})
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    @NotNull
    private String name;

    private String description;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        return Optional.ofNullable(object).filter(obj -> obj instanceof Developer).map(obj -> (Developer) obj).
                filter(ag -> getId() == null || MiscUtils.equals(ag.getReference(), this.reference)).
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
