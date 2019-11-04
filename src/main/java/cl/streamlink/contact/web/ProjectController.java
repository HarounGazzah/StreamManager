//package cl.streamlink.contact.web;
//
//import cl.streamlink.contact.domain.Project;
//import cl.streamlink.contact.exception.ContactApiException;
//import cl.streamlink.contact.service.ProjectService;
//import cl.streamlink.contact.utils.MiscUtils;
//import cl.streamlink.contact.utils.enums.ActivityArea;
//import cl.streamlink.contact.utils.enums.ProjectStage;
//import cl.streamlink.contact.utils.enums.ProjectType;
//import cl.streamlink.contact.web.dto.ProjectDTO;
//import cl.streamlink.contact.web.dto.ProjectInformationDTO;
//import cl.streamlink.contact.web.dto.ProjectResponseDTO;
//import cl.streamlink.contact.web.dto.SkillsInformationDTO;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import net.minidev.json.JSONObject;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import javax.inject.Inject;
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/ws/projects")
//public class ProjectController {
//
//    @Inject
//    private ProjectService projectService;
//
//
////    @RequestMapping(value = "from-positioning",
////            method = RequestMethod.POST,
////            produces = MediaType.APPLICATION_JSON_VALUE)
////    @ResponseStatus(HttpStatus.OK)
////    @ApiOperation(value = "Create Project Service")
////    @ApiResponses(value = {
////            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Project.class),
////            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
////    })
////    public ProjectDTO createResourceFromDeveloper(@RequestParam(value = "positioningReference") String positioningReference) {
////
////        return projectService.createProjectFromPositioning(positioningReference);
////    }
//
////    @RequestMapping(value = "from-need",
////            method = RequestMethod.POST,
////            produces = MediaType.APPLICATION_JSON_VALUE)
////    @ResponseStatus(HttpStatus.OK)
////    @ApiOperation(value = "Create Project Service")
////    @ApiResponses(value = {
////            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Project.class),
////            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
////    })
////    public ProjectDTO createResourceFromDeveloper(@RequestParam(value = "needReference") String needReference) {
////
////        return projectService.createProjectFromNeed(needReference);
////    }
//
//
//    @RequestMapping(value = "",
//            method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Create Project Service")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Project.class),
//            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
//    })
//    public ProjectDTO createProject(@RequestBody @Valid ProjectDTO project) {
//
//        return projectService.createProject(project);
//    }
//
//    @RequestMapping(value = "",
//            method = RequestMethod.PUT,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Create Project Service")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Project.class),
//            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
//    })
//    public ProjectDTO updateProject(@Valid @RequestBody ProjectDTO project, @RequestParam(value = "projectReference") String projectReference) throws ContactApiException {
//
//        return projectService.updateProject(project, projectReference);
//    }
//
//    @RequestMapping(value = "all",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Get Project Details Service")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Project.class),
//            @ApiResponse(code = 404, message = "Project with Ref not Found")
//    })
//    public List<ProjectResponseDTO> getProjects() {
//        return projectService.getProjects(null);
//    }
//
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public Page<ProjectResponseDTO> getProjects(Pageable pageable, @RequestParam boolean fromAngular,
//                                                @RequestParam(required = false) String value,
//                                                @RequestParam(required = false) ActivityArea activityArea,
//                                                @RequestParam(required = false) ProjectType type,
//                                                @RequestParam(required = false) ProjectStage stage,
//                                                @RequestParam(required = false) Sort.Direction dir) {
//
//        if (fromAngular) {
//
//            pageable = MiscUtils.convertFromAngularPage(pageable, dir, true);
//
//        }
//
//        return projectService.searchProjects(value, stage, type, activityArea, pageable);
//
//    }
//
//    @RequestMapping(value = "",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Get Project Details Service")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ProjectDTO.class),
//            @ApiResponse(code = 404, message = "Project with Ref not Found")
//    })
//    public ProjectDTO getProject(@RequestParam(value = "projectReference") String projectReference) throws ContactApiException {
//        return projectService.getProject(projectReference);
//    }
//
//    @RequestMapping(value = "",
//            method = RequestMethod.DELETE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Delete Project Service")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Project.class),
//            @ApiResponse(code = 404, message = "Project with Ref not Found")
//    })
//    public JSONObject deleteProject(@RequestParam("projectReference") String projectReference) throws ContactApiException {
//
//        return projectService.deleteProject(projectReference);
//    }
//
//    //information
//
//    @RequestMapping(value = "information",
//            method = RequestMethod.PUT,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "update Project legal information Service")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SkillsInformationDTO.class),
//            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
//    })
//    public ProjectInformationDTO updateProjectInformation(@Valid @RequestBody ProjectInformationDTO legalInformation
//            , @RequestParam(value = "projectReference") String projectReference) throws ContactApiException {
//
//        return projectService.updateProjectInformation(legalInformation, projectReference);
//    }
//
//    @RequestMapping(value = "information",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Get Project legal information Details Service")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ProjectInformationDTO.class),
//            @ApiResponse(code = 404, message = "Project with Ref not Found")
//    })
//    public ProjectInformationDTO getProjectInformation(@RequestParam(value = "projectReference") String projectReference) throws ContactApiException {
//        return projectService.getProjectInformation(projectReference);
//    }
//
//    @RequestMapping(value = "/auto-complete", method = RequestMethod.GET)
//    public List<ProjectResponseDTO> getProjects(@RequestParam(required = false) String term) {
//        return projectService.searchProjects(term);
//
//    }
//
//
//}


