package com.jambit.customer;

import java.util.List;
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
public class CustomerResource {

  @Inject
  CustomerRepository customerRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Customer> findAll() {
    return customerRepository.findAll().list();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Customer findById(@PathParam("id") final long id) {
    return customerRepository.findById(id);
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public void remove(@PathParam("id") final long id) {
    customerRepository.deleteById(id);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public void save(@Valid final Customer customer) {
    customerRepository.persist(customer);
  }
}
