package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Bill;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.BillRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.BillStage;
import cl.streamlink.contact.web.dto.BillDTO;
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {

    @Inject
    private BillRepository billRepository;

    @Inject
    private ApiMapper mapper;

    public List<BillDTO> getBills() {
        return billRepository.findAll().stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }

    public Page<BillDTO> searchBills(String value, BillStage billStage, String label, Pageable pageable) {
        if (MiscUtils.isEmpty(value))
            value = "";

        List<BillStage> billStages;
        if (billStage != null)
            billStages = Collections.singletonList(billStage);
        else
            billStages = BillStage.getAll();
        if (MiscUtils.isEmpty(label))
            label = "";


        return billRepository.findByTitleContainingAndBillStageInAndSocietyContact_SocietyLabelContaining(value, billStages, label, pageable)
                .map(bill -> mapper.fromBeanToDTO(bill));
    }

    public BillDTO getDeveloper(String resourceReference) throws ContactApiException {
        return mapper.fromBeanToDTO(billRepository.findByResourceReference(resourceReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Bill", resourceReference)));
    }

    public BillDTO getBillByReference(String billReference) throws ContactApiException {
        return mapper.fromBeanToDTO(billRepository.findOneByReference(billReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Bill", billReference)));
    }

    public BillDTO updateBill(BillDTO billDTO, String billReference) throws ContactApiException {

        Bill bill = billRepository.findOneByReference(billReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Bill", billReference));

        mapper.updateBeanFromDto(billDTO, bill);
        return mapper.fromBeanToDTO(billRepository.save(bill));
    }

    public JSONObject deleteBill(String billReference) throws ContactApiException {

        Bill bill = billRepository.findOneByReference(billReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Bill", billReference));

        billRepository.delete(bill);

        return MiscUtils.createSuccessfullyResult();
    }

    public long billCount() {
        return billRepository.count();
    }

    public List<BillDTO> getBillByType(BillStage billStage) {
        return billRepository.findByBillStage(billStage)
                .stream()
                .map(mapper::fromBeanToDTO)
                .collect(Collectors.toList());
    }

}
