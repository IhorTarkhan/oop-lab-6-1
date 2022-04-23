package com.ihortarkhan.ooplab61.servlets;

import lombok.SneakyThrows;
import org.keycloak.TokenVerifier;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessToken;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import static com.ihortarkhan.ooplab61.configurations.PropertiesConfiguration.getProperties;
import static org.keycloak.authorization.client.AuthzClient.create;

@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    k();
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    out.println("<h1>Hello World!</h1>");
    out.println("</body></html>");
  }

  @SneakyThrows
  private void k() {
    AuthzClient authorizationClient =
        create(new FileInputStream(getProperties().getKeycloakFile()));
    String tokenString = authorizationClient.obtainAccessToken("myuser", "myuser").getToken();

    log(tokenString);
    AccessToken token = TokenVerifier.create(tokenString, AccessToken.class).getToken();
    log(token.getEmail());
    log(token.getIat().toString());
    log(token.getExp().toString());
    Set<String> roles = token.getRealmAccess().getRoles();
    roles.contains("coffee_user");
    log(String.join(", ", roles));
  }
}
