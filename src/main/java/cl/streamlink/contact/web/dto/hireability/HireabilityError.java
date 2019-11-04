package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HireabilityError {

    @JacksonXmlProperty(isAttribute = true)
    @JsonAlias(value = "Id")
    private String id;

    @JsonAlias(value = "ErrorCode")
    private Integer errorCode;

    @JsonAlias(value = "ErrorMessage")
    private String errorMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
