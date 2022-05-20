package com.jambit;

import io.quarkus.security.Authenticated;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import lombok.extern.slf4j.Slf4j;

@Path("/api/secured")
@Slf4j
public class SecuredResource {

  @Inject
  SecurityContext context;

  @GET
  @Path("/public")
  @Produces(MediaType.APPLICATION_JSON)
  public String publicEndpoint() {
    return "public access";
  }

  @GET
  @Path("/authenticated")
  @Produces(MediaType.APPLICATION_JSON)
  @Authenticated
  public String authenticatedEndpoint() {
    final var name = context.getUserPrincipal().getName();
    log.info("name = {}", name);
    return "authenticated access";
  }

  @GET
  @Path("/admin")
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed("Admin")
  public String roleAdminEndpoint() {
    return "role admin access";
  }

  @GET
  @Path("/other")
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed("Other")
  public String roleOtherEndpoint() {
    return "role other access";
  }
}
