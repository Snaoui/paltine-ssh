package SSH.eservices.web.controller;

import SSH.eservices.model.Survey;
import SSH.eservices.web.dto.SurveyTO;
import SSH.eservices.web.services.Itf.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/rest/model/surveys")
@CrossOrigin("http://localhost:3000")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    /**
     * get all surveys
     *
     * @return
     * @queryPath api/surveys
     */
    @GetMapping(path = "/", produces = "application/json")
		public List<Survey> getAll() throws Exception {
        return surveyService.getAll();
    }

    /**
     * get a survey by id
     *
     * @param id
     * @return survey bean
     * @queryPath api/surveys/{id}
     */
    @GetMapping(path = "/{id}", produces = "application/json")
		public Survey get(@PathVariable("id") int id) throws Exception {
        return surveyService.get( id );
    }

    /**
     * create new survey
     *
     * @return surveyTO
     * @queryPath api/surveys
     */
    @ResponseBody
    @PostMapping(path = "/", consumes = "application/json")
		public Survey create(@RequestBody SurveyTO surveyTO) throws Exception {
        return surveyService.create( surveyTO );
    }

    /**
     * delete a survey
     *
     * @param id
     * @return
     * @queryPath api/surveys/{id}
     * @queryMethod Delete
     */
    @ResponseBody
    @DeleteMapping(path = "/{id}", consumes = "application/json")
		public boolean post(@PathVariable("id") int id) throws Exception {
        return surveyService.delete( id );

    }

    /**
     * update/edit an existant survey
     *
     * @param surveyTO
     * @return
     * @queryPath api/surveys
     */
    @ResponseBody
    @PutMapping(path = "/", consumes = "application/json")
    public Survey edit(@RequestBody SurveyTO surveyTO) throws Exception {
        return surveyService.edit( surveyTO );

    }


}
