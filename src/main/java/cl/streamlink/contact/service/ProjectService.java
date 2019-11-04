package cl.streamlink.contact.service;


import cl.streamlink.contact.domain.Project;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.NeedRepository;
import cl.streamlink.contact.repository.PositioningRepository;
import cl.streamlink.contact.repository.ProjectRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.ActivityArea;
import cl.streamlink.contact.utils.enums.ProjectStage;
import cl.streamlink.contact.utils.enums.ProjectType;
import cl.streamlink.contact.web.dto.ProjectDTO;
import cl.streamlink.contact.web.dto.ProjectInformationDTO;
import cl.streamlink.contact.web.dto.ProjectResponseDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private NeedRepository needRepository;

    @Inject
    private PositioningRepository positioningRepository;


    @Inject
    private ApiMapper mapper;

    public ProjectDTO createProject(ProjectDTO projectDTO) {

        Project project = mapper.fromDTOToBean(projectDTO);

        project.setReference("prj" + MiscUtils.generateReference());
        return mapper.fromBeanToDTO(projectRepository.save(project));
    }

    public ProjectDTO updateProject(ProjectDTO projectDTO, String projectReference) throws ContactApiException {

        Project project = projectRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference));

        mapper.updateBeanFromDto(projectDTO, project);
        return mapper.fromBeanToDTO(projectRepository.save(project));
    }

    public ProjectDTO getProject(String projectReference) throws ContactApiException {

        return mapper.fromBeanToDTO(projectRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference)));
    }


    public List<ProjectResponseDTO> getProjects(String projectReference) throws ContactApiException {

        if (projectReference != null)
            return Collections.singletonList(mapper.fromBeanToDTOResponse(projectRepository.findOneByReference(projectReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference))));

        else
            return projectRepository.findAll().stream().map(mapper::fromBeanToDTOResponse).collect(Collectors.toList());
    }

    public Page<ProjectResponseDTO> searchProjects(String value, ProjectStage stage, ProjectType type,
                                                   ActivityArea activityArea, Pageable pageable) {


        if (MiscUtils.isEmpty(value))
            value = "";

        List<ProjectStage> stages;
        if (stage != null)
            stages = Collections.singletonList(stage);
        else
            stages = ProjectStage.getAll();

        List<ActivityArea> activityAreas;
        if (activityArea != null)
            activityAreas = Collections.singletonList(activityArea);
        else
            activityAreas = ActivityArea.getAll();

        List<ProjectType> types;
        if (type != null)
            types = Collections.singletonList(type);
        else
            types = ProjectType.getAll();

        return projectRepository.
                findByTitleContainingAndStageInAndTypeInAndProjectInformationActivityAreaIn
                        (value, stages, types, activityAreas, pageable)
                .map(project -> mapper.fromBeanToDTOResponse(project));
    }


    public List<ProjectResponseDTO> searchProjects(String term) {

        if (MiscUtils.isEmpty(term) || term.length() < 3)
            return Collections.EMPTY_LIST;

        else return projectRepository.findByTitleContaining(term).stream()
                .map(project -> mapper.fromBeanToDTOResponse(project))
                .collect(Collectors.toList());

    }

    public JSONObject deleteProject(String projectReference) throws ContactApiException {

        Project project = projectRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference));

        projectRepository.delete(project);

        return MiscUtils.createSuccessfullyResult();
    }

    public void deleteBySociety(String societyReference, String societyContactReference) throws ContactApiException {

        if (MiscUtils.isNotEmpty(societyReference))
            projectRepository.deleteBySocietyContactSocietyReference(societyReference);
        else if (MiscUtils.isNotEmpty(societyContactReference))
            projectRepository.deleteBySocietyContactReference(societyContactReference);

    }


    public ProjectInformationDTO updateProjectInformation(ProjectInformationDTO projectInformation, String projectReference) {

        Project project = projectRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference));

        mapper.updateBeanFromDto(projectInformation, project.getProjectInformation());

        project = projectRepository.save(project);

        return mapper.fromBeanToDTO(project.getProjectInformation(), projectReference);

    }

    public ProjectInformationDTO getProjectInformation(String projectReference) {

        ProjectInformationDTO projectInformation = getProjects(projectReference).get(0).getProjectInformation();
        projectInformation.setProjectReference(projectReference);
        return projectInformation;
    }



//    public ProjectDTO createProjectFromNeed(String needReference) {
//
//        if (!projectRepository.existsByReference(needReference)) {
//            Need need = needRepository.findOneByReference(needReference)
//                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", needReference));
//
//            Project project = mapper.fromNeedToProject(need);
//            return mapper.fromBeanToDTO(projectRepository.save(project));
//        } else
//            throw ContactApiException.unprocessableEntityExceptionBuilder("resource-exist", null);
//    }
//
//    public ProjectDTO createProjectFromPositioning(String positioningReference) {
//
//        if (!projectRepository.existsByReference(positioningReference)) {
//        	Positioning positioning = positioningRepository.findOneByReference(positioningReference)
//                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Positioning", positioningReference));
//
//            Project project = mapper.fromPositioningToProject(positioning);
//            return mapper.fromBeanToDTO(projectRepository.save(project));
//        } else
//            throw ContactApiException.unprocessableEntityExceptionBuilder("resource-exist", null);
//    }
}
