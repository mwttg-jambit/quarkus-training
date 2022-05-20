package com.jambit;

import com.jambit.customer.Customer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Path("/api/customer")
@Slf4j
public class FailingResource {

  private List<Customer> customers;
  private AtomicInteger counter = new AtomicInteger(0);

  @PostConstruct
  public void postConstruct() {
    customers = new ArrayList<>();
    for (long id = 0; id < 100; id++) {
      customers.add(
          new Customer(
              id,
              "Customer-" + id,
              LocalDate.of(1960, 10, 1),
              Customer.Status.ORDINARY));
    }
  }

  @SneakyThrows
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Customer> getAllCustomers(
      @QueryParam("block") @DefaultValue("false") boolean block,
      @QueryParam("fail") @DefaultValue("false") boolean fail) {
    if (fail && counter.incrementAndGet() % 2 == 0) {
      log.info("service is failing");
      throw new RuntimeException("failing");
    }

    if (block && counter.incrementAndGet() % 2 == 0) {
      log.info("service is blocking");
      Thread.sleep(20000L);
    }
    return customers;
  }
}