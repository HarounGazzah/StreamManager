package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Evaluation;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.repository.EvaluationRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.EvaluationDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EvaluationService {

    private final Logger logger = LoggerFactory.getLogger(DeveloperService.class);

    @Inject
    private EvaluationRepository evaluationRepository;

    @Inject
    private DeveloperRepository developerRepository;

    @Inject
    private UserService userService;


    @Inject
    private ApiMapper mapper;

    public EvaluationDTO createEvaluation(EvaluationDTO evaluationDTO, String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        evaluationDTO.setReference(MiscUtils.generateReference());
        evaluationDTO.setDeveloperReference(developerReference);
        evaluationDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Evaluation evaluation = mapper.fromDTOToBean(evaluationDTO);

        return mapper.fromBeanToDTO(evaluationRepository.save(evaluation));
    }

    public EvaluationDTO updateEvaluation(EvaluationDTO evaluationDTO, String reference, String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Evaluation evaluation = evaluationRepository.findOneByReferenceAndDeveloperReference(reference, developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Evaluation", reference));

        mapper.updateBeanFromDto(evaluationDTO, evaluation);

        evaluation = evaluationRepository.save(evaluation);

        return mapper.fromBeanToDTO(evaluation);

    }

    public List<EvaluationDTO> getEvaluation(String reference, String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        if (MiscUtils.isEmpty(reference))
            return evaluationRepository.findByDeveloperReference(developerReference).
                    stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
        else {
            Evaluation evaluation = evaluationRepository.findOneByReferenceAndDeveloperReference(reference, developerReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Evaluation", reference));

            return Collections.singletonList(mapper.fromBeanToDTO(evaluation));
        }
    }

    public JSONObject deleteEvaluation(String reference, String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Evaluation evaluation = evaluationRepository.findOneByReferenceAndDeveloperReference(reference, developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Evaluation", reference));

        evaluationRepository.delete(evaluation);
        return MiscUtils.createSuccessfullyResult();


    }
}
