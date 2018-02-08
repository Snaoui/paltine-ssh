package SSH.eservices.web.services.Itf;

import SSH.eservices.model.Survey;
import SSH.eservices.web.dto.SurveyTO;

import java.util.List;

/**
 * SurveyService
 */
public interface SurveyService {
    /**
     * get a survey associate to id
     *
     * @param id
     * @return
     * @throws Exception if survey not found
     */
		Survey get(int id) throws Exception;

    /**
     * get list of all survey
     *
     * @return
     * @throws Exception
     */
		List<Survey> getAll() throws Exception;

    /**
     * create and save a new survey
     *
     * @param surveyTO
     * @return
     * @throws Exception
     */
		Survey create(SurveyTO surveyTO) throws Exception;

    /**
     * edit a survey
     *
     * @param surveyTO
     * @return
     * @throws Exception
     */
		Survey edit(SurveyTO surveyTO) throws Exception;

    /**
     * delete a survey
     *
     * @param id
     * @return
     * @throws Exception if survey not found
     */
		boolean delete(int id) throws Exception;
}
