package cl.streamlink.contact.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class ContactApiExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(ContactApiExceptionHandler.class);


    @ExceptionHandler(ConcurrencyFailureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO processConcurrencyFailureException(ConcurrencyFailureException ex) {
        return new ErrorDTO(null, ErrorConstants.ERR_CONCURRENCY_FAILURE, ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO processMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return new ErrorDTO(null, ErrorConstants.ERR_MISSING_REQUEST_PARAMETER, ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorDTO processAuthenticationException(AuthenticationException ex) {
        return new ErrorDTO(null, ErrorConstants.ERR_UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO processMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        logger.error(ex.getClass().getName(), ex);
        logger.error(fieldErrors.stream().map(FieldError::toString).collect(Collectors.joining("\n")));

        return processFieldErrors(fieldErrors);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorDTO processHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        ErrorDTO errorDTO = new ErrorDTO(null, ErrorConstants.ERR_VALIDATION, ex.getMessage());

        if (ex.getCause() instanceof JsonParseException) {

            JsonParseException cause = ((JsonParseException) ex.getCause());
            logger.error(cause.getMessage());
        }

        if (ex.getCause() instanceof JsonMappingException) {
            JsonMappingException cause = ((JsonMappingException) ex.getCause());
            JsonMappingException.Reference ref = cause.getPath().get(cause.getPath().size() - 1);
            errorDTO.add(ref.getFrom().getClass().getSimpleName(), ref.getFieldName(), cause.getOriginalMessage());


        }
        logger.error(errorDTO.toString());
        return errorDTO;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO processNullPointerException(NullPointerException ex) {
        ErrorDTO errorDTO = new ErrorDTO(null, ErrorConstants.ERR_INTERNAL_SERVER_ERROR, ex.getMessage());
        logger.error(ex.getClass().getName(), ex);
        logger.error(errorDTO.toString());
        return errorDTO;
    }

    private ErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ErrorDTO dto = new ErrorDTO(null, ErrorConstants.ERR_VALIDATION, null);

        for (FieldError fieldError : fieldErrors) {
            dto.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode());
        }

        logger.error(dto.toString());
        return dto;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO processHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ErrorDTO errorDTO = new ErrorDTO(null, ErrorConstants.ERR_METHOD_NOT_SUPPORTED, ex.getMessage());
        logger.error(ex.getClass().getName(), ex);
        logger.error(errorDTO.toString());
        return errorDTO;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO processMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {

        ErrorDTO errorDTO = new ErrorDTO("", ex.getLocalizedMessage(), ex.getMessage());

        logger.error(errorDTO.toString());

        return errorDTO;

    }

    @ExceptionHandler(ContactApiException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> processContactApiException(ContactApiException ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getCode(), ex.getMotif().getMessage(), ex.getMessage());

        if (ex.getFieldError() != null) {
            errorDTO.add(ex.getFieldError());
        }

        logger.error(errorDTO.toString());
        return ResponseEntity.status(ex.getMotif().getHttpStatus()).body(errorDTO);


    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> processException(Exception ex) {
        BodyBuilder builder;
        ErrorDTO errorDTO;
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            builder = ResponseEntity.status(responseStatus.value());
            errorDTO = new ErrorDTO(null, "error." + responseStatus.value().value(), responseStatus.reason());
        } else {
            builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            errorDTO = new ErrorDTO(null, ErrorConstants.ERR_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
        logger.error(ex.getClass().getName(), ex);
        logger.error(errorDTO.toString());

        return builder.body(errorDTO);
    }
}
