package destination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Lukas on 15-11-2017.
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan
@EnableScheduling
public class DestinationApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DestinationApplication.class, args);
    }
}
