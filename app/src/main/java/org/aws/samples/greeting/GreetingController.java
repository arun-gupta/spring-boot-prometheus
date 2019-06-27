package org.aws.samples.greeting;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;

/**
 * @author Arun Gupta
 */
@RestController
public class GreetingController {

    // request counter metric
    static final Counter REQUESTS = Counter.build()
            .name("requests_total").help("Total number of requests.").register();
    // histogram metric
    static final Histogram REQUESTS_LATENCY = Histogram.build()
            .name("requests_latency_seconds").help("Request latency in seconds.").register();

    @RequestMapping("/hello")
    public String sayHello() {
        REQUESTS.inc();
        Histogram.Timer requestTimer = REQUESTS_LATENCY.startTimer();
        try {
            return "Hello World";
        } finally {
            requestTimer.observeDuration();
        }
    }
}
