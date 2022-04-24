package com.ihortarkhan.ooplab61.servlets;

import com.ihortarkhan.ooplab61.services.DrinkService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/drinks")
public class DrinksServlet extends HttpServlet {
    private final ServletUtil servletUtil = new ServletUtil();
    private final DrinkService drinkService = new DrinkService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        servletUtil.execute(request, response, r -> drinkService.findAll());
    }
}
