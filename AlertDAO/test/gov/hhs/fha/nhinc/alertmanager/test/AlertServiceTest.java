/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.test;

import gov.hhs.fha.nhinc.alertmanager.model.AlertAction;
import gov.hhs.fha.nhinc.alertmanager.model.AlertServiceRef;
import gov.hhs.fha.nhinc.alertmanager.model.AlertSpec;
import gov.hhs.fha.nhinc.alertmanager.model.AlertTicket;
import gov.hhs.fha.nhinc.alertmanager.model.AlertType;
import gov.hhs.fha.nhinc.alertmanager.model.TicketQueryParams;
import gov.hhs.fha.nhinc.alertmanager.service.ActionConstants;
import gov.hhs.fha.nhinc.alertmanager.service.AlertService;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class AlertServiceTest {

    public AlertServiceTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testAlertService() {
        System.out.println("Start testAlertService");

        try
        {
            AlertService alertService = new AlertService();
            AlertType alert = null;

            // Delete all
            List<AlertTicket> tickets;
            tickets = alertService.getAllTickets();
            for (AlertTicket tick : tickets) {
                alertService.deleteTicket(tick);
            }
            tickets = alertService.getAllTickets();
            assertEquals("Tickets not deleted", 0, tickets.size());

            // Insert alert
            alert = new AlertType();
            Long alertId = null;
            String alertName = "Alert Name";
            String alertDesc = "Alert Description";

            alert.setAlertTypeId(alertId);
            alert.setName(alertName);
            alert.setDescription(alertDesc);

            Set<AlertSpec> specs = new HashSet<AlertSpec>();
            AlertSpec spec1 = new AlertSpec();
            String specName1 = "specName1";
            String specValue1 = "specValue1";
            spec1.setAlertType(alert);
            spec1.setName(specName1);
            spec1.setValue(specValue1);
            specs.add(spec1);
            AlertSpec spec2 = new AlertSpec();
            String specName2 = "specName2";
            String specValue2 = "specValue2";
            spec2.setAlertType(alert);
            spec2.setName(specName2);
            spec2.setValue(specValue2);
            specs.add(spec2);
            alert.setSpecifications(specs);

            alertService.saveAlert(alert);
            alertId = alert.getAlertTypeId();

            // Retrieve insert
            AlertType retrieved = alertService.getAlert(alertId);
            assertNotNull("Retrieved insert was null", retrieved);
            assertNotNull("After insert - id null", alertId);
            assertEquals("After insert - name", alert.getName(), alertName);
            assertEquals("After insert - description", alert.getDescription(), alertDesc);

            Set<AlertSpec> retrievedSpecs = retrieved.getSpecifications();
            assertNotNull("Retrieved insert specifications was null", retrievedSpecs);
            assertEquals("After insert - specifications size", retrievedSpecs.size(), 2);


            // Update
            alertName = alertName + "updated";
            alertDesc = alertDesc + "updated";
            retrieved.setName(alertName);
            retrieved.setDescription(alertDesc);

            alertService.saveAlert(retrieved);

            // Retrieve updated
            retrieved = alertService.getAlert(alertId);
            assertNotNull("Retrieved update was null", retrieved);
            assertNotNull("After update - id null", alertId);
            assertEquals("After update - name", retrieved.getName(), alertName);
            assertEquals("After update - description", retrieved.getDescription(), alertDesc);

            retrievedSpecs = retrieved.getSpecifications();
            assertNotNull("Retrieved update specifications was null", retrievedSpecs);
            assertEquals("After update - specifications size", retrievedSpecs.size(), 2);

            // Delete
            alertService.deleteAlert(retrieved);
            retrieved = alertService.getAlert(alertId);
            assertNull("Alert not null after delete", retrieved);

            AlertTicket ticket = null;

            // Insert ticket
            ticket = new AlertTicket();
            ticket.setTicketUniqueId("ticket1");
            ticket.setProviderId("provider1");
            ticket.setPatientUnitNumber("patient1");
            ticket.setAlertTimestamp(new Date());
            ticket.setEscalationPeriod(-1);
            ticket.setAlertId(1L);
            alertService.saveTicket(ticket);

            ticket = new AlertTicket();
            ticket.setTicketUniqueId("ticket2");
            ticket.setProviderId("provider2");
            ticket.setPatientUnitNumber("patient2");
            ticket.setAlertTimestamp(new Date());
            ticket.setEscalationPeriod(-1);
            ticket.setAlertId(1L);
            AlertAction action = new AlertAction();
            action.setActionName(ActionConstants.ACTION_ACKNOWLEDGE);
            action.setActionTimestamp(new Date());
            action.setTicket(ticket);
            action.setProviderId("provider1");
            ticket.getActionHistory().add(action);
            alertService.saveTicket(ticket);

            //Query find acknowledged tickets
            TicketQueryParams query = new TicketQueryParams();
            query.setAction(ActionConstants.ACTION_ACKNOWLEDGE);
            tickets = alertService.ticketQuery(query);
            assertEquals("Tickets query found wrong number of tickets.", 1, tickets.size());
            assertEquals("Ticket query returned wrong ticket.", "ticket2", tickets.get(0).getTicketUniqueId());

            //Query find provider updated tickets
            query = new TicketQueryParams();
            query.setUpdatedProviderId("provider1");
            tickets = alertService.ticketQuery(query);
            assertEquals("Tickets query found wrong number of tickets.", 1, tickets.size());
            assertEquals("Ticket query returned wrong ticket.", "ticket2", tickets.get(0).getTicketUniqueId());

            //Query find provider tickets concerning a patient
            query = new TicketQueryParams();
            query.setProviderId("provider2");
            query.setPatientId("patient2");
            query.setAction(ActionConstants.ACTION_ACKNOWLEDGE);
            tickets = alertService.ticketQuery(query);
            assertEquals("Tickets query found wrong number of tickets.", 1, tickets.size());
            assertEquals("Ticket query returned wrong ticket.", "ticket2", tickets.get(0).getTicketUniqueId());

            //Query find provider updated tickets concerning a patient
            query = new TicketQueryParams();
            query.setUpdatedProviderId("provider1");
            query.setPatientId("patient2");
            tickets = alertService.ticketQuery(query);
            assertEquals("Tickets query found wrong number of tickets.", 1, tickets.size());
            assertEquals("Ticket query returned wrong ticket.", "ticket2", tickets.get(0).getTicketUniqueId());

            // Delete all
            tickets = alertService.getAllTickets();
            for (AlertTicket tick : tickets) {
                alertService.deleteTicket(tick);
            }
            tickets = alertService.getAllTickets();
            assertEquals("Tickets not deleted", 0, tickets.size());

        }
        catch (Throwable t)
        {
            t.printStackTrace();
            fail("testAlertService: " + t.getMessage());
        }
        System.out.println("End testAlertService");
    }

    @Test
    public void testAlertServiceRefs() {
        System.out.println("Start testAlertServiceRefs");

        try
        {
            AlertService alertService = new AlertService();
            AlertServiceRef svc = null;

            // Delete all
            List<AlertServiceRef> svcs;
            svcs = alertService.getAllServiceRefs();
            for (AlertServiceRef s : svcs) {
                alertService.deleteServiceRef(s);
            }
            svcs = alertService.getAllServiceRefs();
            assertEquals("Service Refs not deleted", 0, svcs.size());

            // Insert alert service ref
            svc = new AlertServiceRef();
            Long alertServiceRefId = null;
            String svcName = "Service Name";
            String svcDesc = "Service Description";
            String svcType = "Service Type";
            String svcLoc = "Service Location";
            String svcP1 = "Service Parameter 1";

            svc.setAlertServiceRefId(alertServiceRefId);
            svc.setName(svcName);
            svc.setDescription(svcDesc);
            svc.setLocation(svcLoc);
            svc.setType(svcType);
            svc.setServiceParam1(svcP1);

            alertService.saveServiceRef(svc);
            alertServiceRefId = svc.getAlertServiceRefId();

            // Retrieve insert
            AlertServiceRef retrieved = alertService.getServiceRef(alertServiceRefId);
            assertNotNull("Retrieved insert was null", retrieved);
            assertNotNull("After insert - id null", alertServiceRefId);
            assertEquals("After insert - name", retrieved.getName(), svcName);
            assertEquals("After insert - description", retrieved.getDescription(), svcDesc);
            assertEquals("After insert - type", retrieved.getType(), svcType);
            assertEquals("After insert - location", retrieved.getLocation(), svcLoc);
            assertEquals("After insert - param1", retrieved.getServiceParam1(), svcP1);

            // Update
            svcName = svcName + "updated";
            svcDesc = svcDesc + "updated";
            svcType = svcType + "updated";
            svcLoc = svcLoc + "updated";
            svcP1 = svcP1 + "updated";
            retrieved.setName(svcName);
            retrieved.setDescription(svcDesc);
            retrieved.setType(svcType);
            retrieved.setLocation(svcLoc);
            retrieved.setServiceParam1(svcP1);

            alertService.saveServiceRef(retrieved);

            // Retrieve updated
            retrieved = alertService.getServiceRef(alertServiceRefId);
            assertNotNull("Retrieved update was null", retrieved);
            assertNotNull("After update - id null", alertServiceRefId);
            assertEquals("After update - name", retrieved.getName(), svcName);
            assertEquals("After update - description", retrieved.getDescription(), svcDesc);
            assertEquals("After insert - type", retrieved.getType(), svcType);
            assertEquals("After insert - location", retrieved.getLocation(), svcLoc);
            assertEquals("After insert - param1", retrieved.getServiceParam1(), svcP1);

            // Delete
            alertService.deleteServiceRef(retrieved);
            retrieved = alertService.getServiceRef(alertServiceRefId);
            assertNull("Record not null after delete", retrieved);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            fail("testAlertServiceRefs: " + t.getMessage());
        }
        System.out.println("End testAlertServiceRefs");
    }

}