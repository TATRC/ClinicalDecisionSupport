/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fhs.nhic.displaydatasurvey.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author jharby
 */
public class SurveyFact {
    private String userId;
    private String surveyId;
    private String token;
    private String name;
    private String body;
    private Date expiresDate;
    private Boolean interactive;
    private String language;
    private String contextDesc;
    private String surveyType;
    private Boolean successStatus;
    private String statusMessage;
    private List<SurveyQuestion> surveyQuestions;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the surveyId
     */
    public String getSurveyId() {
        return surveyId;
    }

    /**
     * @param surveyId the surveyId to set
     */
    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the expiresDate
     */
    public Date getExpiresDate() {
        return expiresDate;
    }

    /**
     * @param expiresDate the expiresDate to set
     */
    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

    /**
     * @return the interactive
     */
    public Boolean getInteractive() {
        return interactive;
    }

    /**
     * @param interactive the interactive to set
     */
    public void setInteractive(Boolean interactive) {
        this.interactive = interactive;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the contextDesc
     */
    public String getContextDesc() {
        return contextDesc;
    }

    /**
     * @param contextDesc the contextDesc to set
     */
    public void setContextDesc(String contextDesc) {
        this.contextDesc = contextDesc;
    }

    /**
     * @return the surveyType
     */
    public String getSurveyType() {
        return surveyType;
    }

    /**
     * @param surveyType the surveyType to set
     */
    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    /**
     * @return the successStatus
     */
    public Boolean getSuccessStatus() {
        return successStatus;
    }

    /**
     * @param successStatus the successStatus to set
     */
    public void setSuccessStatus(Boolean successStatus) {
        this.successStatus = successStatus;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * @return the surveyQuestions
     */
    public List<SurveyQuestion> getSurveyQuestions() {
        return surveyQuestions;
    }

    /**
     * @param surveyQuestions the surveyQuestions to set
     */
    public void setSurveyQuestions(List<SurveyQuestion> surveyQuestions) {
        this.surveyQuestions = surveyQuestions;
    }


}
