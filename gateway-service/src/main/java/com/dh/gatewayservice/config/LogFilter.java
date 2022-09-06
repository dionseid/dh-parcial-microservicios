package com.dh.gatewayservice.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.logging.Logger;

@Component
public class LogFilter extends AbstractGatewayFilterFactory<LogFilter.Config> {

    private static final Logger LOG = Logger.getLogger(LogFilter.class.getName());

    public LogFilter() {
        super(Config.class); // sobrecarga del constructor, llamado a la clase padre
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            // filtro previo a la invocación del servicio real asociado al gateway
            LOG.info("path requested: " + exchange.getRequest().getPath());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // filtro posterior a la invocación del servicio real asociado al gateway
                // En este punto se pierde el rastro, o el ID de traceo es diferente
                LOG.info("Request processing is complete");
                LOG.info("time response: " + Calendar.getInstance().getTime());
                LOG.info("port used: " + exchange.getResponse().getHeaders().get("port"));
            }));

        };

    }

    public static class Config {
        // Put the configuration properties, variables, or personalized messages
    }

}
