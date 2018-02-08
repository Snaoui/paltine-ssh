package SSH.eservices.web.controller;

import SSH.eservices.web.dto.ResponseTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void get_undefined_user_Test() {
        ResponseTO response;
        response = this.restTemplate.getForObject( "/api/rest/model/users/test@test.com/", ResponseTO.class );
        assertThat( response.getMessage() ).contains( "User with email [test@test.com] doesn't exists" );
        assertThat( response.getStatus() ).isEqualTo( "500" );
        assertThat( response.getError() ).contains( "Internal Server Error" );

    }


}
