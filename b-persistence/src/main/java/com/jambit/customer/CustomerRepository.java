package com.jambit.customer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

  public List<Customer> findAllKings() {
    return find("status", Customer.Status.KING).list();
  }

  public List<Customer> findAllVips() {
    return getEntityManager()
        .createQuery("SELECT c FROM Customer c WHERE c.status = :status", Customer.class)
        .setParameter("status", Customer.Status.VIP)
        .getResultList();
  }

  public List<Customer> findAllOrdinary() {
    return find("SELECT c FROM Customer c WHERE c.status = 1", Customer.Status.ORDINARY).list();
  }
}
