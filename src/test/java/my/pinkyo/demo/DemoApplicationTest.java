package my.pinkyo.demo;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import my.pinkyo.demo.model.Sex;
import my.pinkyo.demo.model.User;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;


public class DemoApplicationTest {
    @ClassRule
    public static final DropwizardAppRule<DemoConfiguration> RULE =
            new DropwizardAppRule<>(DemoApplication.class, ResourceHelpers.resourceFilePath("demo.yml"));

    @Test
    public void main() throws Exception {
        Client client = new JerseyClientBuilder().register(RULE.getEnvironment()).build();

        User user = new User("test", Sex.MALE);
        Response response = client.target(
                String.format("http://localhost:%d/demo/test", RULE.getLocalPort()))
                .request()
                .post(Entity.json(user));

        assertThat(response.getStatus()).isEqualTo(201);
    }
}