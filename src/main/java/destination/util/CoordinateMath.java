package destination.util;

import destination.model.Coordinate;

/**
 * Created by Lukas on 14-11-2017.
 */
public class CoordinateMath
{
    public static final int EARTH_RADIUS = 6371; //approx the radius of earth in km

    public static double haversineDistance(Coordinate c1, Coordinate c2)
    {
        double lat1 = c1.getLatitude();
        double lat2 = c2.getLatitude();
        double lon1 = c1.getLongitude();
        double lon2 = c2.getLongitude();

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon2);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = haversine(dLat) + Math.cos(lat1) * Math.cos(lat2) * haversine(dLon);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public static double haversine(double val)
    {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
