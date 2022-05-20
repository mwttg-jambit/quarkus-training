package com.jambit.customer;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Path("/api/customer")
@Slf4j
public class CustomerResource {

  @Inject
  CustomerRepository customerRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Multi<Customer> findAllCustomers() {
    log.info("called 'findAllCustomers'");
    final var result = customerRepository.findAll().stream();
    log.info("returned 'findAllCustomers'");
    return result;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<Customer> findById(@PathParam("id") final long id) {
    log.info("called 'findById'");
    final var result = customerRepository.findById(id);
    log.info("returned 'findById'");
    return result;
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Uni<Customer> save(final Customer customer) {
    log.info("called 'save'");
    final var result = Panache.withTransaction(() -> customerRepository.persist(customer));
    log.info("returned 'save'");
    return result;
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Uni<Void> delete(@PathParam("id") final long id) {
    log.info("called 'delete'");
//    final var result = Panache.withTransaction(
//            () -> customerRepository.findById(id))
//        .chain(c -> c == null ? Uni.createFrom().voidItem() : customerRepository.delete(c));
//
//  Alternative: see below
    final var result = Panache.withTransaction(() -> customerRepository.deleteById(id).replaceWithVoid());
    log.info("returned 'delete'");
    return result;
  }
}
