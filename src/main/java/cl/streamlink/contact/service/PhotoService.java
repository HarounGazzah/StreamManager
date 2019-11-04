package cl.streamlink.contact.service;


import cl.streamlink.contact.domain.Photo;
import cl.streamlink.contact.domain.User;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.exception.FieldErrorDTO;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.PhotoRepository;
import cl.streamlink.contact.repository.UserRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.PhotoDTO;
import net.minidev.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoService {

    private final Logger logger = LoggerFactory.getLogger(PhotoService.class);
    @Value("${contact.photo.url}")
    String basePhotoUrl;
    @Value("${contact.photo.path}")
    String basePhotoPath;
    @Inject
    private ApiMapper mapper;

    @Inject
    private UserService userService;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PhotoRepository photoRepository;


    public PhotoDTO addUserAvatar(MultipartFile avatar, String userReference) throws IOException {

        if (avatar != null) {
            User user = userRepository.findOneByReference(userReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference));

            if (photoRepository.countByUserReference(userReference) >= 5)
                throw ContactApiException.unprocessableEntityExceptionBuilder("cv-number", null);

            Photo photo = new Photo();
            photo.setReference(MiscUtils.generateReference());
            photo.setLabel(avatar.getOriginalFilename());
            photo.setName(photo.getReference() + "." +
                    FilenameUtils.getExtension(avatar.getOriginalFilename()));
            photo.setUser(user);
            saveCvFile(photo.getName(), avatar);

            photo = photoRepository.save(photo);

            return mapper.fromBeanToDTO(photo);
        } else
            throw ContactApiException.validationErrorBuilder(new FieldErrorDTO("Photo", "PHOTO", "must_be_not_null"));
    }

    private File saveCvFile(String fileName, MultipartFile avatar) throws IOException {

        avatar.transferTo(new File(basePhotoPath, fileName));
        return new File(basePhotoPath, fileName);
    }

    public List<PhotoDTO> findUserPhotos(String userReference) {

        userRepository.findOneByReference(userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference));


        return photoRepository.findByUserReference(userReference)
                .stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());


    }

//    public PhotoDTO getUserAvatar(String userReference) {
//        return mapper.fromBeanToDTO(photoRepository.findOneByReference(userReference)
//                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference)));
//
//    }

    public PhotoDTO getPhotoByUser(String reference, String userReference) {

        userRepository.findOneByReference(userReference);

        return mapper.fromBeanToDTO(photoRepository.findOneByReferenceAndUserReference(reference, userReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference)));

    }

    public PhotoDTO getPhotoByUserReference(String userReference) {
        userRepository.findOneByReference(userReference);
        return mapper.fromBeanToDTO(photoRepository.findOneByUserReference(userReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference)));

    }

    public JSONObject removeUserAvatar(String reference, String userReference) {

        userRepository.findOneByReference(userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference));

        Photo photo = photoRepository.findOneByReferenceAndUserReference
                (reference, userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Photo", reference));

        removePhoto(photo);

        return MiscUtils.createSuccessfullyResult();

    }

    private void removePhoto(Photo photo) {
        File file = new File(basePhotoPath, photo.getName());
        file.delete();
        photoRepository.delete(photo);
    }
}
