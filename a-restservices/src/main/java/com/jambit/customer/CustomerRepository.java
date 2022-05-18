package com.jambit.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

  List<Customer> findAll();

  Optional<Customer> findById(long id);

  void remove(long id);

  Customer save(Customer customer);

  default void remove(final Customer customer) {
    remove(customer.getId());
  }
}
