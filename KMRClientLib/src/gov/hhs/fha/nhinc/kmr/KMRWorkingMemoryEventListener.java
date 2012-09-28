package gov.hhs.fha.nhinc.kmr;

import org.drools.event.rule.ObjectInsertedEvent;
import org.drools.event.rule.ObjectRetractedEvent;
import org.drools.event.rule.ObjectUpdatedEvent;
import org.drools.event.rule.WorkingMemoryEventListener;

public class KMRWorkingMemoryEventListener implements WorkingMemoryEventListener
{
    @Override
    public void objectInserted(ObjectInsertedEvent arg0)
    {
        System.out.println( "ObjectInsertedEvent: " + arg0 );
    }

    @Override
    public void objectRetracted(ObjectRetractedEvent arg0)
    {
        System.out.println( "ObjectRetractedEvent: " + arg0 );
    }

    @Override
    public void objectUpdated(ObjectUpdatedEvent arg0)
    {
        System.out.println( "ObjectUpdatedEvent: " + arg0 );
    }
}
