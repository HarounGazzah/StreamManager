package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Positioning;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.NeedRepository;
import cl.streamlink.contact.repository.PositioningRepository;
import cl.streamlink.contact.repository.ProjectRepository;
import cl.streamlink.contact.repository.ResourceRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.NeedStage;
import cl.streamlink.contact.utils.enums.NeedType;
import cl.streamlink.contact.utils.enums.PositioningStage;
import cl.streamlink.contact.utils.enums.ResourceType;
import cl.streamlink.contact.web.dto.PositioningDTO;
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
public class PositioningService {

    private final Logger logger = LoggerFactory.getLogger(PositioningService.class);

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private NeedRepository needRepository;

    @Inject
    private ResourceRepository resourceRepository;

    @Inject
    private PositioningRepository positioningRepository;

    @Inject
    private UserService userService;

    @Inject
    private ApiMapper mapper;


    public PositioningDTO createPositioning(PositioningDTO positioningDTO) {

        needRepository.findOneByReference(positioningDTO.getNeedReference()).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Need", positioningDTO.getNeedReference()));

        resourceRepository.findOneByReference(positioningDTO.getResourceReference())
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Resource",
                        positioningDTO.getResourceReference()));

        positioningDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Positioning positioning = mapper.fromDTOToBean(positioningDTO);

        positioning.setReference("pos" + MiscUtils.generateReference());

        return mapper.fromBeanToDTO(positioningRepository.save(positioning));
    }

    public PositioningDTO updatePositioning(PositioningDTO positioningDTO, String positioningReference)
            throws ContactApiException {
        needRepository.findOneByReference(positioningDTO.getNeedReference()).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Need", positioningDTO.getNeedReference()));
//        projectRepository.findOneByReference(positioningDTO.getNeedReference()).orElseThrow(
//                () -> ContactApiException.resourceNotFoundExceptionBuilder("Need", positioningDTO.getNeedReference()));

        resourceRepository.findOneByReference(positioningDTO.getResourceReference())
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Resource",
                        positioningDTO.getResourceReference()));

        Positioning positioning = positioningRepository.findOneByReference(positioningReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Positioning", positioningReference));

        mapper.updateBeanFromDto(positioningDTO, positioning);
        return mapper.fromBeanToDTO(positioningRepository.save(positioning));
    }

    public PositioningDTO getPositioning(String positioningReference) throws ContactApiException {

        return mapper.fromBeanToDTO(positioningRepository.findOneByReference(positioningReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Positioning", positioningReference)));
    }

    public List<PositioningDTO> getPositionings() throws ContactApiException {


        return positioningRepository.findAll().stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }

    public Page<PositioningDTO> searchPositionings(String value, NeedStage needStage, NeedType needType,
                                                   ResourceType type, PositioningStage stage, Pageable pageable) {

        if (MiscUtils.isEmpty(value))
            value = "";

        List<PositioningStage> stages;
        if (stage != null)
            stages = Collections.singletonList(stage);
        else
            stages = PositioningStage.getAll();

        List<NeedStage> needStages;
        if (needStage != null)
            needStages = Collections.singletonList(needStage);
        else
            needStages = NeedStage.getAll();

        List<NeedType> needTypes;
        if (needType != null)
            needTypes = Collections.singletonList(needType);
        else
            needTypes = NeedType.getAll();

        List<ResourceType> types;
        if (type != null)
            types = Collections.singletonList(type);
        else
            types = ResourceType.getAll();

        return positioningRepository
                .findByNeedTitleContainingAndNeedStageInAndNeedTypeInAndResourceResourceTypeInAndStageIn(value,

                        needStages, needTypes, types, stages, pageable)
                .map(positioning -> mapper.fromBeanToDTO(positioning));
    }

    public JSONObject deletePositioning(String positioningReference) throws ContactApiException {

        Positioning positioning = positioningRepository.findOneByReference(positioningReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Positioning", positioningReference));

        positioningRepository.delete(positioning);

        return MiscUtils.createSuccessfullyResult();
    }

    public List<PositioningDTO> getPositioningResource( String resourceReference){

        return positioningRepository.findByResourceReference(resourceReference)
                .stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }

}
