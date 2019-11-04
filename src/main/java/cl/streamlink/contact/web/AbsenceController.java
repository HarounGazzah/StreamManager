package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Absence;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.AbsenceService;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.web.dto.hireability.AbsenceDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ws")
public class AbsenceController {

    @Inject
    private AbsenceService absenceService;
    @Inject
    private UserService userService;

    @RequestMapping(value = "absence",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Absence Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Absence.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public AbsenceDTO createAbsence(@RequestBody @Valid AbsenceDTO absence) {

        return  absenceService.createAbsence(absence);
    }


  /*  @GetMapping

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Get lineTemps Details Service")

    @ApiResponses(value = {

            // @ApiResponse(code = 200, message = "Operation Executed Successfully", response = TimeLine.class),

            @ApiResponse(code = 404, message = "lineTemps with Ref not Found")})

    public AbsenceDTO getLineTemps(@RequestParam(value = "absenceReference") String lineTempsReference) throws ContactApiException {
        return timeLineService.getLine(lineTempsReference);
    }*/

    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Absence Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Absence.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<AbsenceDTO> getAbsences() {
        return absenceService.getAbcences(null);
    }


    @RequestMapping(value = "managerResource",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Absence.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<AbsenceDTO> getResourceManager(@RequestParam(value = "managerReference") String managerReference,
                                               @RequestParam(value = "resourceReference") String resourceReference) throws ContactApiException {
        return absenceService.getAbsenceByMangerAndResource(managerReference, resourceReference);
    }

    @RequestMapping(value = "manager",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Absence.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<AbsenceDTO> getResourceManager() throws ContactApiException {
        String refmanager = userService.getCurrentUser().getReference();
        return absenceService.getAbsenceByManger(refmanager );
    }

    @RequestMapping(value = "absence",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create absence Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response =  Absence.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public AbsenceDTO updateAbsence(@Valid @RequestBody AbsenceDTO absenceDTO, @RequestParam(value = "absenceReference") String absenceReference) throws ContactApiException {

        return absenceService.updateAbsence(absenceDTO,absenceReference);

    }



    @RequestMapping(value = "absenceListReference",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Absence Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Absence.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<AbsenceDTO> getAbsenceByAbsenceListReference( @RequestParam(value = "absenceListReference") String absenceListReference) throws ContactApiException {

        return absenceService.getAbsenceByAbseceListReference(absenceListReference);
    }
}
