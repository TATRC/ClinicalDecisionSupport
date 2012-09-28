/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.service;

/**
 *
 * @author cmatser
 */
public interface SpecConstants {

    /** Alert specification attribute name for service reference. */
    public static final String NAME_SERVICE_REF = "serviceRef";

    /** Alert specification attribute name for provider to alert. */
    public static final String NAME_PROVIDER = "providerId";

    /** Alert specification attribute name for organization to alert. */
    public static final String NAME_ORGANIZATION = "organizationId";

    /** Alert specification attribute name for clinic to alert. */
    public static final String NAME_CLINIC = "clinicId";

    /** Alert specification attribute name for provider to alert for escalation. */
    public static final String NAME_ESCALATION_PROVIDER = "escalationProviderId";

    /** Alert specification attribute name for escalation period. */
    public static final String NAME_ESCALATION_PERIOD = "escalationPeriod";
}
