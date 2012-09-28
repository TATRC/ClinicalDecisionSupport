package gov.hhs.fha.nhinc.alertpayload;

import javax.xml.bind.annotation.XmlRegistry;

/**
 *
 * @author Steven
 */
@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of classes for package: gov.hhs.fha.nhinc.alertpayload
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Action }
     *
     */
    public Action createAction() {
        return new Action();
    }

    /**
     * Create an instance of {@link ActionParam }
     *
     */
    public ActionParam createActionParam() {
        return new ActionParam();
    }

    /**
     * Create an instance of {@link PayloadMeta }
     *
     */
    public PayloadMeta createPayloadMeta() {
        return new PayloadMeta();
    }

    /**
     * Create an instance of {@link Recommendation }
     *
     */
    public Recommendation createRecommendation() {
        return new Recommendation();
    }
}
