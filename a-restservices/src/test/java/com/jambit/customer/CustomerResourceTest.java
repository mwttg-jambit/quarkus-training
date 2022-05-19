package com.jambit.customer;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class CustomerResourceTest {

  @InjectMock
  CustomerRepository mockRepository; // create Mockito Mock and inject (where repository is used)

  @BeforeEach
  void setUp() {
    Mockito.when(mockRepository.findAll()).thenReturn(List.of(new Customer(123L, "Name", LocalDate.of(1998, 12, 1), Customer.Status.VIP)));
  }

  @Test
  void testFindAll() {
    final Response response = RestAssured.given()
        .when()
        .header("accept", "application/json")
        .get("api/customer");

    final Customer[] result = response.as(Customer[].class);

    Assertions.assertEquals(result.length, 1);
    Assertions.assertEquals(result[0].getId(), 123L);
  }
}
