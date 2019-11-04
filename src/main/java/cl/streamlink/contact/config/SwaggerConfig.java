package cl.streamlink.contact.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${contact.security.token}")
    private String token;

    @Bean
    public Docket api() {

        List<Parameter> parameters = new LinkedList<>();
        parameters.add(new ParameterBuilder()
                .parameterType("header")
                .name("Authorization")
                .modelRef(new ModelRef("string"))
                .description("Authentication token (see /ws/user/authenticate)")
                .allowMultiple(false)
                .required(false)
                .defaultValue("Bearer " + token)
                .build());

        parameters.add(new ParameterBuilder()
                .parameterType("header")
                .name("Accept-Language")
                .modelRef(new ModelRef("string"))
                .description("Accept-Language")
                .allowMultiple(false)
                .required(false)
                .defaultValue("fr")
                .build());

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("cl.streamlink.contact.web")).paths(PathSelectors.any())
                .build().globalOperationParameters(parameters)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Developer Rest API", "Developer Rest API", "API TOS",
                "Terms of service", new Contact("developer", "http://docs.spring.io", "developer@web.com"), "License of API",
                "http://docs.spring.io");
    }
}
