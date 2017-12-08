package destination.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import destination.util.CoordinateMath;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Lukas on 14-11-2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "latitude",
        "longitude"
})
public class Coordinate
{
    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    public Coordinate()
    {
        this(Double.MIN_VALUE, Double.MIN_VALUE);
    }

    public Coordinate(double latitude, double longitude)
    {
        set(latitude, longitude);
    }

    public void set(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double distance(Coordinate coordinate)
    {
        return CoordinateMath.haversineDistance(this, coordinate);
    }

    @JsonProperty("latitude")
    public double getLatitude()
    {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    @JsonProperty("longitude")
    public double getLongitude()
    {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .toString();
    }
}
