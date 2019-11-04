package cl.streamlink.contact.utils;

import cl.streamlink.contact.config.ApplicationConfig;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.mapper.ApiMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class DeveloperResponseSerializer extends JsonSerializer<Developer> {


    @Override
    public void serialize(Developer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeObject(ApplicationConfig.getService(ApiMapper.class).fromBeanToDTOResponse(value));
    }
}
