package gov.hhs.fha.nhinc.addrbook.model;

/**
 * Parameter object for item queries
 * 
 * @author cmatser
 */
public class AddressQueryParams
{
    private String userId;
    private String classId;

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
     * @return the classId
     */
    public String getClassId() {
        return classId;
    }

    /**
     * @param classId the classId to set
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }
}