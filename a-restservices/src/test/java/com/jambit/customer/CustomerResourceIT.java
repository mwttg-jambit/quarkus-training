package com.jambit.customer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CustomerResourceIT {

  private static final String PATH = "api/customer";

  @Test
  void findAll() {
    final Response response = RestAssured.given()
        .when()
        .header("accept", "application/json")
        .get(PATH);

    final Customer[] result = response.as(Customer[].class);

    response.then()
        .header("Content-Type", "application/json;charset=UTF-8")
        .and()
        .statusCode(200)
        .and()
        .body("size()", CoreMatchers.is(1));
  }

  @Test
  void findById() {
  }

  @Test
  void remove() {
  }

  @Test
  void save() {
  }
}