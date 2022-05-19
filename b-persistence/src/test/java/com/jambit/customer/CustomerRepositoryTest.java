package com.jambit.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestTransaction
class CustomerRepositoryTest {

  @Inject
  CustomerRepository repository;

  private List<Customer> customers;

  @BeforeEach
  public void setup() {
    final var date = LocalDate.of(1998, 11,4);
    customers = List.of(
        new Customer(null, "NameA1", date, Customer.Status.ORDINARY),
        new Customer(null, "Name2", date, Customer.Status.VIP),
        new Customer(null, "Name3", date, Customer.Status.VIP),
        new Customer(null, "NameA4", date, Customer.Status.KING),
        new Customer(null, "Name5", date, Customer.Status.KING),
        new Customer(null, "Name6", date, Customer.Status.KING));
  }

  @Test
  void testFindAllKings() {
    customers.forEach(x -> repository.persist(x));
    assertEquals(repository.findAllKings().size(), 3);
  }

  @Test
  void testFindAllVips() {
    customers.forEach(x -> repository.persist(x));
    assertEquals(repository.findAllVips().size(), 2);
  }

  @Test
  void testFindByName() {
    customers.forEach(x -> repository.persist(x));
    assertEquals(repository.findByName("NameA%").size(), 2);
  }
}