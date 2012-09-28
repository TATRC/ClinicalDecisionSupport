/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaydatasurvey;

import gov.hhs.fhs.nhic.displaydatasurvey.model.PossibleAnswer;
import gov.hhs.fhs.nhic.displaydatasurvey.model.SurveyFact;
import gov.hhs.fhs.nhic.displaydatasurvey.model.SurveyQuestion;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author nhin
 */
@WebService()
public class DisplayDataSurvey {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getSurvey")
    public SurveyFact getSurvey(@WebParam(name = "userId")
    String userId, @WebParam(name = "surveyId")
    String surveyId) {
        SurveyFact fact = new SurveyFact();
        fact.setUserId(userId);
        fact.setSurveyId(surveyId);
        fact.setToken("XXXXXXXXXXXXXXXXXXX");
        fact.setName("Name or title of the Survey (TestSurvey)");
        fact.setInteractive(Boolean.FALSE);
        fact.setLanguage("EN");
        fact.setContextDesc("Inbox");
        
        List<SurveyQuestion> quesList = new ArrayList<SurveyQuestion>();
        SurveyQuestion ques = new SurveyQuestion();
        ques.setQuestionId("123UNIQUEQUESTIONID");
        ques.setPreLabel("Please choose one:");
        ques.setRequired(Boolean.FALSE);
        ques.setAnswerType("number");
        ques.setSuggesteControl("combobox");
        ques.setSingleAnswer(Boolean.FALSE);

        List<PossibleAnswer> ansList = new ArrayList<PossibleAnswer>();
        PossibleAnswer pAnswer = new PossibleAnswer();
        pAnswer.setKey("6");
        pAnswer.setValue("Choice6");
        ansList.add(pAnswer);
        pAnswer.setKey("5");
        pAnswer.setValue("Choice5");
        ansList.add(pAnswer);
        pAnswer.setKey("4");
        pAnswer.setValue("Choice4");
        ansList.add(pAnswer);
        ques.setPossibleAnswers(ansList);
        quesList.add(ques);
        fact.setSurveyQuestions(quesList);
        return fact;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "setSurvey")
    public SurveyFact setSurvey(@WebParam(name = "userid")
    String userid, @WebParam(name = "surveyId")
    String surveyId, @WebParam(name = "questionId")
    String questionId) {
        SurveyFact fact = new SurveyFact();
        fact.setSuccessStatus(true);
        fact.setStatusMessage("There was a problem with this answer");
        return fact;
    }

}
