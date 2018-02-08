package SSH.eservices.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rest/model/")
@CrossOrigin("http://localhost:3000")
public class IndexRestController {

   @GetMapping(path = "/index.html")
    public String index() {
        return "index";
    }
}
