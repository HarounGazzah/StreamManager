package cl.streamlink.contact.web;

import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.PhotoService;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.PhotoDTO;
import cl.streamlink.contact.web.dto.UserDTO;
import io.swagger.annotations.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("ws/users")
@Api(tags = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Inject
    private PhotoService photoService;

    @GetMapping("/login")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public JSONObject login(//
                            @ApiParam("Username") @RequestParam String username, //
                            @ApiParam("Password") @RequestParam String password) {

        return userService.signin(username, password);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserDTO signup(@ApiParam("Signup User") @RequestBody UserDTO user) {
        return userService.signup(user);
    }


    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Create User Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public UserDTO updateUser(@Valid @RequestBody UserDTO user, @RequestParam(value = "userReference") String userReference) throws ContactApiException {

        return userService.updateUser(user, userReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.delete}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = JSONObject.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public JSONObject deleteUser(@RequestParam("userReference") String userReference) throws ContactApiException {

        return userService.deleteUser(userReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get User Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = UserDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public UserDTO getUser(@RequestParam(value = "userReference") String userReference) throws ContactApiException {
        return userService.getUser(userReference);
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "Get All Users", response = UserDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.search}", response = UserDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserDTO search(@ApiParam("Username") @PathVariable String username) {
        return userService.search(username);
    }

    @GetMapping(value = "/current")
    @ApiOperation(value = "${UserController.me}", response = UserDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserDTO whoami(HttpServletRequest req) {
        //System.out.println("ssssss"+userService.whoami(req).getEmail());
        return userService.whoami(req);
    }

    @PutMapping(value = "current/password",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Change Password Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully"),
            @ApiResponse(code = 400, message = "Password Don't Match"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    public JSONObject changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        return userService.changeCurrentUserPassword(oldPassword, newPassword);
    }


    @PutMapping(value = "avatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add user avatar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = PhotoDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public PhotoDTO addDeveloperAvatar(@RequestParam String userReference, @RequestPart(value = "avatar") MultipartFile avatar) throws IOException {

        return photoService.addUserAvatar(avatar, userReference);
    }

    @DeleteMapping(value = "avatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove user avatar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = PhotoDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public JSONObject removeUserAvatar(@RequestParam String reference, @RequestParam String userReference) {

        return photoService.removeUserAvatar(reference, userReference);
    }

    @GetMapping(value = "avatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Users avatars")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = UserDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<PhotoDTO> findUserAvatars(@RequestParam(value = "userReference") String userReference) throws ContactApiException {
        return photoService.findUserPhotos(userReference);
    }


    @GetMapping(value = "userAvatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get User avatar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = UserDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public PhotoDTO getUserAvatar(@RequestParam(value = "reference") String reference,
                                  @RequestParam(value = "UserReference") String userReference) throws ContactApiException {
        return photoService.getPhotoByUser(reference, userReference);
    }

    @GetMapping(value = "userAvatarByUser",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get User avatar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = UserDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public PhotoDTO getPhotoByUserReference(@RequestParam(value = "userReference") String userReference) throws ContactApiException {
        return photoService.getPhotoByUserReference(userReference);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<UserDTO> getUsers(Pageable pageable, @RequestParam boolean fromAngular,
                                     @RequestParam(required = false) String value,
                                  @RequestParam(required = false) Sort.Direction dir) {

        if (fromAngular) {

            pageable = MiscUtils.convertFromAngularPage(pageable, dir, true);

        }

        return userService.searchUsers(value, pageable);

    }

    @GetMapping(value = "count")
    public long usersCount(){
        return userService.usersCount();
    }

}
