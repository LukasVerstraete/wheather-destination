package destination.service;

import destination.config.ApplicationConfig;
import destination.config.DatabaseConfig;
import destination.model.Coordinate;
import destination.model.google.GoogleMapsResult;
import destination.model.google.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

/**
 * Created by Lukas on 15-11-2017.
 */
@Component
public class GoogleMapService
{
    private RestService restService;
    private static final Logger logger = LoggerFactory.getLogger(GoogleMapService.class);

    @Autowired
    public GoogleMapService(RestService restService)
    {
        this.restService = restService;
    }

    public Coordinate getCoordinateForLocation(String country, String city)
    {
        URI uri = new UriTemplate(ApplicationConfig.GEOCOE_API_URL).expand(city + "+" + country, ApplicationConfig.GEOCODE_API_KEY);
        GoogleMapsResult results = restService.request(uri, GoogleMapsResult.class);
        Coordinate coordinate = null;
        if(results.getResults().size() > 0)
        {
            Result result = results.getResults().get(0);
            if(result != null)
            {
                double lat = result.getGeometry().getLocation().getLat();
                double lng = result.getGeometry().getLocation().getLng();
                coordinate = new Coordinate(lat, lng);
            }
        }
        return coordinate;
    }
}
