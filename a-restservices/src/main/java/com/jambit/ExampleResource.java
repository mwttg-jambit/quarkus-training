package com.jambit;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class ExampleResource {

  @ConfigProperty(name = "jambit.message")
  String message;

  @Inject
  AtJava atJava;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return message + " " + atJava.name() + " " + atJava.address() + " " + atJava.email();
  }
}