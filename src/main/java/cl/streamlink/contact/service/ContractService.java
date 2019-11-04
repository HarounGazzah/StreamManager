package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Contract;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.domain.WishedContract;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.ContractRepository;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.repository.WishedContractRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.ContractDTO;
import cl.streamlink.contact.web.dto.WishedContractDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ContractService {

    private final Logger logger = LoggerFactory.getLogger(ContractService.class);

    @Inject
    private ContractRepository contractRepository;

    @Inject
    private WishedContractRepository wishedContractRepository;

    @Inject
    private DeveloperRepository developerRepository;

    @Inject
    private UserService userService;

    @Inject
    private ApiMapper mapper;


    public ContractDTO createContract(ContractDTO contractDTO, String developerReference) {

        Developer developer = developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Contract contract = mapper.fromDTOToBean(contractDTO);
        contract.setReference("con" + MiscUtils.generateReference());
        contract.setResponsible(userService.getCurrentUser());
        contract.setDeveloper(developer);

        return mapper.fromBeanToDTO(contractRepository.save(contract));
    }

    public ContractDTO updateContract(ContractDTO contractDTO, String developerReference) {

        Developer developer = developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Contract contract = contractRepository.findOneByDeveloperReference(developerReference).orElse(null);

        if (contract == null) {
            contract = new Contract();
            contract.setReference(MiscUtils.generateReference());
            contract.setDeveloper(developer);
            contract.setResponsible(userService.getCurrentUser());

        }

        mapper.updateBeanFromDto(contractDTO, contract);
        return mapper.fromBeanToDTO(contractRepository.save(contract));
    }

    public ContractDTO getContract(String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Contract contract = contractRepository.findOneByDeveloperReference(developerReference).orElse(null);

        if (contract != null) {
            ContractDTO contractDTO = mapper.fromBeanToDTO(contract);
            contractDTO.setDeveloperReference(developerReference);

            return contractDTO;
        }

        return null;
    }

    public JSONObject deleteContract(String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Contract contract = contractRepository.findOneByDeveloperReference(developerReference).orElse(null);

        if (contract != null)
            contractRepository.delete(contract);

        return MiscUtils.createSuccessfullyResult();
    }

    public WishedContractDTO updateWishedContract(WishedContractDTO wishedContractDTO, String developerReference) {

        Developer developer = developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        WishedContract wishedContract = wishedContractRepository.findOneByDeveloperReference(developerReference).orElse(null);

        if (wishedContract == null) {
            wishedContract = new WishedContract();
            wishedContract.setReference(MiscUtils.generateReference());
            wishedContract.setDeveloper(developer);
            wishedContract.setResponsible(userService.getCurrentUser());
        }

        mapper.updateBeanFromDto(wishedContractDTO, wishedContract);
        return mapper.fromBeanToDTO(wishedContractRepository.save(wishedContract));
    }

    public WishedContractDTO getWishedContract(String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        WishedContractDTO wishedContract =
                mapper.fromBeanToDTO(wishedContractRepository.findOneByDeveloperReference(developerReference).orElse(new WishedContract()));

        wishedContract.setDeveloperReference(developerReference);
        return wishedContract;
    }
}
