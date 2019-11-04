package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.CurriculumVitaeService;
import cl.streamlink.contact.service.DeveloperService;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.Experience;
import cl.streamlink.contact.utils.enums.Formation;
import cl.streamlink.contact.utils.enums.Stage;
import cl.streamlink.contact.web.dto.*;
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
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ws/developers")
@CrossOrigin("*")
public class DeveloperController {

    @Inject
    private DeveloperService developerService;

    @Inject
    private CurriculumVitaeService curriculumVitaeService;
    @Inject
    private UserService userservice;

    Date actuelDate = new Date();


    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Developer Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public DeveloperDTO createDeveloper(@RequestBody @Valid DeveloperDTO developer) {

        return developerService.createDeveloper(developer);
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Developer Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public DeveloperDTO updateDeveloper(@Valid @RequestBody DeveloperDTO developer, @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return developerService.updateDeveloper(developer, developerReference);
    }

    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<DeveloperResponseDTO> getDevelopers() {
        return developerService.getDevelopers(null);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<DeveloperResponseDTO> getDevelopers(Pageable pageable, @RequestParam boolean fromAngular,
                                                    @RequestParam(required = false) String value,
                                                    @RequestParam(required = false) Formation formation,
                                                    @RequestParam(required = false) Stage stage,
                                                    @RequestParam(required = false) Experience experience,
                                                    @RequestParam(required = false) Sort.Direction dir) {

        if (fromAngular) {

            pageable = MiscUtils.convertFromAngularPage(pageable, dir, false);
        }
        return developerService.searchDevelopers(value, stage, experience, formation, pageable);

    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = DeveloperDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public DeveloperDTO getDeveloper(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return developerService.getDeveloper(developerReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Developer Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public JSONObject deleteDeveloper(@RequestParam("developerReference") String developerReference) throws ContactApiException {

        return developerService.deleteDeveloper(developerReference);
    }
    //contact

    @RequestMapping(value = "contact",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Developer Contact Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContactDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ContactDTO updateDeveloperContact(@Valid @RequestBody ContactDTO contact, @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return developerService.updateDeveloperContact(contact, developerReference);
    }

    @RequestMapping(value = "contact",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Contact Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContactDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public ContactDTO getDeveloperContact(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return developerService.getDeveloperContact(developerReference);
    }
    //skills

    @RequestMapping(value = "skills",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Developer Skills Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SkillsInformationDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public SkillsInformationDTO updateDeveloperSkills(@Valid @RequestBody SkillsInformationDTO skillsInformation
            , @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return developerService.updateDeveloperSkills(skillsInformation, developerReference);
    }

    @RequestMapping(value = "skills",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Contact Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SkillsInformationDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public SkillsInformationDTO getDeveloperSkills(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return developerService.getDeveloperSkills(developerReference);
    }

    /*@RequestMapping(value = "skills/languages",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "add Language to developer skills")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = DeveloperResponseDTO.class),
            @ApiResponse(code = 404, message = "Developer or language with Ref not Found")
    })
    public DeveloperResponseDTO addLanguage(@RequestParam(value = "developerReference") String developerReference,
                                            @RequestParam(value = "languageReference") String languageReference) throws ContactApiException {
        return developerService.addLanguage(developerReference, languageReference);
    }

    @RequestMapping(value = "skills/languages",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove Language to developer skills")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = DeveloperResponseDTO.class),
            @ApiResponse(code = 404, message = "Developer or language with Ref not Found")
    })
    public DeveloperResponseDTO removeLanguage(@RequestParam(value = "developerReference") String developerReference,
                                               @RequestParam(value = "languageReference") String languageReference) throws ContactApiException {
        return developerService.removeLanguage(developerReference, languageReference);
    }*/

    //personal
    @RequestMapping(value = "personal_info",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Developer personal Information Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = PersonalInformationDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public PersonalInformationDTO updateDeveloperPersonalInformation(@Valid @RequestBody PersonalInformationDTO personalInformation, @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return developerService.updateDeveloperPersonalInformation(personalInformation, developerReference);
    }

    @RequestMapping(value = "personal_info",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer personal Information Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = PersonalInformationDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public PersonalInformationDTO getDeveloperPersonalInformation(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return developerService.getDeveloperPersonalInformation(developerReference);
    }

    @PostMapping(value = "cv",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add developer from CV")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = DeveloperResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public DeveloperResponseDTO addDeveloperFromCV(@RequestPart(value = "cv") MultipartFile cv) throws IOException {

        return curriculumVitaeService.generateFromCv(cv);
    }

    @PutMapping(value = "cv",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add developer CV")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = CurriculumVitaeDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public CurriculumVitaeDTO addDeveloperCV(@RequestParam String developerReference, @RequestPart(value = "cv") MultipartFile cv) throws IOException {

        return curriculumVitaeService.addDeveloperCV(cv, developerReference);
    }

    @DeleteMapping(value = "cv",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove developer CV")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = CurriculumVitaeDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public JSONObject removeDeveloperCV(@RequestParam String reference, @RequestParam String developerReference) {

        return curriculumVitaeService.removeDeveloperCV(reference, developerReference);
    }

    @GetMapping(value = "cv",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer CVs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = DeveloperDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<CurriculumVitaeDTO> findDeveloperCVs(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return curriculumVitaeService.findDeveloperCVs(developerReference);
    }




    @RequestMapping(value = "mail",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = DeveloperDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public DeveloperDTO getCurrentDeveloperByEmail( String developerEmail) throws ContactApiException {
      // String developerEmailj = userservice.getCurrentUser().getEmail();
        return developerService.getDeveloperEmail(userservice.getCurrentUser().getEmail());
    }

   @RequestMapping(value = "manager",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<DeveloperDTO> getDevelopersManager(@RequestParam(value = "managerReference") String managerReference) throws ContactApiException {
       return developerService.getDeveloperByManger(managerReference);
   }
    @RequestMapping(value = "currentResource",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public DeveloperDTO getCurrentDeveloper() throws ContactApiException {
        return developerService.getDeveloperEmail(userservice.getCurrentUser().getEmail());
    }




    @RequestMapping(value = "currentLeave",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public int currentLeave() throws ContactApiException {
        return (actuelDate.getMonth()+1) -( developerService.getDeveloperEmail(userservice.getCurrentUser().getEmail()).getAvailability().getMonthValue());
    }

    @GetMapping(value = "count")
    public long developerCount(){
        return developerService.developerCount();
    }
}
