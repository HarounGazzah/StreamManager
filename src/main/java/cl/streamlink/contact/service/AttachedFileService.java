package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.AttachedFile;
import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.domain.TimeList;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.exception.FieldErrorDTO;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.AttachedFileRepository;
import cl.streamlink.contact.repository.ResourceRepository;
import cl.streamlink.contact.repository.TimeListRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.hireability.AttachedFileDTO;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

@Service
public class AttachedFileService {


    private final Logger logger = LoggerFactory.getLogger(AttachedFile.class);
    @Value("${contact.attachedFile.url}")
    String fileUrl;
    @Value("${contact.attachedFile.path}")
    String filePath;
    @Inject
    private ApiMapper mapper;
    @Inject
    private AttachedFileRepository attachedFileRepository;
     @Inject
    private ResourceRepository resourceRepository;

    @Inject
    private TimeListRepository timeListRepository;





    private File saveAttachedFile(String fileName, MultipartFile cv) throws IOException {

        cv.transferTo(new File(filePath, fileName));
        return new File(filePath, fileName);

    }



    public AttachedFileDTO addTimeListFile(MultipartFile file, String resourceReference, String timeListReference) throws IOException {

        if (file != null) {
            Resource resource = resourceRepository.findOneByReference(resourceReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Resource", resourceReference));
           TimeList timeList = timeListRepository.findOneByReference(timeListReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("TimeList",timeListReference));
            if (attachedFileRepository.countByResourceReference(resourceReference) >= 5)
                throw ContactApiException.unprocessableEntityExceptionBuilder("cv-number", null);

            AttachedFile attachedFile = new AttachedFile();
            attachedFile.setReference(MiscUtils.generateReference());
            attachedFile.setLabel(file.getOriginalFilename());
            attachedFile.setTimeList(timeList);
            attachedFile.setName(attachedFile.getReference() + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
            attachedFile. setResource(resource);
            saveAttachedFile(attachedFile.getName(), file);

            attachedFile = attachedFileRepository.save(attachedFile);

            return mapper.fromBeanToDTO(attachedFile);
        } else
            throw ContactApiException.validationErrorBuilder(new FieldErrorDTO("Cv", "CV", "must_be_not_null"));
    }

}
