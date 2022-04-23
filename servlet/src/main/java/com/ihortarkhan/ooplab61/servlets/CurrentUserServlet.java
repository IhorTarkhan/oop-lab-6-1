package com.ihortarkhan.ooplab61.servlets;

import com.ihortarkhan.ooplab61.services.CoffeeUserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/coffee-user")
public class CurrentUserServlet extends HttpServlet {
  private final ServletUtil servletUtil = new ServletUtil();
  private final CoffeeUserService coffeeUserService = new CoffeeUserService();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    servletUtil.execute(request, response, coffeeUserService::getCoffeeUser);
    servletUtil.execute(request, response, coffeeUserService::getCoffeeUser, "coffee_user");
    servletUtil.execute(request, response, coffeeUserService::getCoffeeUser, "coffee_admin");
  }
}
