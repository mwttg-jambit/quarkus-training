package com.jambit.customer;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  public enum Status {ORDINARY, VIP, KING}

  private Long id;
  private String name;
  private LocalDate dateOfBirth;
  private Status status;
}
