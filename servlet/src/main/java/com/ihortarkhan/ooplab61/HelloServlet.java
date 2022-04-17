package com.ihortarkhan.ooplab61;

import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.resource.ProtectedResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.authorization.AuthorizationRequest;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.keycloak.representations.idm.authorization.ScopeRepresentation;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
  private String message = "Hello World!";

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
    res.setContentType("text/html");
    List.of().stream().toList().stream();
    AuthzClient authzClient =
        AuthzClient.create(
            new FileInputStream("/Users/ihor/IdeaProjects/OOP/oop-lab-6-1/servlet/keycloak.json"));
    AccessTokenResponse response = authzClient.obtainAccessToken("myuser", "myuser");
    String token = response.getToken();
    log(token);


    ResourceRepresentation newResource = new ResourceRepresentation();

    newResource.setName("New Resource");
    newResource.setType("urn:hello-world-authz:resources:example");

    newResource.addScope(new ScopeRepresentation("urn:hello-world-authz:scopes:view"));

    ProtectedResource resourceClient = authzClient.protection().resource();
    ResourceRepresentation existingResource = resourceClient.findByName(newResource.getName());

    if (existingResource != null) {
      resourceClient.delete(existingResource.getId());
    }

// create the resource on the server
    ResourceRepresentation response2 = resourceClient.create(newResource);
    String resourceId = response2.getId();

// query the resource using its newly generated id
    ResourceRepresentation resource = resourceClient.findById(resourceId);

    log(resource.toString());


    // Hello
    PrintWriter
    out = res.getWriter();
    out.println("<html><body>");
    out.println("<h1>" + message + "</h1>");
    out.println("</body></html>");
  }
}
