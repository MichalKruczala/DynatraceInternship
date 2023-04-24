package Configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = {
        "it.dynatrace.internship.clients",
        "it.dynatrace.internship.services",
        "it.dynatrace.internship.controllers",
})
@EnableScheduling
public class TestConfiguration {
}
