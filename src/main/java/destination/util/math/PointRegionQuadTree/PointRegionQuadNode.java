package destination.util.math.PointRegionQuadTree;

import destination.util.math.AxisAlignedBoundingBox;
import destination.util.math.QuadComparable;
import destination.util.math.QuadNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lukas on 14-11-2017.
 */
public class PointRegionQuadNode <G extends QuadComparable> extends QuadNode<G>
{
    protected int maxCapacity = 0;
    protected int maxHeight = 0;

    protected List<G> points = new LinkedList<G>();
    protected int height = 1;

    protected PointRegionQuadNode(AxisAlignedBoundingBox aabb, int maxCapacity, int maxHeight)
    {
        super(aabb);
        this.maxCapacity = maxCapacity;
        this.maxHeight = maxHeight;
    }

    public List<G> getPoints()
    {
        return points;
    }

    @Override
    public boolean insert(G g)
    {
        if(!aabb.contains(g) || isLeaf() && points.contains(g))
            return false;

        if(height == maxHeight || isLeaf() && size() < maxCapacity)
        {
            points.add(g);
            return true;
        }

        if(isLeaf() && height < maxHeight)
            subdivide();

        return insertIntoChildren(g);
    }

    @Override
    public boolean remove(G g)
    {
        if(!aabb.contains(g)) return false;

        if(points.remove(g)) return true;

        if(!isLeaf())
        {
            boolean removed = removeFromChildren(g);
            if(!removed) return false;
            merge();
            return true;
        }
        return false;
    }

    @Override
    public int size()
    {
        return points.size();
    }

    private void subdivide()
    {
        double width = aabb.getWidth() / 2d;
        double height = aabb.getHeight() / 2d;

        QuadComparable quadNW = new QuadComparable(aabb.getX(), aabb.getY() + height);
        AxisAlignedBoundingBox aabbNW = new AxisAlignedBoundingBox(quadNW, width, height);
        northWest = new PointRegionQuadNode<G>(aabbNW, maxCapacity, maxHeight);

        QuadComparable quadNE = new QuadComparable(aabb.getX() + width, aabb.getY() + height);
        AxisAlignedBoundingBox aabbNE = new AxisAlignedBoundingBox(quadNE, width, height);
        northEast = new PointRegionQuadNode<G>(aabbNE, maxCapacity, maxHeight);

        AxisAlignedBoundingBox aabbSW = new AxisAlignedBoundingBox(aabb, width, height);
        southWest = new PointRegionQuadNode<G>(aabbSW, maxCapacity, maxHeight);

        QuadComparable quadSE = new QuadComparable(aabb.getX() + width, aabb.getY());
        AxisAlignedBoundingBox aabbSE = new AxisAlignedBoundingBox(quadSE, width, height);
        southEast = new PointRegionQuadNode<G>(aabbSE, maxCapacity, maxHeight);

        points.stream().forEach(point -> {insertIntoChildren(point);});
        points.clear();
    }

    private void merge()
    {
        if(!northWest.isLeaf() || !northEast.isLeaf() || !southWest.isLeaf() || !southEast.isLeaf())
            return;

        int total = northWest.size();
        total += northEast.size();
        total += southWest.size();
        total += southEast.size();

        if(size() + total < maxCapacity)
        {
            points.addAll(((PointRegionQuadNode<G>)northWest).getPoints());
            points.addAll(((PointRegionQuadNode<G>)northEast).getPoints());
            points.addAll(((PointRegionQuadNode<G>)southWest).getPoints());
            points.addAll(((PointRegionQuadNode<G>)southEast).getPoints());

            northWest = null;
            northEast = null;
            southWest = null;
            southEast = null;
        }
    }

    private boolean insertIntoChildren(G g)
    {
        if(northWest.insert(g)) return true;
        if(northEast.insert(g)) return true;
        if(southWest.insert(g)) return true;
        if(southEast.insert(g)) return true;

        return false;
    }

    private boolean removeFromChildren(G g)
    {
        if(northWest.remove(g)) return true;
        if(northEast.remove(g)) return true;
        if(southWest.remove(g)) return true;
        if(southEast.remove(g)) return true;

        return false;
    }

    @Override
    public void queryRange(AxisAlignedBoundingBox aabb, List<G> objectsInRange)
    {
        if(!this.aabb.intersects(aabb)) return;

        if(isLeaf())
        {
            points.stream().forEach(point ->
            {
                if(aabb.contains(point)) objectsInRange.add(point);
            });
            return;
        }

        northWest.queryRange(aabb, objectsInRange);
        northEast.queryRange(aabb, objectsInRange);
        southWest.queryRange(aabb, objectsInRange);
        southEast.queryRange(aabb, objectsInRange);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append(", ");
        builder.append("[");
        for (QuadComparable p : points) {
            builder.append(p).append(", ");
        }
        builder.append("]");
        return builder.toString();
    }
}
