package destination.service;

import destination.database.DestinationDatabase_HashMap;
import destination.database.IDestinationDatabase;
import destination.model.Destination;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by Lukas on 15-11-2017.
 */
@Component
public class DestinationIterationService
{
    private DestinationDatabase_HashMap destinationDatabase;
    private Iterator<Destination> iterator;


    public DestinationIterationService(DestinationDatabase_HashMap destinationDatabase)
    {
        this.destinationDatabase = destinationDatabase;
        reset();
    }

    public Destination getNextDestination()
    {
        if(iterator.hasNext())
            return iterator.next();
        reset();
        return iterator.next();
    }

    private void reset()
    {
        iterator = destinationDatabase.getIterator();
    }
}
