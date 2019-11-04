package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Contact;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.domain.PersonalInformation;
import cl.streamlink.contact.domain.SkillsInformation;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.utils.enums.*;
import cl.streamlink.contact.web.dto.hireability.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HireabilityService {

    private final Logger logger = LoggerFactory.getLogger(HireabilityService.class);


    @Value("${contact.hireability.product_code}")
    private String hireAbilityProductCode;

    @Value("${contact.hireability.url}")
    private String hireAbilityUrl;

    @Value("${contact.hireability.path}")
    private String hireAbilityPath;

    @Inject
    private RestTemplate restTemplate;


    @Inject
    private ObjectMapper objectMapper;


    public Developer parseResume(File resume) throws IOException {

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("document", new FileSystemResource(resume));
        map.add("product_code", hireAbilityProductCode);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(
                map, headers);
        ResponseEntity<String> result = restTemplate.exchange(
                hireAbilityUrl, HttpMethod.POST, requestEntity, String.class);

        File jsonFile = writeToFile(result, FilenameUtils.removeExtension(resume.getName()).concat(".json"));

        return convertToHireAbilityResult(jsonFile);
    }

    private File writeToFile(ResponseEntity<String> result, String fileName) throws IOException {

        FileOutputStream outputStream = new FileOutputStream(new File(hireAbilityPath, fileName));
        byte[] strToBytes = Objects.requireNonNull(result.getBody()).getBytes();
        outputStream.write(strToBytes);
        outputStream.close();

        return new File(hireAbilityPath, fileName);
    }

    private Developer convertToHireAbilityResult(File jsonResult) throws IOException {

        Results results = objectMapper.readValue(jsonResult, Results.class);

        return loadDeveloperFromHireAbility(results.getResults().stream()
                .flatMap(result -> result.getHireAbilityJSONResults().stream())
                .filter(HireAbilityJSONResults::isNotEmpty)
                .findFirst()
                .orElseThrow(() -> ContactApiException.unprocessableEntityExceptionBuilder("parse_error", null)));
    }


    private Developer loadDeveloperFromHireAbility(HireAbilityJSONResults resume) {

        Developer developer = new Developer();

        developer.setGender(Gender.fromString(resume.getGender()));
        developer.setFirstname(resume.getGivenName());
        developer.setLastname(resume.getFamilyName());
        developer.setStage(Stage.ToTreat);

        Contact contact = new Contact();

        contact.setEmail1(resume.getEmails().stream().findFirst().orElse(new HireAbilityContact()).getValue());

        if (resume.getEmails().size() >= 2)
            contact.setEmail2(resume.getEmails().get(1).getValue());

        if (resume.getEmails().size() >= 3)
            contact.setEmail3(resume.getEmails().get(2).getValue());

        contact.setTel1(resume.getPhones().stream().findFirst().orElse(new HireAbilityContact()).getValue());

        if (resume.getPhones().size() >= 2)
            contact.setTel2(resume.getPhones().get(1).getValue());

        if (resume.getPhones().size() >= 3)
            contact.setTel3(resume.getPhones().get(2).getValue());

        contact.setWebsite(resume.getWebsites().stream().findFirst().orElse(new HireAbilityContact()).getValue());

        HireAbilityAddress address = resume.getAddresses().stream().findFirst().orElse(null);

        if (address != null) {
            contact.setAddress(address.getAddressLine());
            contact.setCity(address.getCityName());
            contact.setNpa(address.getPostalCode());

            Locale obj = new Locale("", address.getCountryCode());

            contact.setCountry(address.getCountryCode());
        }

        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setBirthDate(resume.getDateOfBirth());
        personalInformation.setFamilySituation(FamilySituation.fromString(resume.getFamilySituation()));


        SkillsInformation skillsInformation = new SkillsInformation();
        skillsInformation.setTitle(resume.getPositionHistories().stream().findFirst()
                .orElse(new HireAbilityPositionHistory()).getPositionTitle());
        skillsInformation.setFormation(Formation.NOT_DEFINED);
        skillsInformation.setExperience(Experience.NOT_DEFINED);
//
        skillsInformation.setQualifications(!resume.getEducationOrganizations().isEmpty() ?
                resume.getEducationOrganizations().stream()
                        .flatMap(education -> education.getDegrees().stream())
                        .filter(Objects::nonNull)
                        .map(HireAbilityDegree::getName)
                        .collect(Collectors.toList()) : new ArrayList<>());

        skillsInformation.getQualifications().addAll(!resume.getCertifications().isEmpty() ?
                resume.getCertifications().stream()
                        .filter(Objects::nonNull)
                        .map(HireAbilityDegree::getName)
                        .collect(Collectors.toList()) : new ArrayList<>());

        skillsInformation.getQualifications().addAll(!resume.getLicences().isEmpty() ?
                resume.getLicences().stream()
                        .filter(Objects::nonNull)
                        .map(HireAbilityDegree::getName)
                        .collect(Collectors.toList()) : new ArrayList<>());

        skillsInformation.setLanguages(!resume.getCompetencies().isEmpty() ?
                resume.getCompetencies().stream()
                        .map(HireabilityCompetency::getCompetencyName)
                        .collect(Collectors.joining(",")) : "UNDEFINED");

        developer.setContact(contact);
        developer.setPersonalInformation(personalInformation);
        developer.setSkillsInformation(skillsInformation);


        return developer;
    }


}
