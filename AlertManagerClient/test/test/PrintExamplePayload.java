/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import com.thoughtworks.xstream.XStream;
import gov.hhs.fha.nhinc.alertpayload.Action;
import gov.hhs.fha.nhinc.alertpayload.ActionParam;
import gov.hhs.fha.nhinc.alertpayload.PayloadMeta;
import gov.hhs.fha.nhinc.alertpayload.Recommendation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cmatser
 */
public class PrintExamplePayload {

    private String payloadXML =
  "<Recommendation>\n"
+ "  <body>&lt;html&gt;This is a &lt;b&gt;sample&lt;/b&gt; body for a recommendation.&lt;/html&gt;</body>\n"
+ "  <metadata>\n"
+ "    <type>Mail,Notification,etc</type>\n"
+ "  </metadata>\n"
+ "  <actions>\n"
+ "    <Action>\n"
+ "      <actionId>id1</actionId>\n"
+ "      <name>action name 1</name>\n"
+ "      <parameters>\n"
+ "        <ActionParam>\n"
+ "          <paramId>pid2</paramId>\n"
+ "          <name>param2</name>\n"
+ "        </ActionParam>\n"
+ "        <ActionParam>\n"
+ "          <paramId>pid1</paramId>\n"
+ "          <name>param1</name>\n"
+ "        </ActionParam>\n"
+ "      </parameters>\n"
+ "    </Action>\n"
+ "    <Action>\n"
+ "      <actionId>id2</actionId>\n"
+ "      <name>action name 2</name>\n"
+ "      <parameters>\n"
+ "        <ActionParam>\n"
+ "          <paramId>pid2</paramId>\n"
+ "          <name>param2</name>\n"
+ "        </ActionParam>\n"
+ "        <ActionParam>\n"
+ "          <paramId>pid1</paramId>\n"
+ "          <name>param1</name>\n"
+ "        </ActionParam>\n"
+ "      </parameters>\n"
+ "    </Action>\n"
+ "  </actions>\n"
+ "</Recommendation>\n"
;

    public PrintExamplePayload() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
        try {
            //Setup XStream
            XStream xstream = new XStream();
            xstream.alias("Recommendation", Recommendation.class);
            xstream.alias("Action", Action.class);
            xstream.alias("ActionParam", ActionParam.class);

            Recommendation r = new Recommendation();
            r.setBody("<html>This is a <b>sample</b> body for a recommendation.</html>");
            PayloadMeta m = new PayloadMeta();
            m.setType("Mail,Notification,etc");
            r.setMetadata(m);
            Action a = new Action();
            a.setActionId("id1");
            a.setName("action name 1");
            ActionParam p = new ActionParam();
            p.setParamId("pid1");
            p.setName("param1");
            a.getParameters().add(p);
            p = new ActionParam();
            p.setParamId("pid2");
            p.setName("param2");
            a.getParameters().add(p);
            r.getActions().add(a);

            a = new Action();
            a.setActionId("id2");
            a.setName("action name 2");
            p = new ActionParam();
            p.setParamId("pid1");
            p.setName("param1");
            a.getParameters().add(p);
            p = new ActionParam();
            p.setParamId("pid2");
            p.setName("param2");
            a.getParameters().add(p);
            r.getActions().add(a);

            System.out.println(xstream.toXML(r));
        }
        catch (Throwable t) {
            t.printStackTrace();
            fail();
        }

    }

    @Test
    public void parse() {
        try {
            //Setup XStream
            XStream xstream = new XStream();
            xstream.alias("Recommendation", Recommendation.class);
            xstream.alias("Action", Action.class);
            xstream.alias("ActionParam", ActionParam.class);

            Recommendation r = (Recommendation) xstream.fromXML(payloadXML);
            System.out.println("Action1, Param1, Value: " + ((ActionParam) ((Action) r.getActions().toArray()[0]).getParameters().toArray()[0]).getValue());
        }
        catch (Throwable t) {
            t.printStackTrace();
            fail();
        }

    }

}