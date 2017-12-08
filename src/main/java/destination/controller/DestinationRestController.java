package destination.controller;

import destination.model.Destination;
import destination.service.DestinationIterationService;
import destination.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Lukas on 15-11-2017.
 */
@RestController
public class DestinationRestController
{

    private DestinationService destinationService;
    private DestinationIterationService destinationIterationService;

    @Autowired
    public DestinationRestController(DestinationService destinationService, DestinationIterationService destinationIterationService)
    {
        this.destinationService = destinationService;
        this.destinationIterationService = destinationIterationService;
    }

    @RequestMapping(path="destination", method=RequestMethod.GET)
    public List<Destination> getAllDestinations()
    {
        return destinationService.getAllDestinations();
    }

    @RequestMapping(path="/destination/code/{code}", method = RequestMethod.GET)
    public Destination getByCode(@PathVariable Integer code)
    {
        return destinationService.getDestinationByCode(code);
    }

    @RequestMapping(path="/destination/next", method=RequestMethod.GET)
    public Destination getNext()
    {
        return destinationIterationService.getNextDestination();
    }
}
