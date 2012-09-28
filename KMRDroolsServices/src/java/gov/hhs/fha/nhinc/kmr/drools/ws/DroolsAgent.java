/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.drools.ws;

import gov.hhs.fha.nhinc.adapter.kmr.factobjects.LabResultFact;
import gov.hhs.fha.nhinc.adapter.kmr.ruleobjects.PlateletHistory;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import gov.hhs.fha.nhinc.adapter.kmr.ruleobjects.RuleExecutionResponse;
import gov.hhs.fha.nhinc.adapter.kmr.utils.Conversion;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.Globals;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;


/**
 *
 * @author Duane DeCouteau
 */
@WebService()
public class DroolsAgent {
    protected StatefulKnowledgeSession session;
    private String plateletdrl = "../drl/platelet.drl";

    private StatefulKnowledgeSession getRulesession() {
        if (session == null) {
            try {
                System.out.println("Rule session is NULL");
                KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
                //Resource drl = ResourceFactory.newFileResource(plateletdrl);
                Resource drl = ResourceFactory.newInputStreamResource(this.getClass().getResourceAsStream(plateletdrl));
                kbuilder.add(drl, ResourceType.DRL);

                KnowledgeBuilderErrors errors = kbuilder.getErrors();
                if (errors.size() > 0) {
                    for (KnowledgeBuilderError error : errors) {
                        System.err.println(error);
                    }
                }

                KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
                knowledgeBase.addKnowledgePackages(kbuilder.getKnowledgePackages());

                Collection<KnowledgePackage> pkg = knowledgeBase.getKnowledgePackages();
                System.out.println("KnowledgePackages Loaded "+pkg.size());

                //session stuff
                session = knowledgeBase.newStatefulKnowledgeSession();
                session.setGlobal("helper", new Conversion());
                session.setGlobal("execResponse", new RuleExecutionResponse());
                session.setGlobal("pHistory", new PlateletHistory());
                //KnowledgeRuntimeLogger ruleslogger = KnowledgeRuntimeLoggerFactory.newFileLogger(session, "/home/nhin/drools.log");

                System.out.println("Rule session init is complete");
             }
             catch (Exception ex) {
                    ex.printStackTrace();
             }
        }
        return session;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "assertLabResult")
    public Boolean assertLabResult(@WebParam(name = "lab")
    LabResultFact lab) {
        if (session == null) getRulesession();
        Boolean res = new Boolean(false);
        try {
            FactHandle handle = session.insert(lab);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "runTestRule1")
    public RuleExecutionResponse runTestRule1() {
        if (session == null) getRulesession();
        RuleExecutionResponse res = null;
        try {
            LabResultFact lab = new LabResultFact();
            lab.setLabId("1835-72");
            lab.setValue("80");
            lab.setAbnormalFlag("A");
            lab.setHistorical(false);
            FactHandle handler = session.insert(lab);

            int rules = session.fireAllRules();

            if (rules > 0) {
                res = (RuleExecutionResponse) session.getGlobal("execResponse");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "runTest2")
    public RuleExecutionResponse runTestRule2() {
        //TODO write your implementation code here:
        if (session == null) getRulesession();
        RuleExecutionResponse res = null;
        try {
            LabResultFact lab = new LabResultFact();
            lab.setLabId("1835-72");
            lab.setValue("35");
            lab.setAbnormalFlag("A");
            lab.setHistorical(false);
            FactHandle handler = session.insert(lab);

            int rules = session.fireAllRules();

            if (rules > 0) {
                RuleExecutionResponse re = (RuleExecutionResponse)session.getGlobal("execResponse");
                res = new RuleExecutionResponse();
                res.setCriticality(re.getCriticality());
                res.setMessage(re.getMessage());
                res.setObjectId(re.getObjectId());
                res.setObjectValue(re.getObjectValue());
                res.setPatientId(re.getPatientId());
                res.setRuleId(re.getRuleId());
                res.setRuleName(re.getRuleName());
                res.setTaskId(re.getTaskId());
                res.setTaskType(re.getTaskType());
            }
        }
        catch (Exception ex) {
            System.err.println("DROOLS AGENT ERROR "+ex.getMessage());
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "runTest3")
    public RuleExecutionResponse runTest3() {
        if (session == null) getRulesession();
        RuleExecutionResponse res = null;
        try {
            LabResultFact lab = new LabResultFact();
            lab.setLabId("1835-72");
            lab.setValue("10");
            lab.setAbnormalFlag("A");
            lab.setHistorical(false);
            FactHandle handler = session.insert(lab);

            int rules = session.fireAllRules();

            if (rules > 0) {
                res = (RuleExecutionResponse) session.getGlobal("execResponse");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "closeStatefulKnowledgesession")
    public Boolean closeStatefulKnowledgesession() {
        Boolean res = new Boolean(false);
        if (session == null) getRulesession();
        try {
            session.dispose();
            session = null;
            res = new Boolean(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "fireAllRules")
    public RuleExecutionResponse fireAllRules() {
        if (session == null) getRulesession();
        RuleExecutionResponse res = null;
        try {
            int rules = session.fireAllRules();

            if (rules > 0) {
                res = (RuleExecutionResponse) session.getGlobal("execResponse");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "assertAndExecute")
    public RuleExecutionResponse assertAndExecute(@WebParam(name = "labs")
    List<LabResultFact> labs) {
        System.out.println("RULE FACT COUNT IS "+labs.size());
        if (session == null) getRulesession();
        RuleExecutionResponse res = null;
        try {
            Iterator iter = labs.iterator();
            while (iter.hasNext()) {
                LabResultFact lab = (LabResultFact)iter.next();
                System.out.println("PLATELETHISTORY:Asserting Value "+lab.getValue());
                session.insert(lab);
            }
            int rules = session.fireAllRules();
            System.out.println("RULES EVENT "+rules);

            res = (RuleExecutionResponse)session.getGlobal("execResponse");

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
