package com.jambit.monitoring;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

@ApplicationScoped
@Liveness
public class MyLivenessProbe implements HealthCheck {

  @Override
  public HealthCheckResponse call() {
    HealthCheckResponseBuilder javatraining_readyness = HealthCheckResponse.named("javatraining cpu readiness");
    int cpu = Runtime.getRuntime().availableProcessors();
    if (cpu > 1){
      javatraining_readyness.up();
    } else {
      javatraining_readyness.down();
    }
    return javatraining_readyness.withData("cpus", "" + cpu).build();
  }
}
