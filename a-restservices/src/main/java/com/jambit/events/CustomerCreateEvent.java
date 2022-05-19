package com.jambit.events;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerCreateEvent {

  private Instant timestamp;
  private long customerId;
}
