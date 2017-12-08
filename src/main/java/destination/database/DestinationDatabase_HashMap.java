package destination.database;

import destination.config.DatabaseConfig;
import destination.model.Destination;
import destination.util.DestinationConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Lukas on 12-10-2017.
 */

@Component
@Primary
public class DestinationDatabase_HashMap implements IDestinationDatabase
{
    private Map<Integer, Destination> destinations = new HashMap<>();
    private DestinationConverter destinationConverter;

    private static final Logger logger = LoggerFactory.getLogger(DestinationDatabase_HashMap.class);

    @Autowired
    public DestinationDatabase_HashMap()
    {
        destinationConverter = new DestinationConverter();
        populate();
    }

    @Override
    public void addDestination(Destination destination)
    {
        destinations.put(destination.getCode(), destination);
    }

    @Override
    public void addDestinations(List<Destination> destinations)
    {
        for(Destination destination : destinations)
        {
            addDestination(destination);
        }
    }

    @Override
    public List<Destination> getAllDestinations()
    {
        return new ArrayList<Destination>(destinations.values());
    }

    @Override
    public Iterator<Destination> getIterator()
    {
        return destinations.values().iterator();
    }

    @Override
    public Destination getByCode(Integer code)
    {
        return destinations.get(code);
    }

    @Override
    public void remove(Integer code)
    {
        destinations.remove(code);
    }

    private void populate()
    {
        List<Destination> destinations = destinationConverter.readPlaces(DatabaseConfig.PLACE_FILE_LOCATION);
        addDestinations(destinations);
    }

    public void saveDataBase()
    {
        destinationConverter.mapToJson(getAllDestinations());
    }
}
