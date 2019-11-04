package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.repository.LanguageRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.Experience;
import cl.streamlink.contact.utils.enums.Formation;
import cl.streamlink.contact.utils.enums.Stage;
import cl.streamlink.contact.web.dto.*;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CrossOrigin("*")
public class DeveloperService {

    private final Logger logger = LoggerFactory.getLogger(DeveloperService.class);


    @Inject
    private DeveloperRepository developerRepository;


    @Inject
    private ResourceService resourceService;

    @Inject
    private LanguageService languageService;

    @Inject
    private LanguageRepository languageRepository;

    @Inject
    private ApiMapper mapper;

    public DeveloperDTO createDeveloper(DeveloperDTO developerDTO) {

        if (developerDTO.getStage() == Stage.ConvertedToResource)
            return resourceService.createResource(mapper.fromDeveloperToResource(developerDTO));

        Developer developer = mapper.fromDTOToBean(developerDTO);

        developer.setReference(MiscUtils.generateReference());
        return mapper.fromBeanToDTO(developerRepository.save(developer));
    }

    public DeveloperDTO updateDeveloper(DeveloperDTO developerDTO, String developerReference)
            throws ContactApiException {

        Developer developer = developerRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        if (developerDTO.getStage() == Stage.ConvertedToResource && developer.getStage() != Stage.ConvertedToResource)
            return resourceService.createResourceFromDeveloper(developerReference, developerDTO);

        mapper.updateBeanFromDto(developerDTO, developer);
        return mapper.fromBeanToDTO(developerRepository.save(developer));
    }

    public DeveloperDTO getDeveloper(String developerReference) throws ContactApiException {

        return mapper.fromBeanToDTO(developerRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference)));
    }


    public DeveloperDTO getDeveloperEmail(String developerEmail) throws ContactApiException {

        return mapper.fromBeanToDTO(developerRepository.findByContact_Email1(developerEmail).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerEmail)));
    }



    public List<DeveloperResponseDTO> getDevelopers(String developerReference) throws ContactApiException {

        if (developerReference != null)
            return Collections.singletonList(mapper.fromBeanToDTOResponse(
                    developerRepository.findOneByReference(developerReference).orElseThrow(() -> ContactApiException
                            .resourceNotFoundExceptionBuilder("Developer", developerReference))));

        else
            return developerRepository.findAll().stream().map(mapper::fromBeanToDTOResponse)
                    .collect(Collectors.toList());
    }

    public Page<DeveloperResponseDTO> searchDevelopers(String value, Stage stage, Experience experience,
                                                       Formation formation, Pageable pageable) {

        if (MiscUtils.isEmpty(value))
            value = "";

        List<Stage> stages;
        if (stage != null)
            stages = Collections.singletonList(stage);
        else
            stages = Stage.getAll();

        List<Experience> experiences;
        if (experience != null)
            experiences = Collections.singletonList(experience);
        else
            experiences = Experience.getAll();

        List<Formation> formations;
        if (formation != null)
            formations = Collections.singletonList(formation);
        else
            formations = Formation.getAll();

        return developerRepository
                .findByFirstnameContainingAndStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInOrLastnameContainingAndStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInOrSkillsInformationTitleContainingAndStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInOrSkillsInformationLanguagesContainingAndStageInAndSkillsInformationFormationInAndSkillsInformationExperienceIn(
                        value, stages, formations, experiences,
                        value, stages, formations, experiences,
                        value, stages, formations, experiences,
                        value, stages, formations, experiences,
                        pageable)
                .map(developer -> mapper.fromBeanToDTOResponse(developer));
    }

    public JSONObject deleteDeveloper(String developerReference) throws ContactApiException {

        Developer developer = developerRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        developerRepository.delete(developer);

        return MiscUtils.createSuccessfullyResult();
    }

    /*
     * public DeveloperResponseDTO addLanguage(String developerReference, String
     * languageReference) throws ContactApiException { Developer developer =
     * developerRepository.findOneByReference(developerReference) .orElseThrow(() ->
     * ContactApiException.resourceNotFoundExceptionBuilder("Developer",
     * developerReference));
     *
     * Language language = languageRepository.findOneByReference(languageReference)
     * .orElseThrow(() ->
     * ContactApiException.resourceNotFoundExceptionBuilder("Language",
     * languageReference));
     *
     *
     * if (developer.getSkillsInformation().getLanguages().add(language)) { return
     * mapper.fromBeanToDTOResponse(developerRepository.save(developer));
     *
     * } else { throw
     * ContactApiException.unprocessableEntityExceptionBuilder("relation.exist", new
     * String[]{developerReference, languageReference}); } }
     */

    /*
     * public DeveloperResponseDTO removeLanguage(String developerReference, String
     * languageReference) throws ContactApiException { Developer developer =
     * developerRepository.findOneByReference(developerReference) .orElseThrow(() ->
     * ContactApiException.resourceNotFoundExceptionBuilder("Developer",
     * developerReference));
     *
     * Language language = languageRepository.findOneByReference(languageReference)
     * .orElseThrow(() ->
     * ContactApiException.resourceNotFoundExceptionBuilder("Language",
     * languageReference));
     *
     *
     * if (developer.getSkillsInformation().getLanguages().remove(language)) {
     * return mapper.fromBeanToDTOResponse(developerRepository.save(developer));
     *
     * } else { throw
     * ContactApiException.unprocessableEntityExceptionBuilder("relation.not.exist",
     * new String[]{developerReference, languageReference}); } }
     */

    public ContactDTO updateDeveloperContact(ContactDTO contact, String developerReference) {
        Developer developer = developerRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));
        mapper.updateBeanFromDto(contact, developer.getContact());

        developer = developerRepository.save(developer);

        return mapper.fromBeanToDTO(developer.getContact(), developerReference);

    }

    public ContactDTO getDeveloperContact(String developerReference) {

        ContactDTO contact = getDevelopers(developerReference).get(0).getContact();
        contact.setOwnerReference(developerReference);
        return contact;
    }

    public SkillsInformationDTO updateDeveloperSkills(SkillsInformationDTO skillsInformation,
                                                      String developerReference) {

//        skillsInformation.getLanguages().stream().filter(lan -> MiscUtils.isEmpty(lan.getReference())).
//                forEach(languageService::createLanguage);

        Developer developer = developerRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));
        mapper.updateBeanFromDto(skillsInformation, developer.getSkillsInformation());

        developer = developerRepository.save(developer);

        return mapper.fromBeanToDTO(developer.getSkillsInformation(), developerReference);

    }

    public SkillsInformationDTO getDeveloperSkills(String developerReference) {

        SkillsInformationDTO skillsInformation = getDevelopers(developerReference).get(0).getSkillsInformation();
        skillsInformation.setDeveloperReference(developerReference);
        return skillsInformation;
    }

    public PersonalInformationDTO updateDeveloperPersonalInformation(PersonalInformationDTO personalInformation,
                                                                     String developerReference) {

        Developer developer = developerRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));
        mapper.updateBeanFromDto(personalInformation, developer.getPersonalInformation());

        developer = developerRepository.save(developer);

        return mapper.fromBeanToDTO(developer.getPersonalInformation(), developerReference);
    }

    public PersonalInformationDTO getDeveloperPersonalInformation(String developerReference) {
        PersonalInformationDTO personalInformation = getDevelopers(developerReference).get(0).getPersonalInformation();
        personalInformation.setDeveloperReference(developerReference);
        return personalInformation;
    }


    public List<DeveloperDTO> getDeveloperByManger(String managerReference) throws ContactApiException {

        return developerRepository.findByManagerReference(managerReference).stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());


    }


    public long developerCount() {
        return developerRepository.count();
    }

    /*public DeveloperDTO getDeveloperEmail(String developerEmail) throws ContactApiException {

        return mapper.fromBeanToDTO(developerRepository.findByContact_Email1(developerEmail).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerEmail)));
    }*/


}
