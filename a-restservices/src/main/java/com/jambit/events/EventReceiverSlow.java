package com.jambit.events;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class EventReceiverSlow {

  @SneakyThrows
  public void customerCreatedListener(@ObservesAsync final CustomerCreateEvent event) {
    log.info("Received: {}", event);
    Thread.sleep(5000L);
    log.info("Process finished");
  }

  public void containerStartUpListener(@Observes final StartupEvent event) {
    log.info("Container started");
  }

  public void containerShutDownEvent(@Observes final ShutdownEvent event) {
    log.info("Container shutdown");
  }
}
