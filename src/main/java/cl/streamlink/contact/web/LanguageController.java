package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Language;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.LanguageService;
import cl.streamlink.contact.web.dto.LanguageDTO;
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
@RequestMapping("/ws/languages")
public class LanguageController {
    @Inject
    private LanguageService languageService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Language Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Language.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public LanguageDTO createLanguage(@RequestBody @Valid LanguageDTO language) {

        return languageService.createLanguage(language);
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Language Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Language.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public LanguageDTO updateLanguage(@Valid @RequestBody LanguageDTO language, @RequestParam(value = "reference") String reference) throws ContactApiException {

        return languageService.updateLanguage(language, reference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Language Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Language.class),
            @ApiResponse(code = 404, message = "Language with Ref not Found")
    })
    public List<LanguageDTO> getLanguage(@RequestParam(value = "reference", required = false) String reference) throws ContactApiException {

        return languageService.getLanguage(reference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Language Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Language.class),
            @ApiResponse(code = 404, message = "Language with Ref not Found")
    })
    public JSONObject deleteLanguage(@RequestParam("reference") String reference) throws ContactApiException {

        return languageService.deleteLanguage(reference);
    }

}
