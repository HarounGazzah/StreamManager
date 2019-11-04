package cl.streamlink.contact;

import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.ResourceService;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.utils.FakerService;
import cl.streamlink.contact.utils.enums.Role;
import cl.streamlink.contact.web.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.ArrayList;
import java.util.Collections;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cl.streamlink.contact")
public class StreamlinkContactApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(StreamlinkContactApplication.class);

    @Autowired
    UserService userService;

    @Autowired
    FakerService fakerService;

    @Autowired
    ResourceService rs;

    public static void main(String[] args) {
        SpringApplication.run(StreamlinkContactApplication.class, args);
    }

    @Override
    public void run(String... params) {

        try {
            UserDTO admin = new UserDTO();
            admin.setLastname("admin");
            admin.setFirstname("admin");

            admin.setPassword("admin");
            admin.setEmail("admin@email.com");
            admin.setRoles(new ArrayList<>(Collections.singletonList(Role.ROLE_ADMIN)));

            userService.signup(admin);
        } catch (ContactApiException e) {
            logger.warn(e.getMessage());
        }
        try {
            UserDTO client = new UserDTO();
            client.setLastname("client");
            client.setFirstname("client");
            client.setPassword("client");
            client.setEmail("client@email.com");
            client.setRoles(new ArrayList<>(Collections.singletonList(Role.ROLE_CLIENT)));

            userService.signup(client);
        } catch (ContactApiException e) {
            logger.warn(e.getMessage());
        }
        try {
            UserDTO resource = new UserDTO();
            resource.setLastname("resource");
            resource.setFirstname("resource");

            resource.setPassword("resource");
            resource.setEmail("resource@email.com");
            resource.setRoles(new ArrayList<>(Collections.singletonList(Role.ROLE_RESOURCE)));

            userService.signup(resource);
        } catch (ContactApiException e) {
            logger.warn(e.getMessage());
        }

          // fakerService.deleteAll();
          // fakerService.generateFakerDeveloperData(20);
         // fakerService.generateFakerResourceDate(20);
         // fakerService.generateFakerSocietyData(20,10);
    }
}
