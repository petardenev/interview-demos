package qq.quickbase.template.test.integration;

import qq.quickbase.template.Application;
import qq.quickbase.template.server.api.model.Entity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static qq.quickbase.template.test.util.TestUtils.toLocalUrl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
class EntityControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getEntity() {
        var response = restTemplate
                .getForEntity(toLocalUrl(port, "/entities/efd8fee0-3ae5-4376-9f08-24274134a34b"), Entity.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(new Entity().id("efd8fee0-3ae5-4376-9f08-24274134a34b").name("test-entity"), response.getBody());
    }

    @Test
    public void getCreateEntity() {
        var uuid = UUID.randomUUID().toString();
        var newEntity = new Entity().name("Name - " + uuid);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Entity> request = new HttpEntity<>(newEntity, headers);

        var response = restTemplate.postForEntity(toLocalUrl(port, "/entities"), request, Entity.class);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("Name - " + uuid, response.getBody().getName());
    }
}