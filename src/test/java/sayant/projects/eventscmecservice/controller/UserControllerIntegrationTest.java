package sayant.projects.eventscmecservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import sayant.projects.eventscmecservice.EventsCmecServiceApplication;
import sayant.projects.eventscmecservice.model.UserDTO;
import sayant.projects.eventscmecservice.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sayantjm on 20/12/20
 */

@WebFluxTest(controllers = UserController.class)
@Import(UserService.class)
@SpringBootTest(classes = EventsCmecServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    public void testGetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<UserDTO>> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(getRootUrl().concat("/user"), HttpMethod.GET, entity, ArrayList<>.class);
    }

}
