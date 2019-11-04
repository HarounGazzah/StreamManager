package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.TimeLine;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.TimeLineService;
import cl.streamlink.contact.web.dto.TimeLineDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ws/time_line")
public class TimeLineController {


    @Inject
    private TimeLineService timeLineService;

    @GetMapping

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Get lineTemps Details Service")

    @ApiResponses(value = {

            // @ApiResponse(code = 200, message = "Operation Executed Successfully", response = TimeLine.class),

            @ApiResponse(code = 404, message = "lineTemps with Ref not Found")})

    public TimeLineDTO getLineTemps(@RequestParam(value = "ligneTempsReference") String lineTempsReference) throws ContactApiException {
        return timeLineService.getLine(lineTempsReference);
    }


    @RequestMapping(value = "", method = RequestMethod.DELETE, produces =
            MediaType.APPLICATION_JSON_VALUE)

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Delete ligne Temps Service")

    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Operation Executed Successfully",
                    response = TimeLine.class),

            @ApiResponse(code = 404, message = "ligneTemps with Ref not Found")})
    public JSONObject deleteLigneTemps(@RequestParam("ligneTempsReference") String
                                               ligneTempsReference) throws ContactApiException {

        return timeLineService.deleteLineTemps(ligneTempsReference);
    }


    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create TimeLine Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = TimeLine.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })

    public TimeLineDTO createLineTemps(@RequestBody @Valid TimeLineDTO lineTemps) {

        return timeLineService.createLineTemps(lineTemps);
    }

   @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create ligne temps Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response =  TimeLine.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public TimeLineDTO updateProject(@Valid @RequestBody TimeLineDTO lineTemps, @RequestParam(value = "ligneTempsReference") String lineTempsReference) throws ContactApiException {

        return timeLineService.updateLineTemps(lineTemps,lineTempsReference);

    }


    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get timeLines Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response =TimeLine.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<TimeLineDTO> getDevelopers() {
        return timeLineService.getDeTimeLines(null);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TimeLine saveTimeLine(@RequestBody TimeLine timeLine) {
        System.out.println(timeLine);
        return timeLineService.save(timeLine);
    }

    @RequestMapping(value = "timelines", method = RequestMethod.GET)
    public  List<TimeLine> getAllTimeLines() {
        return timeLineService.getAllTimeLines();
    }
}
