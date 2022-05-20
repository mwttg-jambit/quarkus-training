package com.jambit.customer;

import io.quarkus.scheduler.Scheduled;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
@Slf4j
public class CustomerSender {

  private final AtomicLong counter = new AtomicLong(0);

  @Inject
  @Channel("customers-out")
  Emitter<Customer> customerEmitter;

  @Scheduled(every = "2s")
  public void send() {
    final var id = counter.incrementAndGet();
    final var customer =
        new Customer(id, "CustomerName-" + id, LocalDate.of(1998, 10, 1), Customer.Status.ORDINARY);
    log.info("Sending: {}", customer);

    customerEmitter.send(customer);
  }
}
