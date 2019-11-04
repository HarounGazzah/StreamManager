package cl.streamlink.contact.exception;

import org.springframework.http.HttpStatus;

public enum ContactApiError {


    VALIDATION_ERROR("error.ValidationError", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND("error.resourceNotfound", HttpStatus.NOT_FOUND),
    UNPROCESSABLE_ENTITY("error.UnprocessableEntity", HttpStatus.UNPROCESSABLE_ENTITY),
    FORBIDDEN("error.FORBIDDEN", HttpStatus.FORBIDDEN),
    UNAUTHORIZED("error.UNAUTHORIZED", HttpStatus.UNAUTHORIZED),
    ERR_API("error.InternalAPIError", HttpStatus.INTERNAL_SERVER_ERROR);


    private final String message;

    private final HttpStatus httpStatus;


    ContactApiError(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


}
