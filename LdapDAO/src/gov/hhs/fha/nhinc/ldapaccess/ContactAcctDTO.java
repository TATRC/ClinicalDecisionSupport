/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.ldapaccess;

/**
 *
 * @author cmatser
 */
public class ContactAcctDTO {
    private String commonName;
    private String description;
    private String uid;
    private String clearPassword;

    public static final String CN_MAIL = "Mail.Account";
    public static final String CN_CALENDAR = "Cal.Account";

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getClearPassword() {
        return clearPassword;
    }

    public void setClearPassword(String clearPassword) {
        this.clearPassword = clearPassword;
    }
}
