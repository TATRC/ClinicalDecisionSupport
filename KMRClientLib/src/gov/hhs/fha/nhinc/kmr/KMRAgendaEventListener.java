package gov.hhs.fha.nhinc.kmr;

import org.drools.event.rule.ActivationCancelledEvent;
import org.drools.event.rule.ActivationCreatedEvent;
import org.drools.event.rule.AfterActivationFiredEvent;
import org.drools.event.rule.AgendaEventListener;
import org.drools.event.rule.AgendaGroupPoppedEvent;
import org.drools.event.rule.AgendaGroupPushedEvent;
import org.drools.event.rule.BeforeActivationFiredEvent;

public class KMRAgendaEventListener implements AgendaEventListener
{
    @Override
    public void activationCancelled(ActivationCancelledEvent arg0)
    {
        System.out.println( "ActivationCancelledEvent: " + arg0 );
    }

    @Override
    public void activationCreated(ActivationCreatedEvent arg0)
    {
        System.out.println( "ActivationCreatedEvent: " + arg0 );
    }

    @Override
    public void afterActivationFired(AfterActivationFiredEvent arg0)
    {
        System.out.println( "AfterActivationFiredEvent: " + arg0 );
    }

    @Override
    public void agendaGroupPopped(AgendaGroupPoppedEvent arg0)
    {
        System.out.println( "AgendaGroupPoppedEvent: " + arg0 );
    }

    @Override
    public void agendaGroupPushed(AgendaGroupPushedEvent arg0)
    {
        System.out.println( "AgendaGroupPushedEvent: " + arg0 );
    }

    @Override
    public void beforeActivationFired(BeforeActivationFiredEvent arg0)
    {
        System.out.println( "BeforeActivationFiredEvent: " + arg0 );
    }
}
