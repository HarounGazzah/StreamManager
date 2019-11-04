package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Need;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.NeedService;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.ActivityArea;
import cl.streamlink.contact.utils.enums.NeedStage;
import cl.streamlink.contact.utils.enums.NeedType;
import cl.streamlink.contact.web.dto.NeedDTO;
import cl.streamlink.contact.web.dto.NeedInformationDTO;
import cl.streamlink.contact.web.dto.NeedResponseDTO;
import cl.streamlink.contact.web.dto.SkillsInformationDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ws/needs")
public class NeedController {

    @Inject
    private NeedService needService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Need Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Need.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")})
    public NeedDTO createNeed(@RequestBody @Valid NeedDTO need) {

        return needService.createNeed(need);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Need Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Need.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")})
    public NeedDTO updateNeed(@Valid @RequestBody NeedDTO need,
                              @RequestParam(value = "needReference") String needReference) throws ContactApiException {

        return needService.updateNeed(need, needReference);
    }

    @GetMapping(value = "all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Need Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Need.class),
            @ApiResponse(code = 404, message = "Need with Ref not Found")})
    public List<NeedResponseDTO> getNeeds() {
        return needService.getNeeds(null);
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<NeedResponseDTO> getNeeds(Pageable pageable, @RequestParam boolean fromAngular,
                                          @RequestParam(required = false) String value,
                                          @RequestParam(required = false) ActivityArea activityArea,
                                          @RequestParam(required = false) NeedType type,
                                          @RequestParam(required = false) NeedStage stage,
                                          @RequestParam(required = false) Sort.Direction dir) {

        if (fromAngular) {

            pageable = MiscUtils.convertFromAngularPage(pageable, dir, true);

        }

        return needService.searchNeeds(value, stage, type, activityArea, pageable);

    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Need Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = NeedDTO.class),
            @ApiResponse(code = 404, message = "Need with Ref not Found")})
    public NeedDTO getNeed(@RequestParam(value = "needReference") String needReference) throws ContactApiException {
        return needService.getNeed(needReference);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Need Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Need.class),
            @ApiResponse(code = 404, message = "Need with Ref not Found")})
    public JSONObject deleteNeed(@RequestParam("needReference") String needReference) throws ContactApiException {

        return needService.deleteNeed(needReference);
    }

    // information

    @RequestMapping(value = "information", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Need legal information Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SkillsInformationDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")})
    public NeedInformationDTO updateNeedInformation(@Valid @RequestBody NeedInformationDTO legalInformation,
                                                    @RequestParam(value = "needReference") String needReference) throws ContactApiException {

        return needService.updateNeedInformation(legalInformation, needReference);
    }

    @RequestMapping(value = "information", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Need legal information Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = NeedInformationDTO.class),
            @ApiResponse(code = 404, message = "Need with Ref not Found")})
    public NeedInformationDTO getNeedInformation(@RequestParam(value = "needReference") String needReference)
            throws ContactApiException {
        return needService.getNeedInformation(needReference);
    }

    @RequestMapping(value = "/auto-complete", method = RequestMethod.GET)
    public List<NeedResponseDTO> getNeeds(@RequestParam(required = false) String term) {
        return needService.searchNeeds(term);

    }

    @GetMapping(value = "count")
    public long needCount(){
        return needService.needCount();
    }
}
