package destination.database;

import destination.model.Destination;
import org.danilopianini.util.FlexibleQuadTree;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Lukas on 14-11-2017.
 */
@Component
public class DestinationDatabase_QuadTree implements IDestinationDatabase
{
    private FlexibleQuadTree<Destination> destinations;

    @Override
    public void addDestination(Destination destination)
    {

    }

    @Override
    public void addDestinations(List<Destination> destination)
    {

    }

    @Override
    public List<Destination> getAllDestinations()
    {
        return null;
    }

    @Override
    public Iterator<Destination> getIterator()
    {
        return null;
    }

    @Override
    public Destination getByCode(Integer code)
    {
        return null;
    }

    @Override
    public void remove(Integer code)
    {

    }
}
