package com.jambit.customer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NamedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
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

  public List<Customer> findByName(final String name) {
    return find("#Customer.findByName", Parameters.with("name", name)).list();
  }
}
