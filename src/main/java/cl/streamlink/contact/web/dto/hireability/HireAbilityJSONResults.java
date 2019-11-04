package cl.streamlink.contact.web.dto.hireability;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HireAbilityJSONResults {

    private String id;

    @JsonAlias(value = "FormattedName")
    private String formattedName;

    @JsonAlias(value = "GivenName")
    private String givenName;

    @JsonAlias(value = "PreferredGivenName")
    private String preferredGivenName;

    @JsonAlias(value = "MiddleName")
    private String middleName;

    @JsonAlias(value = "FamilyName")
    private String familyName;

    @JsonAlias(value = "DateOfBirth")
    private Date dateOfBirth;

    @JsonAlias(value = "Gender")
    private String gender = "UNDEFINED";

    @JsonAlias(value = "MaritalStatus")
    private String familySituation = "UNDEFINED";

    @JsonAlias(value = "Phone")
    private List<HireAbilityContact> phones = new ArrayList<>();

    @JsonAlias(value = "Email")
    private List<HireAbilityContact> emails = new ArrayList<>();

    @JsonAlias(value = "Website")
    private List<HireAbilityContact> websites = new ArrayList<>();

    @JsonAlias(value = "Address")
    private List<HireAbilityAddress> addresses = new ArrayList<>();

    @JsonAlias(value = "PersonCompetency")
    private List<HireabilityCompetency> competencies = new ArrayList<>();

    @JsonAlias(value = "Languages")
    private List<HireAbilityLanguages> languages = new ArrayList<>();

    @JsonAlias(value = "PositionHistory")
    private List<HireAbilityPositionHistory> positionHistories = new ArrayList<>();

    @JsonAlias(value = "License")
    private List<HireAbilityDegree> licences = new ArrayList<>();

    @JsonAlias(value = "Certification")
    private List<HireAbilityDegree> certifications = new ArrayList<>();

    @JsonAlias(value = "EducationOrganizationAttendance")
    private List<HireAbilityEducationOrganizationAttendance> educationOrganizations = new ArrayList<>();

    @JsonAlias(value = "ProcessingErrors")
    private List<HireAbilityProcessingError> processingError = new ArrayList<>();

    public boolean isNotEmpty() {
        return MiscUtils.isNotEmpty(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPreferredGivenName() {
        return preferredGivenName;
    }

    public void setPreferredGivenName(String preferredGivenName) {
        this.preferredGivenName = preferredGivenName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public LocalDate getDateOfBirth() {
        if (dateOfBirth != null)
            return dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        else
            return null;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFamilySituation() {
        return familySituation;
    }

    public void setFamilySituation(String familySituation) {
        this.familySituation = familySituation;
    }

    public List<HireAbilityContact> getPhones() {
        return phones;
    }

    public void setPhones(List<HireAbilityContact> phones) {
        this.phones = phones;
    }

    public List<HireAbilityContact> getEmails() {
        return emails;
    }

    public void setEmails(List<HireAbilityContact> emails) {
        this.emails = emails;
    }

    public List<HireAbilityContact> getWebsites() {
        return websites;
    }

    public void setWebsites(List<HireAbilityContact> websites) {
        this.websites = websites;
    }

    public List<HireAbilityAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<HireAbilityAddress> addresses) {
        this.addresses = addresses;
    }

    public List<HireabilityCompetency> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(List<HireabilityCompetency> competencies) {
        this.competencies = competencies;
    }

    public List<HireAbilityLanguages> getLanguages() {
        return languages;
    }

    public void setLanguages(List<HireAbilityLanguages> languages) {
        this.languages = languages;
    }

    public List<HireAbilityPositionHistory> getPositionHistories() {
        return positionHistories;
    }

    public void setPositionHistories(List<HireAbilityPositionHistory> positionHistories) {
        this.positionHistories = positionHistories;
    }

    public List<HireAbilityDegree> getLicences() {
        return licences;
    }

    public void setLicences(List<HireAbilityDegree> licences) {
        this.licences = licences;
    }

    public List<HireAbilityDegree> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<HireAbilityDegree> certifications) {
        this.certifications = certifications;
    }

    public List<HireAbilityEducationOrganizationAttendance> getEducationOrganizations() {
        return educationOrganizations;
    }

    public void setEducationOrganizations(List<HireAbilityEducationOrganizationAttendance> educationOrganizations) {
        this.educationOrganizations = educationOrganizations;
    }

    public List<HireAbilityProcessingError> getProcessingError() {
        return processingError;
    }

    public void setProcessingError(List<HireAbilityProcessingError> processingError) {
        this.processingError = processingError;
    }
}
