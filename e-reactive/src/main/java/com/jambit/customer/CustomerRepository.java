package com.jambit.customer;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
// @Transactional can't be used in reactive - see #save in the resource
public class CustomerRepository implements PanacheRepository<Customer> {
}
