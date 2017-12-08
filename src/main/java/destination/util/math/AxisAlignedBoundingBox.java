package destination.util.math;

/**
 * Created by Lukas on 14-11-2017.
 */
public class AxisAlignedBoundingBox extends QuadComparable
{
    private double height = 0;
    private double width = 0;

    private double minX = 0;
    private double minY = 0;
    private double maxX = 0;
    private double maxY = 0;

    public AxisAlignedBoundingBox() {}

    public AxisAlignedBoundingBox(QuadComparable lowerLeft, double width, double height)
    {
        set(lowerLeft, width, height);
    }

    public void set(QuadComparable lowerLeft, double width, double height)
    {
        set(lowerLeft.x, lowerLeft.y);
        this.width = width;
        this.height = height;

        minX = lowerLeft.x;
        minY = lowerLeft.y;
        maxX = lowerLeft.x + width;
        maxY = lowerLeft.y + height;
    }

    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }

    public boolean contains(double x, double y)
    {
        if(x >= maxX) return false;
        if(x <= minX) return false;
        if(y >= maxY) return false;
        if(y <= minY) return false;
        return true;
    }

    public boolean contains(QuadComparable point)
    {
        return contains(point.x, point.y);
    }

    public boolean contains(AxisAlignedBoundingBox aabb)
    {
        return aabb.minX >= minX && aabb.maxX <= maxX && aabb.minY >= minY && aabb.maxY <= maxY;
    }

    public boolean intersects(AxisAlignedBoundingBox aabb)
    {
        if (contains(aabb) || aabb.contains(this))
            return true;

        if (maxX < aabb.minX || minX > aabb.maxX) return false;
        if (maxY < aabb.minY || minY > aabb.maxY) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 13 + (int)height;
        hash = hash * 19 + (int)width;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof AxisAlignedBoundingBox))
            return false;

        AxisAlignedBoundingBox aabb = (AxisAlignedBoundingBox) obj;
        return compareTo(aabb) == 0;
    }

    @Override
    public int compareTo(Object o) {
        if ((o instanceof AxisAlignedBoundingBox)==false)
            throw new RuntimeException("Cannot compare object.");

        AxisAlignedBoundingBox aabb = (AxisAlignedBoundingBox) o;

        if (height>aabb.height) return 1;
        if (height<aabb.height) return -1;

        if (width>aabb.width) return 1;
        if (width<aabb.width) return -1;

        return 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(super.toString()).append(", ");
        builder.append("height").append("=").append(height).append(", ");
        builder.append("width").append("=").append(width);
        builder.append(")");
        return builder.toString();
    }
}
