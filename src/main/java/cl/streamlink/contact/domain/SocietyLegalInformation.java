package cl.streamlink.contact.domain;

import javax.persistence.Embeddable;

@Embeddable
public class SocietyLegalInformation {

    private String tva;
    private String siret;
    private String legalStatus;
    private String rcs;
    private String apeCode;

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(String legalStatus) {
        this.legalStatus = legalStatus;
    }

    public String getRcs() {
        return rcs;
    }

    public void setRcs(String rcs) {
        this.rcs = rcs;
    }

    public String getApeCode() {
        return apeCode;
    }

    public void setApeCode(String apeCode) {
        this.apeCode = apeCode;
    }
}
