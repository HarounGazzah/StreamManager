package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.Currency;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

public class ProjectPosDTO extends PositioningDTO {

    private String country;

    private String address;

    private String city;

    private String postal;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private LocalDate presentationDate;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDate getPresentationDate() {
        return presentationDate;
    }

    public void setPresentationDate(LocalDate presentationDate) {
        this.presentationDate = presentationDate;
    }
}
