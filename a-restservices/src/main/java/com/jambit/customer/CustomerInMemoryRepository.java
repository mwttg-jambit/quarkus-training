package com.jambit.customer;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CustomerInMemoryRepository implements CustomerRepository {

  private final Map<Long, Customer> storage = new HashMap<>();
  private final AtomicLong nextId = new AtomicLong(0L);

  @PostConstruct
  public void postConstruct() {
    log.info("Create CustomerRepository");
    save(new Customer(null, "Testkunde", LocalDate.of(1900, 1, 1), Customer.Status.ORDINARY));
  }

  @Override
  public List<Customer> findAll() {
    log.info("CustomerRepository: findAll");
    return storage.values()
        .stream()
        .sorted(Comparator.comparing(Customer::getId))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Customer> findById(long id) {
    log.info("CustomerRepository: findById {}", id);
    return Optional.ofNullable(storage.get(id));
  }

  @Override
  public void remove(long id) {
    log.info("CustomerRepository: remove {}", id);
    storage.remove(id);
  }

  @Override
  public Customer save(Customer customer) {
    log.info("CustomerRepository: save {}", customer);
    if (customer.getId() == null) {
      customer.setId(nextId.incrementAndGet());
    }
    storage.put(customer.getId(), customer);

    return customer;
  }
}
