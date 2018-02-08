package SSH.eservices.web.controller;

import SSH.eservices.model.Path;
import SSH.eservices.repository.PathRepository;
import SSH.eservices.web.dto.PathTO;
import SSH.eservices.web.services.Itf.IPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/rest/model/paths")
@CrossOrigin("http://localhost:3000")
public class PathRestController {

    @Autowired
    PathRepository pathRepository;

    @Autowired
    IPathService pathService;

    /**
     * get all path
     *
     * @queryPath api/paths
     * @return
     */
		@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Path> getAll() throws Exception {
        return pathService.getAll();
    }

    /**
     * get a path by id
     *
     * @queryPath api/paths/{id}
     * @param id
     * @return path bean
     */
    @GetMapping(path = "/{id}", produces = "application/json")
		public Path get(@PathVariable("id") int id) throws Exception {
        return pathService.get(id);
    }

    /**
     * create new path
     *
     * @queryPath api/paths
     * @param pathTO
     * @return
     */
    @ResponseBody
    @PostMapping(path = "/", consumes = "application/json")
		public Path create(@RequestBody PathTO pathTO) throws Exception {
        return pathService.create(pathTO);
    }

    /**
     *update/edit an existant path
     *
     * @queryPath api/paths
     * @param pathTO
     * @return
     */
    @ResponseBody
    @PutMapping(path = "/", consumes = "application/json")
		public Path edit(@RequestBody PathTO pathTO) throws Exception {
       return pathService.edit(pathTO);

    }
    /**
     * delete a path
     *
     * @queryPath api/paths/{id}
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping(path = "/{id}", consumes = "application/json")
		public boolean post(@PathVariable("id") int id) throws Exception {
        return pathService.delete(id);

    }


}
