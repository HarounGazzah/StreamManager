package cl.streamlink.contact.web;


import cl.streamlink.contact.domain.Need;
import cl.streamlink.contact.domain.ProjectPos;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.ProjectPosService;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.ProjectPosDTO;
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
@RequestMapping("/ws/projectspos")
public class ProjectPosController {

    @Inject
    ProjectPosService projectPosService;

    @PostMapping(value = "from-positioning")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Project Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ProjectPos.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ProjectPosDTO createProjectFromPositioning(
            @RequestParam(value = "positioningReference") String positioningReference) {
        return projectPosService.createProjectFromPositioning(positioningReference);
    }

    @GetMapping(value = "all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Projects Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ProjectPos.class),
            @ApiResponse(code = 404, message = "Project with Ref not Found")
    })
    public List<ProjectPosDTO> getProjects() {

        return projectPosService.getProjects(null);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Project Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ProjectPos.class),
            @ApiResponse(code = 404, message = "Project with Ref not Found")
    })
    public ProjectPosDTO getProject(@RequestParam(value = "projectReference") String projectReference) throws ContactApiException {
        return projectPosService.getProject(projectReference);
    }

    @PutMapping
    @ApiOperation(value = "Create Project Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Need.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")})
    public ProjectPosDTO updateProject(@Valid @RequestBody ProjectPosDTO project,
                                       @RequestParam(value = "projectReference") String projectReference) throws ContactApiException {

        return projectPosService.updateProject(project, projectReference);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<ProjectPosDTO> getProjects(Pageable pageable, @RequestParam boolean fromAngular,
                                           @RequestParam(required = false) String value,
                                           @RequestParam(required = false) Sort.Direction dir) {

        if (fromAngular) {

            pageable = MiscUtils.convertFromAngularPage(pageable, dir, true);

        }

        return projectPosService.searchProjects(value, pageable);

    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Project Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ProjectPos.class),
            @ApiResponse(code = 404, message = "Project with Ref not Found")
    })
    public JSONObject deleteProject(@RequestParam("projectReference") String projectReference) throws ContactApiException {

        return projectPosService.deleteProject(projectReference);
    }

    @GetMapping(value = "count")
    public long projectCount(){
        return projectPosService.projectCount();
    }
}
