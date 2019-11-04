package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Positioning;
import cl.streamlink.contact.domain.Project;
import cl.streamlink.contact.domain.ProjectPos;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.PositioningRepository;
import cl.streamlink.contact.repository.ProjectPosRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.PositioningStage;
import cl.streamlink.contact.web.dto.ProjectPosDTO;
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectPosService {

    @Inject
    ProjectPosRepository projectPosRepository;

    @Inject
    PositioningRepository positioningRepository;

    @Inject
    private ApiMapper mapper;

    public ProjectPosDTO createProjectFromPositioning(String positioningReference) {

        if (!projectPosRepository.existsByReference(positioningReference)) {
            Positioning positioning = positioningRepository.findOneByReference(positioningReference)

                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Positioning", positioningReference));

            ProjectPos projectPos = mapper.fromPositioningToProject(positioning);
            projectPos.setStage(PositioningStage.Won);

            positioningRepository.delete(positioning);
            return mapper.fromBeanToDTO(projectPosRepository.save(projectPos));
        } else
            throw ContactApiException.unprocessableEntityExceptionBuilder("project-exist", null);
    }


    public List<ProjectPosDTO> getProjects(String projectReference) throws ContactApiException {

        if (MiscUtils.isNotEmpty(projectReference))
            return Collections.singletonList(mapper.fromBeanToDTO(projectPosRepository.findOneByReference(projectReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("ProjectPos", projectReference))));

        else
            return projectPosRepository.findAll().stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }

    public ProjectPosDTO getProject(String projectReference) throws ContactApiException {

        return mapper.fromBeanToDTO(projectPosRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("ProjectPos", projectReference)));
    }

    public ProjectPosDTO updateProject(ProjectPosDTO projectPosDTO, String projectReference) throws ContactApiException {

        ProjectPos projectPos = projectPosRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("ProjectPos", projectReference));

        mapper.updateBeanFromDto(projectPosDTO, projectPos);
        return mapper.fromBeanToDTO(projectPosRepository.save(projectPos));
    }

    public Page<ProjectPosDTO> searchProjects(String value, Pageable pageable) {

        if (MiscUtils.isEmpty(value))
            value = "";


        return projectPosRepository.findByNeedTitleContaining
                (value, pageable).map(project -> mapper.fromBeanToDTO(project));
    }

    public JSONObject deleteProject(String projectReference) throws ContactApiException {

        ProjectPos projectPos = projectPosRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference));

        projectPosRepository.delete(projectPos);

        return MiscUtils.createSuccessfullyResult();
    }

    public long projectCount() {
        return projectPosRepository.count();
    }
}
