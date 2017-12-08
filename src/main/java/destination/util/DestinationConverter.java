package destination.util;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import destination.config.DatabaseConfig;
import destination.model.Destination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Lukas on 12-10-2017.
 */
@Service
public class DestinationConverter
{

    private static final Logger logger = LoggerFactory.getLogger(DestinationConverter.class);

    public List<Destination> readPlaces(String fileName)
    {
        ObjectMapper mapper = new ObjectMapper();

        List<Destination> destinations = null;
        try
        {
            destinations = mapper.readValue(new File(DatabaseConfig.RESOURCE_LOCATION + "/" + fileName), new TypeReference<List<Destination>>(){});
        } catch (IOException e)
        {
            logger.error(e.toString());
        }
        logger.info("amount of destinations loaded: " + destinations.size());
        return destinations;
    }

    public void mapToJson(List<Destination> destinations)
    {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer();
        try
        {
            writer.writeValue(new File(DatabaseConfig.RESOURCE_LOCATION + "/destinations.json"), destinations);
        } catch (IOException e)
        {
           logger.error(e.toString());
        }
    }
}
