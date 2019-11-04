package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Need;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.NeedRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.ActivityArea;
import cl.streamlink.contact.utils.enums.NeedStage;
import cl.streamlink.contact.utils.enums.NeedType;
import cl.streamlink.contact.web.dto.NeedDTO;
import cl.streamlink.contact.web.dto.NeedInformationDTO;
import cl.streamlink.contact.web.dto.NeedResponseDTO;
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
public class NeedService {

    private final Logger logger = LoggerFactory.getLogger(NeedService.class);

    @Inject
    private NeedRepository needRepository;

    @Inject
    private ApiMapper mapper;

    public NeedDTO createNeed(NeedDTO needDTO) {

        Need need = mapper.fromDTOToBean(needDTO);

        need.setReference("bes" + MiscUtils.generateReference());
        need.setStage(NeedStage.InProgress);
        need.setType(NeedType.Authority);
        return mapper.fromBeanToDTO(needRepository.save(need));
    }

    public NeedDTO updateNeed(NeedDTO needDTO, String needReference) throws ContactApiException {

        Need need = needRepository.findOneByReference(needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference));

        mapper.updateBeanFromDto(needDTO, need);
        return mapper.fromBeanToDTO(needRepository.save(need));
    }

    public NeedDTO getNeed(String needReference) throws ContactApiException {

        return mapper.fromBeanToDTO(needRepository.findOneByReference(needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference)));
    }

    public List<NeedResponseDTO> getNeeds(String needReference) throws ContactApiException {

        if (needReference != null)
            return Collections.singletonList(
                    mapper.fromBeanToDTOResponse(needRepository.findOneByReference(needReference).orElseThrow(
                            () -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference))));

        else
            return needRepository.findAll().stream().map(mapper::fromBeanToDTOResponse).collect(Collectors.toList());
    }

    public Page<NeedResponseDTO> searchNeeds(String value, NeedStage stage, NeedType type, ActivityArea activityArea,
                                             Pageable pageable) {

        if (MiscUtils.isEmpty(value))
            value = "";

        List<NeedStage> stages;
        if (stage != null)
            stages = Collections.singletonList(stage);
        else
            stages = NeedStage.getAll();

        List<ActivityArea> activityAreas;
        if (activityArea != null)
            activityAreas = Collections.singletonList(activityArea);
        else
            activityAreas = ActivityArea.getAll();

        List<NeedType> types;
        if (type != null)
            types = Collections.singletonList(type);
        else
            types = NeedType.getAll();

        return needRepository.findByTitleContainingAndStageInAndTypeInAndNeedInformationActivityAreaIn(value, stages,
                types, activityAreas, pageable).map(need -> mapper.fromBeanToDTOResponse(need));
    }

    public List<NeedResponseDTO> searchNeeds(String term) {

        if (MiscUtils.isEmpty(term) || term.length() < 3)
            return Collections.EMPTY_LIST;

        else
            return needRepository.findByTitleContaining(term).stream().map(need -> mapper.fromBeanToDTOResponse(need))
                    .collect(Collectors.toList());

    }

    public JSONObject deleteNeed(String needReference) throws ContactApiException {

        Need need = needRepository.findOneByReference(needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference));

        needRepository.delete(need);

        return MiscUtils.createSuccessfullyResult();
    }

    public void deleteBySociety(String societyReference, String societyContactReference) throws ContactApiException {

        if (MiscUtils.isNotEmpty(societyReference))
            needRepository.deleteBySocietyContactSocietyReference(societyReference);
        else if (MiscUtils.isNotEmpty(societyContactReference))
            needRepository.deleteBySocietyContactReference(societyContactReference);

    }

    public NeedInformationDTO updateNeedInformation(NeedInformationDTO needInformation, String needReference) {

        Need need = needRepository.findOneByReference(needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference));

        mapper.updateBeanFromDto(needInformation, need.getNeedInformation());

        need = needRepository.save(need);

        return mapper.fromBeanToDTO(need.getNeedInformation(), needReference);

    }

    public NeedInformationDTO getNeedInformation(String needReference) {

        NeedInformationDTO needInformation = getNeeds(needReference).get(0).getNeedInformation();
        needInformation.setNeedReference(needReference);
        return needInformation;
    }

    public long needCount() {
        return needRepository.count();
    }
}
