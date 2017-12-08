package destination.util.math;

import java.util.List;

/**
 * Created by Lukas on 13-11-2017.
 */
public abstract class QuadNode<G extends QuadComparable> implements Comparable<QuadNode<G>>
{
    protected final AxisAlignedBoundingBox aabb;

    protected QuadNode<G> northWest = null;
    protected QuadNode<G> northEast = null;
    protected QuadNode<G> southWest = null;
    protected QuadNode<G> southEast = null;

    protected QuadNode(AxisAlignedBoundingBox aabb)
    {
        this.aabb = aabb;
    }

    public abstract boolean insert(G g);
    public abstract boolean remove(G g);
    public abstract int size();
    public abstract void queryRange(AxisAlignedBoundingBox aabb, List<G> objectsInRange);

    public boolean isLeaf()
    {
        return northWest == null && northEast == null && southWest == null && southEast == null;
    }

    @Override
    public int hashCode()
    {
        int hash = aabb.hashCode();
        hash = hash * 13 + ((northWest!=null)?northWest.hashCode():1);
        hash = hash * 17 + ((northEast!=null)?northEast.hashCode():1);
        hash = hash * 19 + ((southWest!=null)?southWest.hashCode():1);
        hash = hash * 23 + ((southEast!=null)?southEast.hashCode():1);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) return false;
        if (!(obj instanceof QuadNode)) return false;

        QuadNode<G> qNode = (QuadNode<G>) obj;

        if (this.compareTo(qNode) == 0) return true;

        return false;
    }

    public int compareTo(QuadNode o)
    {
        return this.aabb.compareTo(o.aabb);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(aabb.toString());
        return builder.toString();
    }
}
