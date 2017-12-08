package destination.database;

import destination.model.Destination;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Lukas on 12-10-2017.
 */
public interface IDestinationDatabase
{
    void addDestination(Destination destination);
    void addDestinations(List<Destination> destination);
    List<Destination> getAllDestinations();
    Iterator<Destination> getIterator();
    Destination getByCode(Integer code);
    void remove(Integer code);
}
