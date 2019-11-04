package cl.streamlink.contact.service;


import cl.streamlink.contact.domain.Society;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.SocietyRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.ActivityArea;
import cl.streamlink.contact.utils.enums.SocietyStage;
import cl.streamlink.contact.web.dto.ContactDTO;
import cl.streamlink.contact.web.dto.SocietyDTO;
import cl.streamlink.contact.web.dto.SocietyLegalInformationDTO;
import cl.streamlink.contact.web.dto.SocietyResponseDTO;
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
public class SocietyService {

    private final Logger logger = LoggerFactory.getLogger(SocietyService.class);

    @Inject
    private SocietyRepository societyRepository;

    @Inject
    private SocietyContactService societyContactService;

    @Inject
    private ApiMapper mapper;

    public SocietyDTO createSociety(SocietyDTO societyDTO) {

        Society society = mapper.fromDTOToBean(societyDTO);

        society.setReference(MiscUtils.generateReference());
        return mapper.fromBeanToDTO(societyRepository.save(society));
    }

    public SocietyDTO updateSociety(SocietyDTO societyDTO, String societyReference) throws ContactApiException {

        Society society = societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));

        mapper.updateBeanFromDto(societyDTO, society);
        return mapper.fromBeanToDTO(societyRepository.save(society));
    }

    public SocietyDTO getSociety(String societyReference) throws ContactApiException {

        return mapper.fromBeanToDTO(societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference)));
    }


    public List<SocietyResponseDTO> getSocieties(String societyReference) throws ContactApiException {

        if (societyReference != null)
            return Collections.singletonList(mapper.fromBeanToDTOResponse(societyRepository.findOneByReference(societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference))));

        else
            return societyRepository.findAll().stream().map(mapper::fromBeanToDTOResponse).collect(Collectors.toList());
    }

    public List<SocietyResponseDTO> searchSocieties(String term) {

        if (MiscUtils.isEmpty(term) || term.length() < 3)
            return Collections.EMPTY_LIST;

        else return societyRepository.
                findByLabelContaining(term).stream()
                .map(society -> mapper.fromBeanToDTOResponse(society))
                .collect(Collectors.toList());

    }

    public Page<SocietyResponseDTO> searchSocieties(String value, SocietyStage stage,
                                                    ActivityArea activityArea, Pageable pageable) {


        if (MiscUtils.isEmpty(value))
            value = "";

        List<SocietyStage> stages;
        if (stage != null)
            stages = Collections.singletonList(stage);
        else
            stages = SocietyStage.getAll();

        List<ActivityArea> activityAreas;
        if (activityArea != null)
            activityAreas = Collections.singletonList(activityArea);
        else
            activityAreas = ActivityArea.getAll();

        return societyRepository.
                findByLabelContainingAndStageInAndActivityAreaIn
                        (value, stages, activityAreas, pageable)
                .map(society -> mapper.fromBeanToDTOResponse(society));
    }

    public JSONObject deleteSociety(String societyReference) throws ContactApiException {

        Society society = societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));

        societyContactService.deleteSocietyContacts(societyReference);

        societyRepository.delete(society);

        return MiscUtils.createSuccessfullyResult();
    }

    public ContactDTO updateSocietyContact(ContactDTO contact, String societyReference) {
        Society society = societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));
        mapper.updateBeanFromDto(contact, society.getContact());

        society = societyRepository.save(society);

        return mapper.fromBeanToDTO(society.getContact(), societyReference);

    }

    public ContactDTO getSocietyContact(String societyReference) {

        ContactDTO contact = getSocieties(societyReference).get(0).getContact();
        contact.setOwnerReference(societyReference);
        return contact;
    }

    public SocietyLegalInformationDTO updateSocietyLegalInformation(SocietyLegalInformationDTO legalInformation, String societyReference) {

        Society society = societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));

        mapper.updateBeanFromDto(legalInformation, society.getLegalInformation());

        society = societyRepository.save(society);

        return mapper.fromBeanToDTO(society.getLegalInformation(), societyReference);

    }

    public SocietyLegalInformationDTO getSocietyLegalInformation(String societyReference) {

        SocietyLegalInformationDTO legalInformation = getSocieties(societyReference).get(0).getLegalInformation();
        legalInformation.setSocietyReference(societyReference);
        return legalInformation;
    }

    public long societyCount() {
        return societyRepository.count();
    }

}
