//package cl.streamlink.contact.config;
//
//import cl.streamlink.contact.domain.Developer;
//import cl.streamlink.contact.utils.DeveloperResponseSerializer;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.databind.module.SimpleSerializers;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
//
//@Configuration
////@EnableSpringDataWebSupport
//public class RepositoryRestConfigurerAdapterImpl extends RepositoryRestConfigurerAdapter {
//
//
//
//    @Override
//    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
//        super.configureJacksonObjectMapper(objectMapper);
//
//        objectMapper.registerModule(new SimpleModule() {
//            @Override
//            public void setupModule(SetupContext context) {
//                SimpleSerializers developerSerializers = new SimpleSerializers();
//
//                developerSerializers.addSerializer(Developer.class, new DeveloperResponseSerializer());
//                context.addSerializers(developerSerializers);
//
//            }
//        });
//    }
//}
