package com.jambit.customer;

import javax.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
@Slf4j
public class CustomerReceiver {

  @Incoming("customers-in")
  public void receiver(final Customer customer) {
    log.info("received: {}", customer);
  }
}
