package destination.util.math;

/**
 * Created by Lukas on 13-11-2017.
 */
public class QuadComparable implements Comparable<Object>
{
    protected double x = Float.MIN_VALUE;
    protected double y = Float.MIN_VALUE;

    public QuadComparable() {}

    public QuadComparable(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    @Override
    public int hashCode()
    {
        int hash = 1;
        hash *= 13 + (int)x;
        hash *= 19 + (int)y;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (!(obj instanceof QuadComparable))
            return false;

        QuadComparable quadComparable = (QuadComparable) obj;
        return compareTo(quadComparable) == 0;
    }

    @Override
    public int compareTo(Object o)
    {
        if ((o instanceof QuadComparable)==false)
            throw new RuntimeException("Cannot compare object.");

        QuadComparable p = (QuadComparable) o;
        int xComp = 0;
        xComp = this.x < p.x ? -1 : 0;
        xComp = this.x > p.x && xComp == 0 ? 1 : 0;
        if (xComp != 0) return xComp;
        int yComp = 0;
        yComp = this.y < p.y ? -1 : 0;
        yComp = this.y > p.y && yComp == 0 ? 1 : 0;
        return yComp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(x).append(", ");
        builder.append(y);
        builder.append(")");
        return builder.toString();
    }
}
