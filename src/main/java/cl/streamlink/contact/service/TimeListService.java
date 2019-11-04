package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.TimeList;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.TimeListRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.TimeListDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
public class TimeListService {


    private final Logger logger = LoggerFactory.getLogger(TimeListService.class);

    @Inject
    private TimeListRepository timeListRepository;

    @Inject
    private ApiMapper mapper;


    public TimeListDTO getListTemps(String listTempsReference) throws ContactApiException {

        return mapper.fromBeanToDTO(timeListRepository.findOneByReference(
                listTempsReference).orElseThrow(() ->
                ContactApiException.resourceNotFoundExceptionBuilder("TimeList", listTempsReference)));
    }


    public JSONObject deleteListTemps(String listTempsReference) throws ContactApiException {


        timeListRepository.deleteByReference(listTempsReference);

        return MiscUtils.createSuccessfullyResult();
    }


    public TimeListDTO createListTemps(TimeListDTO listTempsDTO) {

        TimeList timeList = mapper.fromDTOToBean(listTempsDTO);

        timeList.setReference(MiscUtils.generateReference());
        return
                mapper.fromBeanToDTO(timeListRepository.save(timeList));
    }


    public TimeListDTO updateTimeList(TimeListDTO timeListDTO, String timeListReference) throws ContactApiException {


        TimeList timeList = timeListRepository.findOneByReference(timeListReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", timeListReference));

        mapper.updateBeanFromDto(timeListDTO,timeList);
        return mapper.fromBeanToDTO(timeListRepository.save(timeList));
    }

}
