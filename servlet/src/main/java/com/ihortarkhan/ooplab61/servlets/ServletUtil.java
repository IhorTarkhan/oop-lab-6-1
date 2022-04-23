package com.ihortarkhan.ooplab61.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihortarkhan.ooplab61.services.AuthorizationService;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.function.Function;

public class ServletUtil extends HttpServlet {
  public static final String APPLICATION_JSON = "application/json";
  public static final String UTF_8 = "UTF-8";
  private final AuthorizationService authorizationService = new AuthorizationService();
  private final ObjectMapper objectMapper = new ObjectMapper();

  @SneakyThrows
  public <RESPONSE> void execute(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Function<HttpServletRequest, RESPONSE> executor,
                                 String authority) {
    if (!authorizationService.hasAuthority(request, authority)) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }
    execute(request, response, executor);
  }

  @SneakyThrows
  public <RESPONSE> void execute(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Function<HttpServletRequest, RESPONSE> executor) {
    response.setContentType(APPLICATION_JSON);
    response.setCharacterEncoding(UTF_8);
    PrintWriter out = response.getWriter();
    RESPONSE result = executor.apply(request);
    String jsonResponse = objectMapper.writeValueAsString(result);
    out.print(jsonResponse);
    out.flush();
  }
}
