package destination.util.math;

import java.util.Collection;

/**
 * Created by Lukas on 13-11-2017.
 */
public abstract class QuadTree<Key extends QuadComparable>
{
    protected abstract QuadNode<Key> getRoot();

    public abstract Collection<Key> queryRange(double x, double y, double width , double height);
    public abstract boolean insert(Key k);
    public abstract boolean remove(Key k);
}
