package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.TimeList;
import cl.streamlink.contact.domain.SocietyContact;
import cl.streamlink.contact.domain.TimeLine;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.AttachedFileService;
import cl.streamlink.contact.service.TimeListService;
import cl.streamlink.contact.web.dto.TimeListDTO;
import cl.streamlink.contact.web.dto.hireability.AttachedFileDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequestMapping("/ws/time_list")
public class TimeListController {
    @Inject
    private TimeListService timeListService;
    @Inject
    private AttachedFileService attachedFileService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Get Project Details Service")

    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Operation Executed Successfully",
                    response = TimeList.class),

            @ApiResponse(code = 404, message = "listeTemps with Ref not Found")})
    public TimeListDTO getListeTemps(@RequestParam(value = "listeTempsReference") String listeTempsReference) throws ContactApiException {

        return timeListService.getListTemps(listeTempsReference);


    }


    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete ligne temps Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SocietyContact.class),
            @ApiResponse(code = 404, message = "listeTemps with Ref not Found")
    })
    public JSONObject deleteLigneTemps(@RequestParam("listeTempsReference") String listeTempsReference) throws ContactApiException {

        return timeListService.deleteListTemps(listeTempsReference);
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces =
            MediaType.APPLICATION_JSON_VALUE)

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Create TimeLine Service")

    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Operation Executed Successfully",
                    response = TimeLine.class),

            @ApiResponse(code = 400, message = "Validation Error, Database conflict")})

    public TimeListDTO createListTemps(@RequestBody @Valid TimeListDTO listTemps) {

        return timeListService.createListTemps(listTemps);
    }

    @RequestMapping(value = "",
           method = RequestMethod.PUT,
           produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseStatus(HttpStatus.OK)
   @ApiOperation(value = "Create TimeList Service")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Operation Executed Successfully", response = TimeList.class),
           @ApiResponse(code = 400, message = "Validation Error, Database conflict")
  })
   public TimeListDTO  updateProject(@Valid @RequestBody TimeListDTO  timeListDTO, @RequestParam(value = "projectReference") String projectReference) throws ContactApiException {

       return timeListService.updateTimeList(timeListDTO, projectReference);
    }


    @PutMapping(value = "file",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add timelist file")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AttachedFileDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public AttachedFileDTO addDeveloperCV(@RequestParam String resourceReference,@RequestParam String timelistReference, @RequestPart(value = "file") MultipartFile file) throws IOException {

        return attachedFileService.addTimeListFile(file,resourceReference,timelistReference);
                //addDeveloperCV(cv, developerReference);
    }
}
