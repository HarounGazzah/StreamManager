package cl.streamlink.contact.service;

import cl.streamlink.contact.config.ApplicationConfig;
import cl.streamlink.contact.domain.User;
import cl.streamlink.contact.exception.ContactApiError;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.exception.FieldErrorDTO;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.UserRepository;
import cl.streamlink.contact.security.JwtTokenProvider;
import cl.streamlink.contact.security.SecurityUtils;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.Role;
import cl.streamlink.contact.web.dto.UserDTO;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Inject
    private ApiMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public JSONObject signin(String username, String password) {
        try {
            ApplicationConfig.getService(AuthenticationManager.class)
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userRepository.findOneByEmail(username).get();
            String token = jwtTokenProvider.createToken(username, user.getRoles());

            JSONObject result = new JSONObject();
            result.put("access_token", token);
            result.put("roles", user.getRoles());
            return result;

        } catch (AuthenticationException e) {
            throw new ContactApiException("Invalid email/password supplied", ContactApiError.UNAUTHORIZED, null);
        }
    }

    public UserDTO signup(UserDTO userDTO) {

        checkIfEmailIsUsed(userDTO.getEmail(), null);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setReference("usr" + MiscUtils.generateReference());
        if (MiscUtils.isEmpty(userDTO.getRoles()))
            userDTO.setRoles(Collections.singletonList(Role.ROLE_CLIENT));
        return mapper.fromBeanToDTO(userRepository.save(mapper.fromDTOToBean(userDTO)));

    }

    public UserDTO updateUser(UserDTO userDTO, String userReference) throws ContactApiException {

        checkIfEmailIsUsed(userDTO.getEmail(), userReference);

        User user = userRepository.findOneByReference(userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference));

        mapper.updateBeanFromDto(userDTO, user);

        return mapper.fromBeanToDTO(userRepository.save(user));
    }

    public UserDTO getUser(String userReference) {

        return mapper.fromBeanToDTO(userRepository.findOneByReference(userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference)));

    }


    public JSONObject deleteUser(String userReference) throws ContactApiException {

        User user = userRepository.findOneByReference(userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference));

        userRepository.delete(user);

        return MiscUtils.createSuccessfullyResult();
    }

    public UserDTO search(String email) {
        User user = userRepository.findOneByEmail(email).orElse(null);
        if (user == null) {
            throw ContactApiException.resourceNotFoundExceptionBuilder("User", email);
        }
        return mapper.fromBeanToDTO(user);
    }

    public UserDTO whoami(HttpServletRequest req) {
        return mapper.fromBeanToDTO(userRepository.findOneByEmail
                (jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req))).orElse(null));
    }

    public JSONObject changeCurrentUserPassword(String oldPassword, String newPassword) {

        User user = getCurrentUser();
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw ContactApiException.validationErrorBuilder(new FieldErrorDTO("User", "Password", "must_match"));
        }

        return MiscUtils.createSuccessfullyResult();
    }

    public User getCurrentUser() {
        String currentUserName = SecurityUtils.getCurrentUserLogin();

        if (!SecurityUtils.checkIfThereIsUserLogged())
            throw new ContactApiException("Access denied", ContactApiError.UNAUTHORIZED, null);

        return userRepository.findOneByEmail(currentUserName).
                orElseThrow(() -> ContactApiException.
                        resourceNotFoundExceptionBuilder("User", currentUserName));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }

    private void checkIfEmailIsUsed(String email, String reference) {

        if (MiscUtils.isNotEmpty(email)) {
            Optional<User> find = userRepository.findOneByEmail(email).
                    filter(user -> MiscUtils.isEmpty(reference) || !user.getReference().equals(reference));
            if (find.isPresent()) {
                throw ContactApiException.unauthorizedExceptionBuilder("email_exist", null);
            }
        }

    }

    public Page<UserDTO> searchUsers(String value, Pageable pageable) {

        if (MiscUtils.isEmpty(value))
            value = "";

        return userRepository.findByFirstnameContainingOrLastnameContainingOrEmailContaining
                (value, value, value, pageable).map(user -> mapper.fromBeanToDTO(user));
    }

    public long usersCount() {
        return userRepository.count();
    }
}
