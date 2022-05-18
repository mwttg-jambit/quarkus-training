package com.jambit.customer;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  public enum Status {ORDINARY, VIP, KING}

  private Long id;

  @NotNull
  @NotEmpty
  @Size(min = 2)
  private String name;

  @NotNull
  @Past
  private LocalDate dateOfBirth;

  @NotNull
  private Status status;
}
