package destination.schedule;

import destination.database.DestinationDatabase_HashMap;
import destination.database.IDestinationDatabase;
import destination.model.Coordinate;
import destination.model.Destination;
import destination.service.GoogleMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by Lukas on 15-11-2017.
 */
@Component
public class DestinationScheduling
{
    private DestinationDatabase_HashMap destinationDatabase;
    private Logger logger = LoggerFactory.getLogger(DestinationScheduling.class);
    private GoogleMapService googleService;

    @Autowired
    public DestinationScheduling(DestinationDatabase_HashMap database, GoogleMapService googleService)
    {
        this.destinationDatabase = database;
        this.googleService = googleService;
    }



    @Scheduled(fixedRate = 60000)
    public void saveDatabase()
    {
        logger.info("Saving the database...");
        destinationDatabase.saveDataBase();
        logger.info("Finished saving the database.");
        Long count = destinationDatabase.getAllDestinations().stream().filter(d -> d.getCoordinate() == null).count();
        int size = destinationDatabase.getAllDestinations().size();
        logger.info("Destinations without Coodinates: " + count + "/" + size);
    }

    @Scheduled(fixedRate = 5000)
    public void pollCoordinates()
    {
        Destination destination = getDestinationWithoutCoordinates();
        if(destination != null)
        {
            Coordinate coordinate = googleService.getCoordinateForLocation(destination.getCountry(), destination.getCity());
            destination.setCoordinate(coordinate);
        }
    }

    private Destination getDestinationWithoutCoordinates()
    {
        Destination destination = null;

        Optional<Destination> result = destinationDatabase.getAllDestinations().stream().filter(d -> d.getCoordinate() == null).findFirst();
        try{
            destination = result.get();
        }
        catch(NoSuchElementException e)
        {
            logger.info("No Destinations found without coodinates");
        }
        return destination;
    }
}
