package com.jambit;

import io.quarkus.security.Authenticated;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/secured")
public class SecuredResource {

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
