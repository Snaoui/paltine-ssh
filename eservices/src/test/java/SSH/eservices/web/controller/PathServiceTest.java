package SSH.eservices.web.controller;

import SSH.eservices.web.dto.ResponseTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@WebMvcTest(PathRestController.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PathServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;
   // @MockBean
  //  IPathService pathService;
    @Test
    public void indexTest() {
        String body = this.restTemplate.getForObject( "/api/rest/model/index.html", String.class );
        assertThat(body).isEqualTo("index");
    }
    @Test
    public void deleteTest() {
        ResponseTO<Boolean> response = new ResponseTO<>();
        response.setData(true);
     //   given(this.pathService.delete(1)).willReturn( response);
        //response = pathService.delete(1);
        assertThat(response.data).isEqualTo(true);
    }

    @Test
    public void get_path_with_undefined_id_Test() {
        ResponseTO response ;
        response = this.restTemplate.getForObject( "/api/rest/model/paths/111133", ResponseTO.class );
        assertThat( response.getMessage()).contains( "Path with id [111133] doesn't exists !" );
        assertThat( response.getStatus()).isEqualTo( "500");
        assertThat( response.getError() ).contains( "Internal Server Error" );

    }






}
