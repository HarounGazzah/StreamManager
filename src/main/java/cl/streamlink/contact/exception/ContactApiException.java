package cl.streamlink.contact.exception;

import cl.streamlink.contact.utils.MessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactApiException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 8072531105668136904L;

    private Logger logger = LoggerFactory.getLogger(ContactApiException.class);

    private ContactApiError motif;
    private String code;
    private FieldErrorDTO fieldError;

    public ContactApiException(Throwable cause) {
        super(cause);
        this.motif = ContactApiError.ERR_API;

        logger.error("ContactApiException is Thrown");
        logger.error(this.getMessage());
    }

    public ContactApiException(String txt, ContactApiError cause, String code) {
        super(txt);
        this.motif = cause;
        this.code = code;

        logger.error("ContactApiException is Thrown");
        logger.error(this.getMessage());
    }

    public static ContactApiException unauthorizedExceptionBuilder(String key, String[] objects) {

        String message = MessageFactory.getMessage("contact.api.service.unauthorized_exception." + key, objects);
        return new ContactApiException(message, ContactApiError.UNAUTHORIZED, null);
    }

    public static ContactApiException unprocessableEntityExceptionBuilder(String key, String[] objects) {

        String message = MessageFactory.getMessage("contact.api.service.unprocessable_entity_exception." + key, objects);
        return new ContactApiException(message, ContactApiError.UNPROCESSABLE_ENTITY, null);
    }

    public static ContactApiException validationErrorBuilder(FieldErrorDTO fieldError) {

        String message = MessageFactory.getMessage("contact.api.exception.validation_error." + fieldError.getMessage(), new String[]{fieldError.getObjectName(), fieldError.getField()});
        ContactApiException ex = new ContactApiException(message, ContactApiError.VALIDATION_ERROR, null);
        ex.setFieldError(fieldError);
        return ex;
    }

    public static ContactApiException resourceNotFoundExceptionBuilder(String object, String reference) {

        return new ContactApiException(MessageFactory.getMessage("contact.api.exception.resource_not_found",
                new String[]{object, reference}), ContactApiError.RESOURCE_NOT_FOUND, null);

    }

    public FieldErrorDTO getFieldError() {
        return fieldError;
    }

    public void setFieldError(FieldErrorDTO fieldError) {
        this.fieldError = fieldError;
    }

    @Override
    public String getMessage() {
        return super.getMessage();

    }

    public ContactApiError getMotif() {
        if (motif == null) {
            motif = ContactApiError.ERR_API;
        }
        return motif;
    }

    public void setMotif(ContactApiError motif) {
        this.motif = motif;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
