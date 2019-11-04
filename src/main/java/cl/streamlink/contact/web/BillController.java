package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Bill;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.BillService;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.BillStage;
import cl.streamlink.contact.web.dto.BillDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/ws/bills")
public class BillController {

    @Inject
    BillService billService;

    @GetMapping(value = "all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all bills")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Bill.class),
            @ApiResponse(code = 404, message = "bills not Found")})
    public List<BillDTO> getBills() {
        return billService.getBills();
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<BillDTO> getBills(Pageable pageable, @RequestParam boolean fromAngular,
                                  @RequestParam(required = false) String value,
                                  @RequestParam(required = false) BillStage billStage,
                                  @RequestParam(required = false) String label,
                                  @RequestParam(required = false) Sort.Direction dir) {
        if (fromAngular) {
            pageable = MiscUtils.convertFromAngularPage(pageable, dir, true);
        }
        return billService.searchBills(value, billStage, label, pageable);
    }

    @GetMapping(value = "one")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Bill Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = BillDTO.class),
            @ApiResponse(code = 404, message = "Bill with Ref not Found")
    })
    public BillDTO getBillByReference(@RequestParam(value = "billReference") String billReference) throws ContactApiException {
        return billService.getBillByReference(billReference);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Bill Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Bill.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public BillDTO updateResource(@Valid @RequestBody BillDTO bill, @RequestParam(value = "billReference") String billReference) throws ContactApiException {

        return billService.updateBill(bill, billReference);
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Bill Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Bill.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public JSONObject deleteBill(@RequestParam("billReference") String billReference) throws ContactApiException {

        return billService.deleteBill(billReference);
    }

    @GetMapping(value = "count")
    public long billCount(){
        return billService.billCount();
    }

    @GetMapping(value = "byType")
    public List<BillDTO> getBillsByType (@RequestParam(value = "billStage") BillStage billStage) {
        return billService.getBillByType(billStage);
    }
}
