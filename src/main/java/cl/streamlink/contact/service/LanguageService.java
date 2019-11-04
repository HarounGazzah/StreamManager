package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Language;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.LanguageRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.LanguageDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService {
    private final Logger logger = LoggerFactory.getLogger(LanguageService.class);
    @Inject
    private LanguageRepository languageRepository;

    @Inject
    private ApiMapper mapper;

    public LanguageDTO createLanguage(LanguageDTO languageDTO) {

        languageDTO.setReference(MiscUtils.generateReference());
        Language language = mapper.fromDTOToBean(languageDTO);

        return mapper.fromBeanToDTO(languageRepository.save(language));
    }

    public LanguageDTO updateLanguage(LanguageDTO languageDTO, String reference) throws ContactApiException {

        Language language = languageRepository.findOneByReference(reference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Language", reference));
        mapper.updateBeanFromDto(languageDTO, language);
        return mapper.fromBeanToDTO(languageRepository.save(language));
    }


    public List<LanguageDTO> getLanguage(String reference) throws ContactApiException {

        if (reference != null)
            return Collections.singletonList(mapper.fromBeanToDTO(languageRepository.findOneByReference(reference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Language", reference))));

        else
            return languageRepository.findAll().stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }

    public JSONObject deleteLanguage(String reference) throws ContactApiException {

        Language language = languageRepository.findOneByReference(reference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Language", reference));

        languageRepository.delete(language);

        JSONObject result = new JSONObject();
        result.put("result", "delete-success");
        return result;
    }


}
