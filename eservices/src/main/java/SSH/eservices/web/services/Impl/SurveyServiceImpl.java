package SSH.eservices.web.services.Impl;

import SSH.eservices.model.Path;
import SSH.eservices.model.Survey;
import SSH.eservices.repository.SurveyRepository;
import SSH.eservices.web.dto.SurveyTO;
import SSH.eservices.web.services.Itf.IPathService;
import SSH.eservices.web.services.Itf.SurveyService;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("surveyService")
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private IPathService pathService;


    /**
     * get a survey associate to id
     *
     * @param id
     * @return
     * @throws Exception if survey not found
     */
    @Override
		public Survey get(int id) throws Exception {
			return getSurvey(id);
    }

    /**
     * get list of all survey
     *
     * @return
     * @throws Exception
     */
    @Override
		public List<Survey> getAll() {
			List<Survey> allSurveysList = new ArrayList<>();
			surveyRepository.findAll().forEach(allSurveysList::add);
			return allSurveysList;
    }

    /**
     * create and save a new survey
     *
     * @param surveyTO
     * @return
     * @throws Exception
     */
    @Override
		public Survey create(SurveyTO surveyTO) throws Exception {
        if (surveyTO == null || surveyTO.getSurvey() == null) {
            throw new IllegalArgumentException( "Invalid arguments" );
        }
			return createSurvey(surveyTO);
    }

    /**
     * edit a survey
     *
     * @param surveyTO
     * @return
     * @throws Exception
     */
    @Override
    public Survey edit(SurveyTO surveyTO) {
        return null;
    }

    /**
     * delete a survey
     *
     * @param id
     * @return
     * @throws Exception if survey not found
     */
    @Override
		public boolean delete(int id) {
        try {
            surveyRepository.delete( id );
					return true;
        } catch (HibernateException e) {
					throw new HibernateException("Nothing deleted !");
        }

    }

    private Survey createSurvey(SurveyTO surveyTO) throws Exception {
        if (!pathService.exists( surveyTO.getPathId() )) {
            throw new Exception( "Can not create survey associate to undefined path " );
        }
        Path path = pathService.getPath(surveyTO.getPathId());
        Survey survey = surveyTO.getSurvey();
        if (survey != null) {
            try {
                surveyRepository.save(survey);
            } catch (Exception e) {
                throw new Exception("Impossible to save survey [" + survey + "]");
            }
        }
        List<Survey> surveyList = path.getSurveys();
        //todo: verify survey value and add in db
        surveyList.add(surveyTO.getSurvey());
        path.setSurveys(surveyList);
        pathService.save(path);
        return surveyTO.getSurvey();

    }

    private Survey getSurvey(int id) throws Exception {
        if (!exists( id )) {
            throw new Exception( "Can not find survey " );
        }
        return surveyRepository.findOne(id);
    }



    private boolean exists(int id) throws Exception {
        if (!surveyRepository.exists( id )) {
            throw new Exception( "Survey without id [ " + id + "] doesn't exists" );
        }
        return true;
    }
}