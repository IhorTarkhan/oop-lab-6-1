package com.ihortarkhan.ooplab61.servlets;

import com.ihortarkhan.ooplab61.services.CoffeeAdminService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Refresh-drink")
public class RefreshDrinkServlet extends HttpServlet {
    private final ServletUtil servletUtil = new ServletUtil();
    private final CoffeeAdminService coffeeAdminService = new CoffeeAdminService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        servletUtil.execute(request, response, r -> {
            coffeeAdminService.refreshDrink(r);
            return new Object();
        }, "coffee_admin");
    }
}
