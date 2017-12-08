package destination.util.math.PointRegionQuadTree;

import destination.util.math.AxisAlignedBoundingBox;
import destination.util.math.QuadComparable;
import destination.util.math.QuadNode;
import destination.util.math.QuadTree;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lukas on 13-11-2017.
 */
public class PointRegionQuadTree<G extends QuadComparable> extends QuadTree<G>
{
    private final QuadComparable point = new QuadComparable();
    private final AxisAlignedBoundingBox AABB = new AxisAlignedBoundingBox();

    private PointRegionQuadNode<G> root = null;

    public PointRegionQuadTree(double x, double y, double width, double height)
    {
        this(x, y, width, height, 4, 20);
    }

    public PointRegionQuadTree(double x, double y, double width, double height, int leafCapacity)
    {
        this(x, y, width, height, leafCapacity, 20);
    }

    public PointRegionQuadTree(double x, double y, double width, double height, int leafCapacity, int maxTreeDepth)
    {
        QuadComparable point = new QuadComparable(x, y);
        AxisAlignedBoundingBox aabb = new AxisAlignedBoundingBox(point, width, height);
        root = new PointRegionQuadNode<G>(aabb, leafCapacity, maxTreeDepth);
    }

    @Override
    protected QuadNode<G> getRoot()
    {
        return root;
    }

    @Override
    public Collection<G> queryRange(double x, double y, double width, double height)
    {
        if(root == null)
            return Collections.EMPTY_LIST;

        QuadComparable point = new QuadComparable(x, y);
        AxisAlignedBoundingBox aabb = new AxisAlignedBoundingBox(point, width, height);
        List<G> objectInRange = new LinkedList<G>();
        root.queryRange(aabb, objectInRange);
        return objectInRange;
    }

    @Override
    public boolean insert(G g)
    {
        return root.insert(g);
    }

    @Override
    public boolean remove(G g)
    {
        return root.remove(g);
    }
}
