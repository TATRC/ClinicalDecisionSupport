/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fhs.nhic.displaydatasurvey.model;

import java.util.List;

/**
 *
 * @author jharby
 */
public class SurveyQuestion {
    private String questionId;
    private String preLabel;
    private Boolean required;
    private String answerType;
    private String suggesteControl;
    private Boolean singleAnswer;
    private String action;
    private Integer position;
    private String successType;
    private String statusMessage;
    private List<PossibleAnswer> possibleAnswers;

    /**
     * @return the questionId
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId the questionId to set
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * @return the preLabel
     */
    public String getPreLabel() {
        return preLabel;
    }

    /**
     * @param preLabel the preLabel to set
     */
    public void setPreLabel(String preLabel) {
        this.preLabel = preLabel;
    }

    /**
     * @return the required
     */
    public Boolean getRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(Boolean required) {
        this.required = required;
    }

    /**
     * @return the answerType
     */
    public String getAnswerType() {
        return answerType;
    }

    /**
     * @param answerType the answerType to set
     */
    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    /**
     * @return the suggesteControl
     */
    public String getSuggesteControl() {
        return suggesteControl;
    }

    /**
     * @param suggesteControl the suggesteControl to set
     */
    public void setSuggesteControl(String suggesteControl) {
        this.suggesteControl = suggesteControl;
    }

    /**
     * @return the singleAnswer
     */
    public Boolean getSingleAnswer() {
        return singleAnswer;
    }

    /**
     * @param singleAnswer the singleAnswer to set
     */
    public void setSingleAnswer(Boolean singleAnswer) {
        this.singleAnswer = singleAnswer;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * @return the successType
     */
    public String getSuccessType() {
        return successType;
    }

    /**
     * @param successType the successType to set
     */
    public void setSuccessType(String successType) {
        this.successType = successType;
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
     * @return the possibleAnswers
     */
    public List<PossibleAnswer> getPossibleAnswers() {
        return possibleAnswers;
    }

    /**
     * @param possibleAnswers the possibleAnswers to set
     */
    public void setPossibleAnswers(List<PossibleAnswer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }


}
