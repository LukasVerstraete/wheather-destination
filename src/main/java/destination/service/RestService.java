package destination.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by Lukas on 15-11-2017.
 */
@Component
public class RestService
{
    private Logger logger = LoggerFactory.getLogger(RestService.class);
    private RestTemplate restTemplate;

    public RestService()
    {
        restTemplate = new RestTemplate();
    }

    public <T> T request(URI uri, Class<T> responseType)
    {
        RequestEntity<?> request = RequestEntity.get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        ResponseEntity<T> exchange = null;
        try
        {
            exchange = this.restTemplate.exchange(request, responseType);
        }
        catch(HttpClientErrorException exception)
        {
            logger.error(exception.getResponseBodyAsString());
            return null;
        }
        return exchange.getBody();
    }
}
