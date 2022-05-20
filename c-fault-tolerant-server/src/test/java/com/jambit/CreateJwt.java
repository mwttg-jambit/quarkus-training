package com.jambit;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.jwt.build.Jwt;
import java.time.Duration;
import java.util.Set;
import org.eclipse.microprofile.jwt.Claims;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateJwt {

  @Test
  void generateToken() {
    final var token = Jwt.issuer("https://example.com/issuer")
        .upn("UserPrincipleName")
        .groups(Set.of("User", "Admin"))
        .claim(Claims.birthdate, "1998-11-02")
        .expiresIn(Duration.ofDays(1))
        .sign();

    System.out.println(token);
  }
}
