package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.Currency;
import cl.streamlink.contact.utils.enums.ActivityArea;

import java.math.BigDecimal;
import java.time.LocalDate;


public class ProjectInformationDTO {

    private String projectReference;

    private ActivityArea activityArea;

    private String place;

    private Integer durationByMonth;

    private LocalDate startingDate;

    private LocalDate responseDate;

    private LocalDate closingDate;

    private Currency currency;

    private BigDecimal budget;

    public String getProjectReference() {
        return projectReference;
    }

    public void setProjectReference(String projectReference) {
        this.projectReference = projectReference;
    }

    public ActivityArea getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(ActivityArea activityArea) {
        this.activityArea = activityArea;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getDurationByMonth() {
        return durationByMonth;
    }

    public void setDurationByMonth(Integer durationByMonth) {
        this.durationByMonth = durationByMonth;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(LocalDate responseDate) {
        this.responseDate = responseDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
