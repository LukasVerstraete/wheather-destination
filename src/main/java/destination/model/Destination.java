package destination.model;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lukas on 12-10-2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "city",
    "country",
    "coordinate"
})
public class Destination
{
    @JsonProperty("code")
    private Integer code;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country = "Belgium";

    @JsonProperty("coordinate")
    private Coordinate coordinate;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("country")
    public String getCountry()
    {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country)
    {
        this.country = country;
    }

    @JsonProperty("coordinate")
    public Coordinate getCoordinate()
    {
        return coordinate;
    }

    @JsonProperty("coordinate")
    public void setCoordinate(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties)
    {
        this.additionalProperties = additionalProperties;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("city", city)
                .append("county", country)
                .append("coordinate", coordinate)
                .append("additionalProperties", additionalProperties)
                .toString();
    }
}
