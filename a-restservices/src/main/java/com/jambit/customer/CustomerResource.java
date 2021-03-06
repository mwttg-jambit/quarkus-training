package com.jambit.customer;

import com.jambit.events.CustomerCreateEvent;
import com.jambit.interceptors.MeasurePerformance;
import java.time.Instant;
import java.util.List;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/customer")
@MeasurePerformance
public class CustomerResource {

  @Inject
  CustomerRepository customerRepository;

  @Inject
  Event<CustomerCreateEvent> createEvent;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Customer findById(@PathParam("id") final long id) {
    return customerRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public void remove(@PathParam("id") final long id) {
    customerRepository.remove(id);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Customer save(@Valid final Customer customer) {
    final var isNewCustomer = customer.getId() == null;
    final var savedCustomer =  customerRepository.save(customer);

    if (isNewCustomer) {
      final var event = new CustomerCreateEvent(Instant.now(), savedCustomer.getId());
      createEvent.fireAsync(event);
    }

    return savedCustomer;
  }
}
