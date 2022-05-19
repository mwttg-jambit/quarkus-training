package com.jambit.interceptors;

import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import lombok.extern.slf4j.Slf4j;

@Interceptor
@MeasurePerformance // Interceptor is linked to this @MeasurePerformance annotation
@ApplicationScoped
@Slf4j
public class PerformanceInterceptor {

  @AroundInvoke
  public Object measurePerformance(final InvocationContext context) throws Throwable {
    final var start = System.currentTimeMillis();
    final var result = context.proceed();
    final var end = System.currentTimeMillis();

    log.info("Duration of {}#{}: {} ms", context.getTarget().getClass().getSimpleName(), context.getMethod().getName(), end - start);

    return result;
  }
}
