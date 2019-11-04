package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.CurriculumVitaeService;
import cl.streamlink.contact.service.DeveloperService;
import cl.streamlink.contact.service.ResourceService;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.Experience;
import cl.streamlink.contact.utils.enums.Formation;
import cl.streamlink.contact.utils.enums.ResourceStage;
import cl.streamlink.contact.utils.enums.ResourceType;
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
import java.util.List;

@RestController
@RequestMapping("/ws/resources")
public class ResourceController {

    @Inject
    private ResourceService resourceService;

    @Inject
    private DeveloperService developerService;

    @Inject
    private CurriculumVitaeService curriculumVitaeService;

    @RequestMapping(value = "from-developer",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Resource Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Resource.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ResourceDTO createResourceFromDeveloper(@RequestParam(value = "developerReference") String developerReference) {

        return resourceService.createResourceFromDeveloper(developerReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Resource Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Resource.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ResourceDTO createResource(@RequestBody @Valid ResourceDTO resource) {

        return resourceService.createResource(resource);
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Resource Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Resource.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ResourceDTO updateResource(@Valid @RequestBody ResourceDTO resource, @RequestParam(value = "resourceReference") String resourceReference) throws ContactApiException {

        return resourceService.updateResource(resource, resourceReference);
    }

    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resource Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Resource.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public List<ResourceResponseDTO> getResources() {
        return resourceService.getResources(null);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<ResourceResponseDTO> getResources(Pageable pageable, @RequestParam boolean fromAngular,
                                                  @RequestParam(required = false) String value,
                                                  @RequestParam(required = false) Formation formation,
                                                  @RequestParam(required = false) ResourceStage stage,
                                                  @RequestParam(required = false) Experience experience,
                                                  @RequestParam(required = false) ResourceType type,
                                                  @RequestParam(required = false) Sort.Direction dir) {

        if (fromAngular) {

            pageable = MiscUtils.convertFromAngularPage(pageable, dir, false);
        }
        return resourceService.searchResources(value, stage, experience, formation, type, pageable);

    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resource Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ResourceDTO.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public ResourceDTO getResource(@RequestParam(value = "resourceReference") String resourceReference) throws ContactApiException {
        return resourceService.getResource(resourceReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Resource Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Resource.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public JSONObject deleteResource(@RequestParam("resourceReference") String resourceReference) throws ContactApiException {

        return resourceService.deleteResource(resourceReference);
    }
    //contact

    @RequestMapping(value = "contact",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Resource Contact Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContactDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ContactDTO updateResourceContact(@Valid @RequestBody ContactDTO contact, @RequestParam(value = "resourceReference") String resourceReference) throws ContactApiException {

        return developerService.updateDeveloperContact(contact, resourceReference);
    }

    @RequestMapping(value = "contact",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resource Contact Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContactDTO.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public ContactDTO getResourceContact(@RequestParam(value = "resourceReference") String resourceReference) throws ContactApiException {
        return developerService.getDeveloperContact(resourceReference);
    }
    //skills

    @RequestMapping(value = "skills",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Resource Skills Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SkillsInformationDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public SkillsInformationDTO updateResourceSkills(@Valid @RequestBody SkillsInformationDTO skillsInformation
            , @RequestParam(value = "resourceReference") String resourceReference) throws ContactApiException {

        return developerService.updateDeveloperSkills(skillsInformation, resourceReference);
    }

    @RequestMapping(value = "skills",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resource Contact Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SkillsInformationDTO.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public SkillsInformationDTO getResourceSkills(@RequestParam(value = "resourceReference") String resourceReference) throws ContactApiException {
        return developerService.getDeveloperSkills(resourceReference);
    }

    @RequestMapping(value = "personal_info",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Resource personal Information Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = PersonalInformationDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public PersonalInformationDTO updateResourcePersonalInformation(@Valid @RequestBody PersonalInformationDTO personalInformation, @RequestParam(value = "resourceReference") String resourceReference) throws ContactApiException {

        return developerService.updateDeveloperPersonalInformation(personalInformation, resourceReference);
    }

    @RequestMapping(value = "personal_info",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resource personal Information Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = PersonalInformationDTO.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public PersonalInformationDTO getResourcePersonalInformation(@RequestParam(value = "resourceReference") String resourceReference) throws ContactApiException {
        return developerService.getDeveloperPersonalInformation(resourceReference);
    }


    @PutMapping(value = "cv",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add resource CV")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = CurriculumVitaeDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public CurriculumVitaeDTO addResourceCV(@RequestParam String resourceReference, @RequestPart(value = "cv") MultipartFile cv) throws IOException {

        return curriculumVitaeService.addDeveloperCV(cv, resourceReference);
    }

    @DeleteMapping(value = "cv",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove resource CV")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = CurriculumVitaeDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public JSONObject removeResourceCV(@RequestParam String reference, @RequestParam String resourceReference) {

        return curriculumVitaeService.removeDeveloperCV(reference, resourceReference);
    }

    @GetMapping(value = "cv",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resource CVs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ResourceDTO.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public List<CurriculumVitaeDTO> findResourceCVs(@RequestParam(value = "resourceReference") String resourceReference) throws ContactApiException {
        return curriculumVitaeService.findDeveloperCVs(resourceReference);
    }

    @RequestMapping(value = "/auto-complete", method = RequestMethod.GET)
    public List<ResourceResponseDTO> getResources(@RequestParam(required = false) String term) {
        return resourceService.searchResources(term);

    }

    @RequestMapping(value = "manager",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Resource.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<ResourceDTO> getResourceManager(@RequestParam(value = "managerReference") String managerReference) throws ContactApiException {
        return resourceService.getResourceByManger(managerReference);
    }

    @GetMapping(value = "count")
    public long resourceCount(){
        return resourceService.resourceCount();
    }

    @GetMapping(value = "byType")
    public List<ResourceDTO> getResourcesByType (@RequestParam(value = "resourceType") ResourceType resourceType) {
        return resourceService.getResourceByType(resourceType);
    }

}
