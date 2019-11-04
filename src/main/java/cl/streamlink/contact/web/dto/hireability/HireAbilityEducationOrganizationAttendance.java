package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HireAbilityEducationOrganizationAttendance {

    @JsonAlias(value = "School")
    private String school;

    @JsonAlias(value = "DegreeType")
    private List<HireAbilityDegree> degrees = new ArrayList<>();

    @JsonAlias(value = "DegreeDate")
    private Date degreeDate;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<HireAbilityDegree> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<HireAbilityDegree> degrees) {
        this.degrees = degrees;
    }

    public Date getDegreeDate() {
        return degreeDate;
    }

    public void setDegreeDate(Date degreeDate) {
        this.degreeDate = degreeDate;
    }
}
