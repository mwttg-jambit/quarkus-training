package com.jambit;

import com.jambit.customer.Customer;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("/api/customers/counter")
public class CustomerCountResource {

  private final Client client = ClientBuilder.newClient();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public int countCustomers() {
    final Customer[] customers = client
        .target("http://localhost:8080/api/customer")
        .request(MediaType.APPLICATION_JSON)
        .get(Customer[].class);
    return customers.length;
  }

  @GET
  @Path("/retry")
  @Produces(MediaType.APPLICATION_JSON)
  @Retry(maxRetries = 4, delay = 500)
  public int countCustomersWithRetry() {
    final Customer[] customers = client
        .target("http://localhost:8080/api/customer?fail=true")
        .request(MediaType.APPLICATION_JSON)
        .get(Customer[].class);
    return customers.length;
  }

  @GET
  @Path("/fallback")
  @Produces(MediaType.APPLICATION_JSON)
  @Fallback(fallbackMethod = "fallback")
  public int countCustomersWithFallback() {
    final Customer[] customers = client
        .target("http://localhost:8080/api/customer?fail=true")
        .request(MediaType.APPLICATION_JSON)
        .get(Customer[].class);
    return customers.length;
  }

  public int fallback() {
    return 123;
  }

  @GET
  @Path("/timeout")
  @Produces(MediaType.APPLICATION_JSON)
  @Timeout(value = 2000L, unit = ChronoUnit.MILLIS)  // unfortunately the timeout exception is thrown after 20s (when the result is returned not after 1 s - but it works if annotation is put on the server side
  public int countCustomersWithTimeout() {
    final Customer[] customers = client
        .target("http://localhost:8080/api/customer?block=true")
        .request(MediaType.APPLICATION_JSON)
        .get(Customer[].class);
    return customers.length;
  }

  @GET
  @Path("/circuit-breaker")
  @Produces(MediaType.APPLICATION_JSON)
  @CircuitBreaker(failureRatio = 0.25, requestVolumeThreshold = 10)
  public int countCustomersWithCircuitBreaker() {
    final Customer[] customers = client
        .target("http://localhost:8080/api/customer?fail=true")
        .request(MediaType.APPLICATION_JSON)
        .get(Customer[].class);
    return customers.length;
  }
}