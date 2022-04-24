package com.ihortarkhan.ooplab61.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihortarkhan.ooplab61.exceptions.HttpException;
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
    public <RESPONSE> void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            Function<HttpServletRequest, RESPONSE> executor,
            String authority) {
        setAccessControlHeaders(response);
        if (!authorizationService.hasAuthority(request, authority)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        execute(request, response, executor);
    }

    @SneakyThrows
    public <RESPONSE> void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            Function<HttpServletRequest, RESPONSE> executor) {
        setAccessControlHeaders(response);
        response.setContentType(APPLICATION_JSON);
        response.setCharacterEncoding(UTF_8);
        PrintWriter out = response.getWriter();
        try {
            RESPONSE result = executor.apply(request);
            String jsonResponse = objectMapper.writeValueAsString(result);
            out.print(jsonResponse);
            out.flush();
        } catch (HttpException e) {
            response.setStatus(e.getHttpCode());
        } catch (RuntimeException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void setAccessControlHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET");
    }
}
