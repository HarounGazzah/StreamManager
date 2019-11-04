package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Action;
import cl.streamlink.contact.domain.Need;
import cl.streamlink.contact.domain.Project;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.*;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.ActionDTO;
import cl.streamlink.contact.web.dto.DeveloperActionDTO;
import cl.streamlink.contact.web.dto.SocietyActionDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class ActionService {

    private final Logger logger = LoggerFactory.getLogger(DeveloperService.class);

    @Inject
    private ActionRepository actionRepository;

    @Inject
    private DeveloperRepository developerRepository;

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private NeedRepository needRepository;

    @Inject
    private SocietyContactRepository societyContactRepository;

    @Inject
    private SocietyRepository societyRepository;

    @Inject
    private UserService userService;

    @Inject
    private ApiMapper mapper;


    public List<ActionDTO> getActions() {
        return actionRepository.findAll().stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());

    }


    public DeveloperActionDTO createAction(DeveloperActionDTO developerActionDTO, String developerReference) {

        developerRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        developerActionDTO.setReference("act" + MiscUtils.generateReference());
        developerActionDTO.setDeveloperReference(developerReference);
        developerActionDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Action action = mapper.fromDTOToBean(developerActionDTO);

        return mapper.fromBeanToDeveloperActionDTO(actionRepository.save(action));
    }

    public DeveloperActionDTO updateAction(DeveloperActionDTO developerActionDTO, String reference,
                                           String developerReference) {

        developerRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Action action = actionRepository.findOneByReferenceAndDeveloperReference(reference, developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        mapper.updateBeanFromDto(developerActionDTO, action);

        action = actionRepository.save(action);

        return mapper.fromBeanToDeveloperActionDTO(action);

    }

    public List<DeveloperActionDTO> getAction(String reference, String developerReference) {

        developerRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        if (MiscUtils.isEmpty(reference))
            return actionRepository.findByDeveloperReference(developerReference).stream()
                    .map(mapper::fromBeanToDeveloperActionDTO).collect(Collectors.toList());
        else {
            Action action = actionRepository.findOneByReferenceAndDeveloperReference(reference, developerReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

            return Collections.singletonList(mapper.fromBeanToDeveloperActionDTO(action));
        }
    }

    public JSONObject deleteAction(String reference, String developerReference) {

        developerRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Action action = actionRepository.findOneByReferenceAndDeveloperReference(reference, developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        actionRepository.delete(action);
        return MiscUtils.createSuccessfullyResult();

    }

    public SocietyActionDTO createAction(SocietyActionDTO societyActionDTO, String societyContactReference,
                                         String societyReference) {

        societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));

        societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference, societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact",
                        societyContactReference));

        societyActionDTO.setReference(MiscUtils.generateReference());
        societyActionDTO.setSocietyContactReference(societyContactReference);
        societyActionDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Action action = mapper.fromDTOToBean(societyActionDTO);

        return mapper.fromBeanToSocietyActionDTO(actionRepository.save(action));
    }

    public SocietyActionDTO updateAction(SocietyActionDTO societyActionDTO, String reference,
                                         String societyContactReference, String societyReference) {

        societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));
        Action action;

        if (MiscUtils.isNotEmpty(societyContactReference)) {

            societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference, societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact",
                            societyContactReference));

            action = actionRepository
                    .findOneByReferenceAndSocietyContactReferenceAndProjectIsNull(reference, societyContactReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        } else {
            societyContactRepository
                    .findOneByReferenceAndSocietyReference(societyActionDTO.getSocietyContactReference(),
                            societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact",
                            societyActionDTO.getSocietyContactReference()));

            action = actionRepository
                    .findOneByReferenceAndSocietyContactSocietyReferenceAndProjectIsNull(reference, societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));
        }

        mapper.updateBeanFromDto(societyActionDTO, action);

        action = actionRepository.save(action);

        return mapper.fromBeanToSocietyActionDTO(action);

    }

    public List<SocietyActionDTO> getAction(String reference, String societyContactReference, String societyReference) {

        societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));

        if (MiscUtils.isEmpty(societyContactReference)) {
            return actionRepository.findBySocietyContactSocietyReferenceAndProjectIsNull(societyReference).stream()
                    .map(mapper::fromBeanToSocietyActionDTO).collect(Collectors.toList());
        } else {
            societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference, societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact",
                            societyContactReference));
            if (MiscUtils.isEmpty(reference)) {
                return actionRepository.findBySocietyContactReferenceAndProjectIsNull(societyContactReference).stream()
                        .map(mapper::fromBeanToSocietyActionDTO).collect(Collectors.toList());
            } else {
                Action action = actionRepository
                        .findOneByReferenceAndSocietyContactReferenceAndProjectIsNull(reference,
                                societyContactReference)
                        .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

                return Collections.singletonList(mapper.fromBeanToSocietyActionDTO(action));
            }
        }
    }

    public JSONObject deleteAction(String reference, String societyContactReference, String societyReference) {

        societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));

        societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference, societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact",
                        societyContactReference));

        Action action = actionRepository
                .findOneByReferenceAndSocietyContactReferenceAndProjectIsNull(reference, societyContactReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        actionRepository.delete(action);
        return MiscUtils.createSuccessfullyResult();

    }

    public SocietyActionDTO createProjectAction(SocietyActionDTO societyActionDTO, String projectReference) {

        Project project = projectRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference));

        societyActionDTO.setReference(MiscUtils.generateReference());
        societyActionDTO.setProjectReference(projectReference);
        societyActionDTO.setSocietyContactReference(project.getSocietyContact().getReference());
        societyActionDTO.setProjectReference(projectReference);
        societyActionDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Action action = mapper.fromDTOToBean(societyActionDTO);

        return mapper.fromBeanToSocietyActionDTO(actionRepository.save(action));
    }

    public SocietyActionDTO updateProjectAction(SocietyActionDTO societyActionDTO, String reference,
                                                String projectReference) {

        Project project = projectRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference));

        Action action = actionRepository.findOneByReferenceAndProjectReference(reference, projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        mapper.updateBeanFromDto(societyActionDTO, action);

        action = actionRepository.save(action);

        return mapper.fromBeanToSocietyActionDTO(action);
    }

    public List<SocietyActionDTO> getProjectAction(String reference, String projectReference) {

        projectRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference));

        if (MiscUtils.isEmpty(reference))
            return actionRepository.findByProjectReference(projectReference).stream()
                    .map(mapper::fromBeanToSocietyActionDTO).collect(Collectors.toList());
        else {
            Action action = actionRepository.findOneByReferenceAndProjectReference(reference, projectReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

            return Collections.singletonList(mapper.fromBeanToSocietyActionDTO(action));
        }
    }

    public JSONObject deleteProjectAction(String reference, String projectReference) {

        projectRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference));

        Action action = actionRepository.findOneByReferenceAndProjectReference(reference, projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        actionRepository.delete(action);
        return MiscUtils.createSuccessfullyResult();
    }


    public SocietyActionDTO createNeedAction(SocietyActionDTO societyActionDTO, String needReference) {

        Need need = needRepository.findOneByReference(needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference));

        societyActionDTO.setReference(MiscUtils.generateReference());
        societyActionDTO.setNeedReference(needReference);
        societyActionDTO.setSocietyContactReference(need.getSocietyContact().getReference());
        societyActionDTO.setNeedReference(needReference);
        societyActionDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Action action = mapper.fromDTOToBean(societyActionDTO);

        return mapper.fromBeanToSocietyActionDTO(actionRepository.save(action));
    }

    public SocietyActionDTO updateNeedAction(SocietyActionDTO societyActionDTO, String reference,
                                             String needReference) {

        Need need = needRepository.findOneByReference(needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference));

        Action action = actionRepository.findOneByReferenceAndNeedReference(reference, needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        mapper.updateBeanFromDto(societyActionDTO, action);

        action = actionRepository.save(action);

        return mapper.fromBeanToSocietyActionDTO(action);
    }

    public List<SocietyActionDTO> getNeedAction(String reference, String needReference) {

        needRepository.findOneByReference(needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference));

        if (MiscUtils.isEmpty(reference))
            return actionRepository.findByNeedReference(needReference).stream()
                    .map(mapper::fromBeanToSocietyActionDTO).collect(Collectors.toList());
        else {
            Action action = actionRepository.findOneByReferenceAndNeedReference(reference, needReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

            return Collections.singletonList(mapper.fromBeanToSocietyActionDTO(action));
        }
    }

    public JSONObject deleteNeedAction(String reference, String needReference) {

        needRepository.findOneByReference(needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference));

        Action action = actionRepository.findOneByReferenceAndNeedReference(reference, needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        actionRepository.delete(action);
        return MiscUtils.createSuccessfullyResult();
    }
}
