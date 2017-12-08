package destination.service;

import destination.database.IDestinationDatabase;
import destination.model.Destination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Lukas on 12-10-2017.
 */

@Service
public class DestinationService
{
    private static final Logger logger = LoggerFactory.getLogger(DestinationService.class);
    private IDestinationDatabase database;

    @Autowired
    public DestinationService(IDestinationDatabase database)
    {
        this.database = database;
    }

    public void addDestination(Destination destination)
    {
        database.addDestination(destination);
    }

    public void addDestinations(List<Destination> destinations)
    {
        database.addDestinations(destinations);
    }

    public List<Destination> getAllDestinations()
    {
        return database.getAllDestinations();
    }

    public Destination getDestinationByCode(Integer code)
    {
        return database.getByCode(code);
    }

    public void removeDestination(Integer code) { database.remove(code); }

    public Iterator<Destination> getDestinationIterator()
    {
        return database.getIterator();
    }
}
