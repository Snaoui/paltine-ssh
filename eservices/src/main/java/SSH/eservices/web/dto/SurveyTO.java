package SSH.eservices.web.dto;

import SSH.eservices.model.Survey;

public class SurveyTO {
    int pathId;
    Survey survey;

    public int getPathId() {
        return pathId;
    }

    public void setPathId(int pathId) {
        this.pathId = pathId;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
